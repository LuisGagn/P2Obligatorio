
package tatetigigante;

import java.util.Scanner;
import tatetigigante.sistema;

// MOVER COMPARTIDO ENTRE JUEGO Y MAQUINA A NUEVA CLASE -> SISTEMA


    // Agregar jugada maestra
    // q para volver al menu
    // mostrar jugador y turno

public class juego extends sistema{
       
  
    public static void jugar(char[][] tablero, int[][] matrizTestigo, int[][] cantJugadasTateti, int turno, String[] jugadores){
        
        Scanner in = new Scanner(System.in);
        String winner = "";
        boolean enJuego = true;
        
                // Primer comienzo del juego
                System.out.println("Indique el tateti inicial");
                int[] tateti = seleccionTablero(validador(in.nextLine()));
                
                imprimirTablero.imprimirConsola(tablero, tateti, matrizTestigo);
                System.out.println("\nIndique su jugada");
                
                String movimiento = validador(in.nextLine());
                
                int[] jugada = seleccionJugada(tateti, movimiento);
                tablero = jugadas(tablero,jugada, turno);
                cantJugadasTateti[tateti[0]/3][tateti[1]/3]++;
                
                tateti =seleccionTablero(movimiento);
                
                // Siguientes movimientos      
        while(enJuego) {
            
            turno++;
            imprimirTablero.imprimirConsola(tablero, tateti, matrizTestigo);
            
            System.out.println("\nIndique su jugada");
            movimiento = validador(in.nextLine());
      
            int[] posicion = seleccionJugada(tateti, movimiento);
            
            
            // Verifica si la posicion es valida, si no, elige otra.
            while(tablero[posicion[0]][posicion[1]]=='X' || tablero[posicion[0]][posicion[1]]=='O'){
                System.out.println("Posicion invalida, vuelva a seleccionar una");
                movimiento = validador(in.nextLine());
                posicion = seleccionJugada(tateti, movimiento);
            }
            
            
            
            
            jugada = seleccionJugada(tateti,movimiento);
            tablero = jugadas(tablero,jugada, turno);
            matrizTestigo = estadoTestigo(matrizTestigo, tablero, tateti);
            tateti = seleccionTablero(movimiento);
            
            cantJugadasTateti[tateti[0]/3][tateti[1]/3]++;
            
            // Verifica si el tablero esta lleno, de estarlo da la opcion de elegir otro al turno.
            while(cantJugadasTateti[tateti[0]/3][tateti[1]/3]>=9){
                imprimirTablero.imprimirConsola(tablero, tateti, matrizTestigo);
                System.out.println("El tablero esta completo, elija otro:");
                movimiento= validador(in.nextLine());
                tateti= seleccionTablero(movimiento);
            }
            
            
            // Tablero lleno
            if(cantJugadasTateti[tateti[0]/3][tateti[1]/3]==9 && 
                    matrizTestigo[tateti[0]/3][tateti[1]/3] == 0)
            {
                matrizTestigo[tateti[0]/3][tateti[1]/3]=3;
            }
            
            
            // Verifica si hay ganador
        int ganador = estadoJuego(matrizTestigo);
        
        if(ganador== 1){
            enJuego = false;
            winner = jugadores[0];
        } else {
            if(ganador ==2){
                enJuego = false;
                winner = jugadores[1];
            }
        }
            
        }
        
        
        imprimirTablero.imprimirConsola(tablero, tateti, matrizTestigo);
        System.out.println("Felicitaciones "+ winner);
    }
    
//    
//    public static int estadoMatriz(char[][] tablero, int[] tateti){
//        int ganador = 0;
//        int fila = tateti[0];
//        int col = tateti[1];
//        boolean encontrado = false;
//        
//        
//        // DIAGONALES
//        if(match(tablero[fila][col], tablero[fila+1][col+1], tablero[fila+2][col+2]) ||
//           match(tablero[fila][col+2], tablero[fila+1][col+1], tablero[fila+2][col])) {
//            ganador = (tablero[fila+1][col+1] == 'X') ? 1 : 2;
//        }
//        
//        // HORIZONTALES
//        for (int i = 0; i < 3 && !encontrado; i++) {
//            if (match(tablero[fila+i][col], tablero[fila+i][col+1], tablero[fila+i][col+2])) {
//             ganador = (tablero[fila+i][col] == 'X') ? 1 : 2;
//            }
//        }
//        
//        // VERTICALES
//        for (int j = 0; j < 3 && !encontrado; j++) {
//            if (match(tablero[fila][col+j], tablero[fila+1][col+j], tablero[fila+2][col+j])) {
//                ganador = (tablero[fila][col+j] == 'X') ? 1 : 2;
//            }
//        }
//        
//        return ganador;
//
//        
//    }
//    
//    // Si hay ganador o no
//    public static int estadoJuego(int[][] matrizTestigo){
//
//        char[][] charMatrix = new char[3][3];
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                charMatrix[i][j] = (char) (matrizTestigo[i][j]+'0');  
//            }
//        }
//        
//        int[] initial = {0,0};
//        
//        int ganador = estadoMatriz(charMatrix, initial);
//        return ganador;
//        
//    }
//    
//    public static int[][] estadoTestigo(int[][] matrizTestigo, char[][] tablero, int[] tateti){
//        
//
//        int ganador = estadoMatriz(tablero,tateti);
//
//        int filaT = tateti[0] / 3;
//        int colT = tateti[1] / 3;
//
//        matrizTestigo[filaT][colT] = ganador;
//        
//        return matrizTestigo;
//    }
//
//      public static int[] seleccionJugada (int[] tateti, String casilla){
//        
//        int[] concat = new int[2];
//        
//        // 0,0 | 0,3 | 0,6 
//        // 3,0 | 3,3 | 3,6
//        // 6,0 | 6,3 | 6,6
//        
//        concat[0] = switch (casilla.charAt(0)) {
//            case 'A' -> tateti[0];
//            case 'B' -> tateti[0]+1;
//            case 'C' -> tateti[0]+2;
//            default -> -1;
//        };   
//        
//        concat[1] = switch (casilla.charAt(1)) {
//            case '1' -> tateti[1];
//            case '2' -> tateti[1]+1;
//            case '3' -> tateti[1]+2;
//            default -> -1;
//        };   
//        
//        return concat;
//        
//    }
//    
//    // Devuelve la casilla principal en forma de array donde indica la fila
//    // y la columna en la que comienza el tateti pequeño.
//    public static int[] seleccionTablero(String casilla){
//        
//        int[] concat = new int[2];
//        
//        concat[0] = switch (casilla.charAt(0)) {
//            case 'A' -> 0;
//            case 'B' -> 3;
//            case 'C' -> 6;
//            default -> -1;
//        };   
//        
//        concat[1] = switch (casilla.charAt(1)) {
//            case '1' -> 0;
//            case '2' -> 3;
//            case '3' -> 6;
//            default -> -1;
//        };   
// 
//        return concat;
//    }
//
//    public static char[][] jugadas (char[][] tablero, int[] jugada, int turno){
//        
//        Scanner in = new Scanner(System.in);
//        
//        // Valores  Par --> turno 1 || Impar --> turno 2
//        char valor ='X';
//        if(turno %2 != 0){
//            valor = 'O';
//        }        
//        
//        tablero[jugada[0]][jugada[1]] = valor;
//        return tablero;
//    }
//    
//   
//    public static String validador(String palabra){
//        
//        Scanner in = new Scanner(System.in);
//        palabra = palabra.toUpperCase();
//        boolean posicionIncorrecta = false;
//        String letras = "ABC";
//        int num = Character.digit(palabra.charAt(1), 10);
//
//        if(palabra.length()!=2){
//            posicionIncorrecta = true;
//        } else {
//              if(letras.indexOf(palabra.charAt(0))==-1){
//                posicionIncorrecta = true;
//              }
//              if (num> 3 || num < 0){
//                  posicionIncorrecta = true;
//              }
//        }
//       
//        
//        if(posicionIncorrecta) {
//             System.out.println("Vuelva a ingresar una posicion valida");
//            palabra = in.nextLine();
//            return validador(palabra);
//        }
//        
//        
//        return palabra;
//    }
//    
//    public static boolean match(char a, char b, char c){
//            return a == b && b == c && (a == 'X' || a == 'O' || a == '1' || a == '2');
//        }
//    
//    
//    
//    
}
