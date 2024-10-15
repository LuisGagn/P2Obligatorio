
package tatetigigante;

import java.util.ArrayList;
import java.util.Scanner;
import tatetigigante.Jugador;




public class sistema {
    
    
    
    
//  ----------------------------------------             
//  |   ESTADO DEL TABLERO Y SUBTABLEROS   |
//  ----------------------------------------    
    

//  ESTADO DE CADA SUBTABLERO    
    public static int estadoMatriz(char[][] tablero, int[] tateti){
        int ganador = 0;
        int fila = tateti[0];
        int col = tateti[1];
        boolean encontrado = false;
        
//      VER DIAGONALES
        if(match(tablero[fila][col], tablero[fila+1][col+1], tablero[fila+2][col+2]) ||
           match(tablero[fila][col+2], tablero[fila+1][col+1], tablero[fila+2][col])) {
            ganador = (tablero[fila+1][col+1] == 'X') ? 1 : 2;
        }
        
//      VER HORIZONTALES
        for (int i = 0; i < 3 && !encontrado; i++) {
            if (match(tablero[fila+i][col], tablero[fila+i][col+1], tablero[fila+i][col+2])) {
             ganador = (tablero[fila+i][col] == 'X') ? 1 : 2;
            }
        }
        
//      VER VERTICALES
        for (int j = 0; j < 3 && !encontrado; j++) {
            if (match(tablero[fila][col+j], tablero[fila+1][col+j], tablero[fila+2][col+j])) {
                ganador = (tablero[fila][col+j] == 'X') ? 1 : 2;
            }
        }
        
        return ganador;
 
    }
    
//  ESTADO DEL TABLERO GLOBAL (SI HAY UN GANADOR)
    public static int estadoJuego(int[][] matrizTestigo){

        char[][] charMatrix = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                charMatrix[i][j] = (char) (matrizTestigo[i][j]+'0');  
            }
        }
        int[] initial = {0,0};
        int ganador = estadoMatriz(charMatrix, initial);
        
        return ganador;
    }
    
//  Modifica la matriz testigo segun si el tablero lo gano X o O.
    public static int[][] estadoTestigo(int[][] matrizTestigo, char[][] tablero, int[] tateti){
        

        int ganador = estadoMatriz(tablero,tateti);

        int filaT = tateti[0] / 3;
        int colT = tateti[1] / 3;

        matrizTestigo[filaT][colT] = ganador;
        
        return matrizTestigo;
    }

    
//  Indica la posicion en el tablero de la jugada.     
    public static int[] seleccionJugada (int[] tateti, String casilla){
        
        int[] concat = new int[2];
        
        // 0,0 | 0,3 | 0,6 
        // 3,0 | 3,3 | 3,6
        // 6,0 | 6,3 | 6,6
        
        concat[0] = switch (casilla.charAt(0)) {
            case 'A' -> tateti[0];
            case 'B' -> tateti[0]+1;
            case 'C' -> tateti[0]+2;
            default -> -1;
        };   
        
        concat[1] = switch (casilla.charAt(1)) {
            case '1' -> tateti[1];
            case '2' -> tateti[1]+1;
            case '3' -> tateti[1]+2;
            default -> -1;
        };   
        
        return concat;
        
    }
    

//  Indica la fila y columna de la casilla inicial en el tablero de cada tateti
//  Estas son: 0,0; 0,3; 0,6; 3,0; 3,3; 3,6; 6,0; 6,3; 6,6    

    public static int[] seleccionTablero(String casilla){
        
        int[] concat = new int[2];
        
        concat[0] = switch (casilla.charAt(0)) {
            case 'A' -> 0;
            case 'B' -> 3;
            case 'C' -> 6;
            default -> -1;
        };   
        
        concat[1] = switch (casilla.charAt(1)) {
            case '1' -> 0;
            case '2' -> 3;
            case '3' -> 6;
            default -> -1;
        };   
 
        return concat;
    }

    
//  Ingresa "X" o "O" segun el turno.
    public static char[][] jugadas (char[][] tablero, int[] jugada, int turno){
        
        // Valores  Par --> turno 1 || Impar --> turno 2
        char valor ='X';
        if(turno %2 != 0){
            valor = 'O';
        }        
        
        tablero[jugada[0]][jugada[1]] = valor;
        return tablero;
    }
    
   
    
//  Valida cualquier string ingresado, si es Q, M o si es una jugada, asi mismo valida que la jugada sea valida.    
    public static String validador(String palabra){
        
        Scanner in = new Scanner(System.in);
        palabra = palabra.toUpperCase();
        boolean posicionIncorrecta = false;
        boolean quitOrMaestra = (palabra.indexOf('X')==-1 && palabra.indexOf('M')==-1);
        
//  Verifica que sea un string tipo A1 B2 C3, etc.         
        if (quitOrMaestra && palabra.length()==2){
        String letras = "ABC";
        int num = Character.digit(palabra.charAt(1), 10);

        if(palabra.length()!=2){
            posicionIncorrecta = true;
        } else {
              if(letras.indexOf(palabra.charAt(0))==-1){
                posicionIncorrecta = true;
              }
              if (num> 3 || num < 0){
                  posicionIncorrecta = true;
              }
        }
           } 
//  Si es Q o M, lo devuelve, si no pide una nueva.        
        else { 
            if(palabra.length() ==1 && !quitOrMaestra){
            palabra +=" ";
            } else {
                posicionIncorrecta = true;
            }
        }
        
       
        if(posicionIncorrecta) {
             System.out.println("Vuelva a ingresar una posicion valida");
            palabra = in.nextLine();
            return validador(palabra);
        }
     
        return palabra;
    }
    
//  Funcion utilizada para validar ganador.    
    public static boolean match(char a, char b, char c){
            return a == b && b == c && (a == 'X' || a == 'O' || a == '1' || a == '2');
        }
    
    
    
//  Funcion que detecta empate global
    public static boolean empate(int[][] matrizTestigo){
        
        boolean buscando = true;
        
        for(int i = 0; i < 3 && buscando; i++){
            for(int j = 0; j < 3&& buscando; j++) {
                if(matrizTestigo[i][j]==0){
                    buscando = false;
                }
            }
        }
        
//  Si buscando es True, significa que no encontre valor, por lo que hay un empate.
        return buscando;
    }
    
    
    
//  Devuelve si se indico terminar el juego o no.    
    public static boolean terminar(String movimiento){

        if (movimiento.indexOf('X')!=-1){
            return true;
        } else {
            return false;
        }

    }
    
//  Borra la consola (Empuja en pantalla lo que no debe verse).    
    public static void clearConsole() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
}

    
//      ----------------------------------------------
//      | FUNCION SOLAMENTE UTILIZADA EN VS. MAQUINA | 
//      ----------------------------------------------
    
//  Ingresa una posicion en el tablero, elige la primer casilla disponible o tablero.    
    public static String maquinaCasilla(char[][] tablero, int[] tateti){
        int[] posicion = new int[2];
        int fila = tateti[0];
        int col = tateti[1];
        boolean valido = false;
        
        for(int i = fila; i < fila+3 && !valido; i++){
            for(int j = col; j < col+3 && !valido; j++){
                
                if(tablero[i][j]!= 'X' && tablero[i][j] != 'O'){
                    posicion[0]=i;
                    posicion[1]=j;
                    valido=true;
                }               
            }
        }
        
        String casilla = "";
            if(posicion[0] == fila){
                casilla+='A';
            } else{ 
            if(posicion[0] == fila+1){
                casilla+='B';
            } else{
            if(posicion[0] == fila+2){
                casilla+='C';
            }
            }
            }
            if(posicion[1] == col){
                casilla+='1';
            } else{ 
            if(posicion[1] == col+1){
                casilla+='2';
            } else{
            if(posicion[1] == col+2){
                casilla+='3';
            }
            }
            }
        return casilla;
        
    }
    
    
//      -------------------------------------
//      | FUNCIONES SOBRE LA JUGADA MAESTRA | 
//      -------------------------------------
    
//  Indica si puede o no jugar la jugada maestra.    
    public static boolean maestra(int turno, boolean[] jugadaUtilizada){
        if(turno%2 == 0 && !jugadaUtilizada[0]){
            return true;
        } 
        if(turno%2 != 0 && !jugadaUtilizada[1]){
            return true;
        } 
        
        return false;
    }
    
    
//  Modifica el valor si se utiliza la jugada maestra.    
    public static boolean[] maestraValidador(int turno, boolean[] jugadaUtilizada){
        System.out.println(turno);
       
        if(turno%2 == 0){
            jugadaUtilizada[0] = true;
        } 
        if(turno%2 != 0){
            jugadaUtilizada[1] = true;
        } 
        
        return jugadaUtilizada;
    }
    
    
//  Borra el mini tablero.    
    public static char [][] maestraTablero(char[][] tablero, int[] tateti){
        
      // int[] tateti = seleccionTablero(movimiento);

        int fila = tateti[0];
        int col = tateti[1];
        
        
        for(int i = fila; i< fila+3; i++){
            for (int j = col; j < col+3; j++){
                tablero[i][j]='\u0000';
            }
        }
        return tablero;
    }
 
//  Borra los datos del testigo    
    public static int[][] maestraTestigo(int[][] matrizTestigo, int[] tateti){
        //int[] tateti = seleccionTablero(movimiento);
        
        int fila = tateti[0]/3;
        int col = tateti[1]/3;
        
        matrizTestigo[fila][col] = 0;

        return matrizTestigo;

    }
    
//  Borra las jugadas.
    public static int[][] maestraCantJugadas(int[][] cantJugadasTateti, int[] tateti){
       // int[] tateti = seleccionTablero(movimiento);
        
        int fila = tateti[0]/3;
        int col = tateti[1]/3;
        
        cantJugadasTateti[fila][col] = 0;
        

        return cantJugadasTateti;
        
        
    }
    
    

    
        
//      ------------------------------
//      | SISTEMA REGISTRO JUGADORES | 
//      ------------------------------
    
    private static ArrayList<Jugador> jugadores = new ArrayList<>();

    public static void registroJugador(){
        Scanner in = new Scanner(System.in);
        
        boolean deseaRegistrar = true;
        while(deseaRegistrar){
            
            System.out.println("Ingresa el nombre del jugador:");
            String nombre = in.nextLine();

            System.out.println("Ingresa la edad del jugador:");
            int edad = in.nextInt();
            in.nextLine();  // Limpiar el buffer del in

            System.out.println("Ingresa el alias del jugador:");
            String alias = in.nextLine();

          
            
            try {
            Jugador nuevoJugador = new Jugador(nombre, edad, alias, 0);
            jugadores.add(nuevoJugador);
            System.out.println("Jugador registrado exitosamente: "+nuevoJugador);
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                alias = in.nextLine();
            }
            
            System.out.println("¿Desea registrar otro jugador? (s/n)");
            
            String respuesta = in.nextLine();
            
            if(!respuesta.equalsIgnoreCase("s")){
                deseaRegistrar=false;
            }
            
        }
        
        
        
        
    }
    
    public static void imprimirRanking(){
        System.out.println("\n Jugadores:");
            
        for(Jugador jugador : jugadores){
            System.out.println(jugador);
        }
       
        
            }
    
    
    
    
    public static String[] elegirJugadores(){
        String[] jugadoresElegidos = new String[2];
        Scanner in = new Scanner(System.in);
        
        for(Jugador jugador : jugadores){
            System.out.println(jugador.getAlias());
        }
        
        System.out.println("Indique el alias del jugador 1 ");
        
        jugadoresElegidos[0] = in.nextLine();
        
        System.out.println("Indique el alias del jugador 2 ");
        jugadoresElegidos[1] = in.nextLine();
        
        return jugadoresElegidos;
        
    }
    
    public static void agregarVictorias(String alias) {
        
        for(Jugador jugador: jugadores){
            if(jugador.getAlias().equals(alias)){
                jugador.addVictorias();
            }
        }
        
    }
    
    
    
}
