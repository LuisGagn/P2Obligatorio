
package tatetigigante;

import java.util.Arrays;
import java.util.Scanner;
import tatetigigante.Colors;
import tatetigigante.imprimirTablero;

public class TatetiGigante {


    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        // Matriz con todas las casillas.
        char[][] tablero =new char[9][9];
        
        // Matriz con el estado actual de cada TaTeTi 
        // 0 -- Sin Resultado
        // 1 -- Jugador X gano la casilla
        // 2 -- Jugador O gano la casilla
        // 3 -- No mas movimientos posibles
        int[][] matrizTestigo = new int[3][3];

        // Selecciona tateti inicial y su jugada.
        int[] tateti = seleccionTablero();
        
        int[] jugada = seleccionJugada(tateti);
        
        
       
       
       int jugador = 0;
       
       tablero = jugadas(tablero,jugada, jugador);
        
       imprimirTablero.imprimirConsola(tablero,tateti, matrizTestigo);
        
       
    }
    

    public static int[][] estadoJugada(int[][] matrizTestigo, int[][] tablero, int[] jugada, int[] tateti){
        
        
        int cantX = 0;
        int cantO = 0;
        int ganador = -1;
        int fila = tateti[0];
        int col = tateti[1];
        
        for(int i = fila; i < fila+3; i++){
            if(tablero[0][i]==1){
                cantX++;
            }
        }
        
        
    }


    
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
