
package tatetigigante;

import java.util.Scanner;

public class sistema {
    
        
    public static int estadoMatriz(char[][] tablero, int[] tateti){
        int ganador = 0;
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
        
        return ganador;

        
    }
    
    // Si hay ganador o no
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
    
    public static int[][] estadoTestigo(int[][] matrizTestigo, char[][] tablero, int[] tateti){
        

        int ganador = estadoMatriz(tablero,tateti);

        int filaT = tateti[0] / 3;
        int colT = tateti[1] / 3;

        matrizTestigo[filaT][colT] = ganador;
        
        return matrizTestigo;
    }

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
    
    // Devuelve la casilla principal en forma de array donde indica la fila
    // y la columna en la que comienza el tateti pequeño.
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

    public static char[][] jugadas (char[][] tablero, int[] jugada, int turno){
        
        Scanner in = new Scanner(System.in);
        
        // Valores  Par --> turno 1 || Impar --> turno 2
        char valor ='X';
        if(turno %2 != 0){
            valor = 'O';
        }        
        
        tablero[jugada[0]][jugada[1]] = valor;
        return tablero;
    }
    
   
    public static String validador(String palabra){
        
        Scanner in = new Scanner(System.in);
        palabra = palabra.toUpperCase();
        
        if (palabra.indexOf('Q')==-1 || palabra.length()>2){
        boolean posicionIncorrecta = false;
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
        
       
        if(posicionIncorrecta) {
             System.out.println("Vuelva a ingresar una posicion valida");
            palabra = in.nextLine();
            return validador(palabra);
        }
        } else {
            palabra +=" ";
        }
        
        return palabra;
    }
    
    public static boolean match(char a, char b, char c){
            return a == b && b == c && (a == 'X' || a == 'O' || a == '1' || a == '2');
        }
    
    
    
    
    
    public static boolean terminar(String movimiento){
        
        
        if (movimiento.indexOf('Q')!=-1){
            return true;
        } else {
            return false;
        }
        
    }
    
    public static void clearConsole() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
}

    
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
    
    
    

    
}
