
package tatetigigante;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import tatetigigante.Jugador;


// AGREGAR COLORES AL MENU

public class Sistema{
    
    
    
    
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
    
    
//  Lista con todos los jugadores
    private static ArrayList<Jugador> jugadores = new ArrayList<>();

    
//  Registra nuevos jugadores tras realizar todas las validaciones necesarias    
    public static void registroJugador(){
        Scanner in = new Scanner(System.in);
        clearConsole();
        int edad;
        boolean deseaRegistrar = true;
        while(deseaRegistrar){
            
//      Se ingresan los datos del jugador, si no son validos se vuelven a solicitar    


//          NOMBRE
            System.out.println("Ingresa el nombre del jugador:");
            String nombre = in.nextLine();

//          EDAD
            System.out.println("Ingresa la edad del jugador:");
            while(true){
                try{
                    edad = in.nextInt();
                    in.nextLine();  // Limpiar el buffer del in
                    break;
                } catch (Exception e) {
                    System.out.println("La edad debe ser un numero");
                    in.nextLine();

                }
            }
            
//          ALIAS            
            System.out.println("Ingresa el alias del jugador:");
            String alias = in.nextLine();
            
//      Si el alias esta repetido se vuelve a solicitar uno nuevo            
            while(!Jugador.aliasRegistrado(alias) || alias.length() <1){
                System.out.println("Alias invalido, ingrese otro");
                alias = in.nextLine();
            }

            
//      Se agrega el jugador nuevo y se pregunta si desea agregar otro jugador mas.
            Jugador nuevoJugador = new Jugador(nombre, edad, alias, 0);
            jugadores.add(nuevoJugador);
            System.out.println("Jugador registrado exitosamente: "+nuevoJugador.getNombre());
 
            
            System.out.println("¿Desea registrar otro jugador? (s/n)");
            String respuesta = in.nextLine();
            
            if(!respuesta.equalsIgnoreCase("s")){
                deseaRegistrar=false;
                clearConsole();
            }
            
        }
        
        
        
        
    }
    
//  Imprime el ranking de jugadores ordenado de manera decreciente    
    public static void imprimirRanking(){
        clearConsole();
        System.out.println("\n Ranking de jugadores:");
            
       jugadores.sort(Comparator.comparingInt(Jugador::getVictorias).reversed());
       jugadores.forEach(System.out::println);
            }
    
    
    
//  Indica el alias del jugador elegido y verifica que no se elija el mismo jugador dos veces.    
    public static String[] elegirJugadores(){
        clearConsole();
        int index;
        String[] jugadoresElegidos = new String[2];
        Scanner in = new Scanner(System.in);
        int i = 1;
        
//      Imprime la lista de jugadores junto a su posicion en la tabla        
        for(Jugador jugador : jugadores){
            System.out.println(i+". "+jugador.getNombre());
            i++;
        }
        

//      Pide el numero de la lista para facilitar la seleccion.         PLAYER 1 
        while(true){
        try{
        System.out.println("Indique el numero del jugador deseado"+Colors.RED +" (P1)"+Colors.RESET);
        index = in.nextInt()-1;
        
        jugadoresElegidos[0] = jugadores.get(index).getAlias();
        
        break;
        }
        catch (Exception e ){
            System.out.println(Colors.RED+"Indique un numero valido."+Colors.RESET);
            in.nextLine();
        }
        }
        
//      Pide el numero de la lista para facilitar la seleccion.         PLAYER 2
        while(true){
        try{
        System.out.println("Indique el numero del jugador deseado"+Colors.BLUE +" (P2)"+Colors.RESET);
        index = in.nextInt()-1;
        if(!jugadores.get(index).getAlias().equals(jugadoresElegidos[0])){
        jugadoresElegidos[1] = jugadores.get(index).getAlias();
        clearConsole();
        return jugadoresElegidos;
        } else {
            throw new Exception ("Jugador repetido");
        }
        
        
        }
        catch (Exception e ){
            System.out.println("Indique un numero valido o jugador diferente.");
            in.nextLine();
        }
        }
        
    }
    
    
// Agrega las victorias al jugador correspondiente.    
    public static void agregarVictorias(String alias) {
        
        for(Jugador jugador: jugadores){
            if(jugador.getAlias().equals(alias)){
                jugador.addVictorias();
            }
        }
        
    }
    
    

//          --------------------
//          | IMPRIMIR TABLERO |
//          --------------------
    
    
      public static void imprimirTablero(char[][] tablero, int[] seleccion, int[][] testigo){
        
        
        // Imprime los numeritos de arriba para identificar las columnas. 
        System.out.print("      1           2           3");
        System.out.println();
        
        // separadorImpreso lleva un conteo de cuantos separadores horizontales (****) existen.
        
      int separadorImpreso = 0;
      boolean primer = false;
      int[] cuadrante = seleccionCuadrante(seleccion);
      
      // filaImpresa es utilizado para colocar los separadores individuales (-+-+-+-+-)
      // cuadroImpresoV indica cuantos tableros impresos existen, despues es utilizado para ver el estado de ese cuadro.
      int filaImpresa =0;
      int cuadroImpresoV = 0;
      String divisor = "- + - + - ";
      
      // Primer separador
      imprimirSeparador(cuadrante,separadorImpreso);
      separadorImpreso++;
      System.out.println();
      
      // Comienza por las filas del tablero original 9x9
      for(int i = 0; i < tablero.length; i ++){
          // astkImpreso Lleva una cuenta de los asteriscos impresos por fila para identificar si deben ser pintados de amarillo o no.
          // cuadroImpresoH indica que tablero fue impreso en la fila horizontal.
          int astkImpreso = 0;
          int cuadroImpresoH = 0;
          
          // Cada 3 filas, se reinicia el conteo y se imprime un separador horizontal.
          if(filaImpresa== 3){
              cuadroImpresoV++;
              imprimirSeparador(cuadrante,separadorImpreso);
              separadorImpreso++;
              System.out.println();
              filaImpresa=0;
              
          // Si no es la tercer fila, y se ingreso la primer fila, se ingresa el separador individual.
          } else { if(primer){
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso,false);
              astkImpreso++;
              // Utiliza la matriz testigo segun la fila y la columna respectiva para indicar su color segun el estado del tablero.

              
              
              
              colorearString(divisor, testigo[cuadroImpresoV][0]);
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso,false);
              astkImpreso++;
              
              colorearString(divisor, testigo[cuadroImpresoV][1]);
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso,false);
              astkImpreso++;
              
              colorearString(divisor, testigo[cuadroImpresoV][2]);
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso,false);
              astkImpreso++;
              
              System.out.println();
              astkImpreso=0;
          }
          }
          // elemImpreso funciona para que cuando no sea el tercer elemento (termine una fila de 3) coloque un * en ves del |.
           int elemImpreso =0;
          imprimirAstk(cuadrante,cuadroImpresoV,astkImpreso,false);
          astkImpreso++;
          
          // Comienza a evaluar cada columna del tablero.
          for(int j = 0; j<tablero.length; j++){
             
              // Evalua el color segun el estado y elemento.
              colorearString((tablero[i][j]+" "), testigo[cuadroImpresoV][cuadroImpresoH]);
              elemImpreso++;
              
              
              if(elemImpreso==3){
                  imprimirAstk(cuadrante,cuadroImpresoV,astkImpreso,false);
                  astkImpreso++;
                  cuadroImpresoH++;
                  elemImpreso=0;
              } else {
                  colorearString("| ",testigo[cuadroImpresoV][cuadroImpresoH]);
              }
              
              
          }
          filaImpresa++;
          // Imprime si la fila es A B o C para facilitar la eleccion.
          if(filaImpresa == 2){
              switch (separadorImpreso){
                  case 1 -> System.out.print("A");
                  case 2 -> System.out.print("B");
                  case 3 -> System.out.print("C");
              }
              
          }
          System.out.println();
          primer = true;
          
          

      }
       // Ultimo separador. 
        imprimirSeparador(cuadrante, separadorImpreso);
        
        
        
    }
    
    
    

   public static void colorearString(String caracter, int status){
        if(caracter.equals('\u0000'+" ")){
            caracter = "  ";
        }

    // colorearString es una funcion que recibe un string y el status del cuadrante dado por la matrizTestigo.
      
       switch (status){
           // Ganador X
           case 1 -> {
                System.out.print(Colors.RED+caracter+Colors.RESET);
           }
           // Ganador O
           case 2 ->{
               System.out.print(Colors.BLUE+caracter+Colors.RESET);
           }
           // Empate o sin jugadas posibles.
           case 3 -> {
               System.out.print(Colors.BLACK+caracter+Colors.RESET);
           }
           // Jugadas posibles.
           case 0 -> {
               if(caracter.equals("X ")){
                   System.out.print(Colors.RED+caracter+Colors.RESET);
               } else {
                   if(caracter.equals("O ")){
                   System.out.print(Colors.BLUE+caracter+Colors.RESET);
                   } else {
                       System.out.print(caracter);
                   }
               }
           }
       }
       } 
   
    
   public static void astkAuxiliar (int columna, int astkImpreso){
       
       // Segun la columna valida el color sobre que asterisco haya sido impreso.
       
        switch (columna){
                case 1 -> {
                    if(astkImpreso == 0 || astkImpreso == 1){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                case 2 -> {
                    if(astkImpreso == 1 || astkImpreso == 2){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                case 3 -> {
                    if(astkImpreso == 2 || astkImpreso == 3){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                default -> {
                        colorAstk(0);
                }
            }

} 
    public static void imprimirAstk(int[] cuadrante, int cuadroImpreso, int astkImpreso, boolean esSeparador){
        // Valida si el cuadro impreso, segun el cuadrante seleccionado. 
        // Si es un separador, utiliza ambos cuadros posibles.
        switch (cuadrante[0]){
                case 1 -> {
                    if(cuadroImpreso == 0 || (cuadroImpreso == 1&& esSeparador)){
                        astkAuxiliar(cuadrante[1], astkImpreso);
                    } else {
                        colorAstk(0);
                    }
                }
                case 2 -> {
                    if(cuadroImpreso == 1 || (cuadroImpreso == 2&& esSeparador)){
                        astkAuxiliar(cuadrante[1], astkImpreso);
                    } else {
                        colorAstk(0);
                    }
                }
                case 3 -> {
                    if(cuadroImpreso == 2|| (cuadroImpreso == 3&& esSeparador)){
                       astkAuxiliar(cuadrante[1], astkImpreso);
                    } else {
                        colorAstk(0);
                    }
                }
                default -> {
                        colorAstk(0);
                }
            }
    }
    
    
    public static void colorAstk(int numero){
        // Recibe un valor y segun el valor lo pinta.
        if(numero == 0){
            System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
        } else {
            System.out.print(Colors.YELLOW_BACKGROUND+"* "+Colors.RESET);
        }
    }

    
    public static void imprimirSeparador(int[] cuadrante ,int separadorImpreso){
        String separador="* * * * * ";
        int astkImpreso =0;
        
        // El separador para que qeude bien, se debe tomar como 5 astericos juntos y 2 vertices, ya que estos ultimos son compartidos entre los 3 cuadrantes. 
        
        for(int i = 1; i<=3; i++){
            if(cuadrante[1] == i){
                imprimirAstk(cuadrante,separadorImpreso,astkImpreso, true);
                astkImpreso++;
                switch (cuadrante[0]) {
                    case 1 -> {
                        
                        if(separadorImpreso== 0 || separadorImpreso == 1){
                            System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
                        }else{
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                    
                        }
                    } 
                    case 2 -> {
                    
                        if(separadorImpreso== 1 || separadorImpreso == 2){
                                                    
                            System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
                        }else{
                                                
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                        }
                    } 
                    case 3 -> {
                        if(separadorImpreso== 2 || separadorImpreso == 3){
                                                  
                            System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
                        }else{
                                                
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                        }
                    }                     
                }
                imprimirAstk(cuadrante,separadorImpreso,astkImpreso, true);
                        astkImpreso++;
                
                     
                
            } else{                
                imprimirAstk(cuadrante,separadorImpreso,astkImpreso,true);
                        astkImpreso++;
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                            
                        }
        }


    }
    
    // INDICA CUAL DE LOS 9 CUADRANTES ES.
       public static int[] seleccionCuadrante(int[] cuadrante){
        
        int[] seleccion = new int[2];
        
        switch (cuadrante[0]){
            case 0 -> {
                seleccion[0] = 1; 
            }
            case 3 -> {
                seleccion[0] = 2; 
            }
            case 6 -> {
                seleccion[0] = 3;
            }
        }
        switch (cuadrante[1]){
            case 0 -> {
                seleccion[1] = 1; 
            }
            case 3 -> {
                seleccion[1] = 2; 
            }
            case 6 -> {
                seleccion[1] = 3;
            }
            
        }
        
        return seleccion;
        
    }






    
}
