
package tatetigigante;

import java.util.Arrays;
import java.util.Scanner;
import tatetigigante.Colors;
import tatetigigante.imprimirTablero;

public class TatetiGigante {
    // TRYCATCH EVERYTHING
    
    public static void bienvenida() throws InterruptedException {
String[] banner = {
    "  _______           _______             _______          ",
    " |__   __|         |__   __|           |__   __|         ",
    "    | |   _  _  _     | | ____   _  _     | | ___   ___  ",
    "    | |  | || |/ /    | |/  _ \\ | |/ /    | |/ _ \\ / _ \\ ",
    "    | |  | ||   /     | || |_| || | /     | | (_) |  __/ ",
    "    |_|  |_||_|\\_\\    |_||_| |_||_|\\_\\    |_|\\___/ \\___| ",
    "                                                      "
};

        for (String line : banner) {
            System.out.println(line);
            Thread.sleep(300);  // 300 ms delay between each line
        }
        System.out.println("\nWelcome to the Program!");
    }

    public static void main(String[] args)throws InterruptedException {
        
        bienvenida();
        
        Scanner in = new Scanner(System.in);
        
        // Matriz con todas las casillas.
        char[][] tablero =new char[9][9];
        
        // Matriz con el estado actual de cada TaTeTi 
        // 0 -- Sin Resultado
        // 1 -- Jugador X gano la casilla
        // 2 -- Jugador O gano la casilla
        // 3 -- No mas movimientos posibles
        int[][] matrizTestigo = new int[3][3];
        
        // Lleva un conteo de cantidad de jugadas, si llega a 9 y la matriz testigo es val 0, cambia a 3 = no se puede jugar mas.
        int[][] cantJugadasTateti = new int [3][3];
     
        // Selecciona tateti inicial y su jugada.
    //    int[] tateti = seleccionTablero();
        
      //  int[] jugada = seleccionJugada(tateti);
        
        
        
       
       
       int jugador = 0;
        
       Juego(tablero,matrizTestigo,cantJugadasTateti,jugador);
       
       
     //  tablero = jugadas(tablero,jugada, jugador);
        
       //imprimirTablero.imprimirConsola(tablero,tateti, matrizTestigo);
       
       //matrizTestigo = estadoMatriz(matrizTestigo, tablero, tateti);
       
      
       
    }
    
    
    
    public static String Juego(char[][] tablero, int[][] matrizTestigo, int[][] cantJugadasTateti, int jugador){
        
        Scanner in = new Scanner(System.in);
        
        boolean enJuego = true;
        boolean primerMov = false;
        
        
        while(enJuego) {
            
            
            if(!primerMov) {
                System.out.println("Indique el tateti inicial");
                int[] tateti = seleccionTablero();
                imprimirTablero.imprimirConsola(tablero, tateti, matrizTestigo);
                System.out.println("Indique su jugada");
                int[] jugada = seleccionJugada(tateti);
                tablero = jugadas(tablero,jugada, jugador);
                imprimirTablero.imprimirConsola(tablero, tateti, matrizTestigo);
            }
            
            enJuego = false;
            
            
            
            
            
            
            
            
        }
        
        
        
        return "Ganador X";
    }
    
    
    public static boolean match(char a, char b, char c){
            return a == b && b == c && a != ' ';
        }
    

    

    
    // ACTUALIZA EL ESTADO DE LA MATRIZ PARA EL TATETI JUGADO
    // ACTUALIZAR PARA VER GANADOR
    public static int[][] estadoMatriz(int[][] matrizTestigo, char[][] tablero, int[] tateti){
        

        int ganador = -1;
        int fila = tateti[0];
        int col = tateti[1];
        boolean encontrado = false;
        
        
        // DIAGONALES
        if(match(tablero[fila][col], tablero[fila+1][col+1], tablero[fila+2][col+2]) ||
           match(tablero[fila][col+2], tablero[fila+1][col+1], tablero[fila+2][col])) {
            ganador = (tablero[fila+1][col+1] == 'X') ? 1 : 2;
        }
        
        // HORIZONTALES
        for (int i = 0; i < 3 && !encontrado; i++) {
            if (match(tablero[fila+i][col], tablero[fila+i][col+1], tablero[fila+i][col+2])) {
             ganador = (tablero[fila+i][col] == 'X') ? 1 : 2;
            }
        }
        
        // VERTICALES
        for (int j = 0; j < 3 && !encontrado; j++) {
            if (match(tablero[fila][col+j], tablero[fila+1][col+j], tablero[fila+2][col+j])) {
                ganador = (tablero[fila][col+j] == 'X') ? 1 : 2;
            }
        }
        
        
        int filaT = tateti[0] / 3;
        int colT = tateti[1] / 3;
        
        
        matrizTestigo[filaT][colT] = ganador;
        
        return matrizTestigo;
        
    }



    
    // AGREGAR VALIDACION DE CASILLA OCUPADA POR OTRA
    
    public static int[] seleccionJugada(int[] tateti){
        
        Scanner in = new Scanner(System.in);
        
        String jugada = in.nextLine();
        
        int[] casilla = posJugada(tateti, jugada);
        
        if(casilla[0] == -1 || casilla[1] == -1){
            System.out.println("Casilla invalida, pruebe nuevamente");
            return seleccionJugada(tateti);
        }
        return casilla;
    }
    
    
    
    
    // Verifica la posicion que sea valida
    // AGREGAR CASILLA OCUPADA
      public static int[] posJugada (int[] posInicial, String casilla){
        
        int[] concat = new int[2];
        
        // 0,0 | 0,3 | 0,6 
        // 3,0 | 3,3 | 3,6
        // 6,0 | 6,3 | 6,6
        
        concat[0] = switch (casilla.charAt(0)) {
            case 'A' -> posInicial[0];
            case 'B' -> posInicial[0]+1;
            case 'C' -> posInicial[0]+2;
            default -> -1;
        };   
        
        concat[1] = switch (casilla.charAt(1)) {
            case '1' -> posInicial[1];
            case '2' -> posInicial[1]+1;
            case '3' -> posInicial[1]+2;
            default -> -1;
        };   
        
        return concat;
        
    }
    
      
      // AGREGAR TABLERO COMPLETO
    // Indica la posicion inicial del tateti y evita tableros invalidos.
    public static int[] seleccionTablero (){
        Scanner in = new Scanner(System.in);
        String seleccionTablero = in.nextLine();
        int[] tatetiIni = posicionInicial(seleccionTablero); 
        
        if(tatetiIni[0] == -1 || tatetiIni[1] == -1){
            System.out.println("Tablero Invalido, intente nuevamente.");
            return seleccionTablero();
            
        }
        
        
        return tatetiIni;
    }
    
    
    // Devuelve la casilla principal en forma de array donde indica la fila
    // y la columna en la que comienza el tateti pequeño.
    public static int[] posicionInicial(String casilla){
        
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
        
        // Verificar que posicion existe.
        
        return concat;
        
        
    }
    
    
    // TURNOS IGNORAR POR AHORA
    public static char[][] jugadas (char[][] tablero, int[] jugada, int jugador){
        
        // Valores 0 --> inf || Par --> Jugador 1 || Impar --> Jugador 2
        char valor ='X';
        if(jugador %2 != 0){
            valor = 'O';
        }        
        
        jugador++;
        tablero[jugada[0]][jugada[1]] = valor;
        
        return tablero;
    }
    
    
    
    
    
    
    
    
    
    
    

}
