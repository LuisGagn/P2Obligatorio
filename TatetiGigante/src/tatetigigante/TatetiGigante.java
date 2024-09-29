
package tatetigigante;

import java.util.Arrays;
import java.util.Scanner;
import tatetigigante.Colors;

public class TatetiGigante {


    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        // Matriz con todas las casillas.
       // char[][] tablero = new char[9][9];
        
        char[][] tablero = {
            {'X', 'O', 'X', 'O', '-', '-', '-', 'X', 'O'},
            {'O', 'O', '-', '-', 'X', 'O', 'X', 'O', '-'},
            {'-', '-', 'X', '-', '-', 'X', '-', '-', 'O'},
            {'-', '-', 'O', '-', 'X', 'O', '-', 'X', 'O'},
            {'-', 'X', '-', 'O', '-', '-', 'X', '-', 'X'},
            {'X', '-', 'O', 'X', '-', '-', '-', 'X', 'O'},
            {'-', 'X', 'O', '-', 'X', '-', '-', 'O', '-'},
            {'-', '-', 'O', '-', 'O', 'X', '-', 'X', '-'},
            {'O', 'X', '-', 'O', '-', '-', '-', 'O', 'X'}
        };
        
        // Matriz con el estado actual de cada TaTeTi 
        // 0 -- Sin Resultado
        // 1 -- Jugador X gano la casilla
        // 2 -- Jugador O gano la casilla
        int[][] matrizTestigo = new int[3][3];
       

        
        // Selecciona tateti inicial.
        int[] tateti = seleccionTablero();
        
        int[] jugada = seleccionJugada(tateti);
        
        imprimirConsola(tablero,tateti);
        
        tablero = jugadas(tablero,jugada);
        
        
        
        
        
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
    
    public static char[][] jugadas (char[][] tablero, int[] jugada){
        
        // Valores 0 --> inf || Par --> Jugador 1 || Impar --> Jugador 2
        int turno = 0;
        char valor ='X';
        if(turno %2 != 0){
            valor = 'O';
        }        
        
        turno++;
        tablero[jugada[0]][jugada[1]] = valor;
        
        return tablero;
    }
    
    
    public static void imprimirConsola(char[][] tablero, int[] posicion){
        
        // 4 cols de *
        // 9 cols X , O 
        // 6 cols |
        
        // * En valores 0, 6, 12, 18 -- Filas
        // * En valores 0, 6, 12, 18 -- Cols
        int actualRow = 0;
        int actualCol = 0;
        
        Integer[] ast = {0, 6, 12, 18};
        int countF = 0;
        
        
        String separadorA = "* * * * * * ";
        String separadorB = "- + - + - ";
        
        
        // posicion[0] --> 0, 3 , 6 --> 0, 1 y 2 | 3, 2 y 3 | 6, 3 y 4. 
        // posicion[1] --> 0, 3 , 6 --> 0, 1 y 2 | 3, 2 y 3 | 6, 3 y 4. 
        
       String vert = "";
       int horz = 0;
       vert = switch (posicion[0]) {
            case 0 -> "12";
            case 3 -> "23";
            case 6 -> "34";
            default -> "";
        };   
        
       horz = switch (posicion[1]) {
            case 0 -> 1;
            case 3 -> 2;
            case 6 -> 3;
            default -> 0;
        };   
        
        
        
        int contadorHorizontal =1;

        
        for(int i =0; i < tablero.length; i++){
            
            // Separador de ****** 3 secciones
            if(i%3==0){
                for(int k = 1; k <=3; k++){
                if(vert.contains(Integer.toString(contadorHorizontal)) && horz == k){
                System.out.print(Colors.YELLOW_BACKGROUND+separadorA+Colors.RESET);

                }else{
                System.out.print(Colors.GREEN_BACKGROUND+separadorA+Colors.RESET);
                        }
                
                }
                contadorHorizontal++;
                System.out.println();
            }
            
            
            
            
            // FOR que imprima lo mismo 3 veces pero con color segun A B o C
            if(countF%3 != 0){
                
                if(horz == )
                
                System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET+"- + - + - * - + - + - * - + - + -"+Colors.GREEN_BACKGROUND+" *"+Colors.RESET);
                System.out.println();
            }

            int count = 1;
            
            for(int j = 0; j < tablero.length; j++){
                if(j%3 == 0){ 
                    System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
                    System.out.print(tablero[i][j]+" | ");
                }else{
                    if(count%3!=0){
                System.out.print(tablero[i][j]+" | ");
                }
                    else {
                        System.out.print(tablero[i][j]+" ");
                    }
                }
                
                if(j == tablero.length-1){
                    System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
                }
                
                count++;
            }
            
            
                        if(i==tablero.length-1){
                            System.out.println();
                System.out.print(Colors.GREEN_BACKGROUND+"* * * * * * * * * * * * * * * * * * *"+Colors.RESET);
                
            }
            
            System.out.println();
            countF++;
        }
        
        
       System.out.println("HORIZONTAL = " + vert+ " "+" "+ contadorHorizontal);
        
    } 
    
}
