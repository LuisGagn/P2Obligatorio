
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
        
        imprimirConsola(tablero,tateti, matrizTestigo);
        
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
    
    
    public static void imprimirConsola(char[][] tablero, int[] seleccion, int[][] testigo){

        int filaImpresa = 0;
        
        int[] cuadrante = seleccionCuadrante(seleccion);
        
       // 0 | 6  | 12 | 18 
        String divisor = " - + - + - ";   
        
        for(int i =0; i < tablero.length; i ++){
            
            // Imprime separadores horizontales
            if(filaImpresa%6==0){
            switch (cuadrante[0]){
                case 1 -> {
                    if(filaImpresa == 0 || filaImpresa == 6){
                        imprimirSeparador(cuadrante[1]);
                    } else {
                        imprimirSeparador(0);
                    }
                }
                case 2 -> {
                    if(filaImpresa == 6 || filaImpresa == 12){
                        imprimirSeparador(cuadrante[1]);
                    }else {
                        imprimirSeparador(0);
                    }
                }
                case 3 -> {
                    if(filaImpresa == 12 || filaImpresa == 18){
                        imprimirSeparador(cuadrante[1]);
                    }else {
                        imprimirSeparador(0);
                    }
                }
                default -> {
                        imprimirSeparador(0);
                }
            }
            System.out.println();
            filaImpresa++;
            }
            
            int colImpresa =0;
            int elemImpreso = 0;
            for(int j = 0; j<tablero.length; j++){
                if(colImpresa%6==0){
                switch (cuadrante[1]){
                    case 1 -> {
                        if(filaImpresa>0 && filaImpresa<6){
                            imprimirAstk(cuadrante[0], colImpresa);
                        } else {
                            imprimirAstk(0,0);
                        }
                    }
                    case 2 -> {
                        if(filaImpresa>6 && filaImpresa<12){
                            imprimirAstk(cuadrante[0], colImpresa);
                        }else {
                            imprimirAstk(0,0);
                        }
                    }
                    case 3 -> {
                        if(filaImpresa>12 && filaImpresa<18){
                            imprimirAstk(cuadrante[0], colImpresa);
                        }else {
                            imprimirAstk(0,0);
                        }
                    }
                    default -> {
                        imprimirAstk(0,0);
                         
                    }
                }
                  colImpresa++;
                }
                
             
               
              // Cambiar por funciones de coloreado
              
              System.out.print(" "+tablero[i][j]);
              colImpresa++;
              elemImpreso++;
              
              if(elemImpreso != 3){
              System.out.print("|");
              colImpresa++;
              } else{
                  elemImpreso=0;
              }
              
             
              
            }
            filaImpresa++;
            System.out.println();
            if(filaImpresa%6 !=0 ){
            imprimirAstk(0,0);
            System.out.print(" -+ -+ -");
            imprimirAstk(0,0);
            System.out.print(" -+ -+ -");
            imprimirAstk(0,0);
            System.out.print(" -+ -+ -");
            imprimirAstk(0,0);
            System.out.println();
            filaImpresa++;
            }
         
        }

        
        
        
        
        
        
    }
    
    
    public static void imprimirAstk(int num, int col){
            switch (num){
                case 1 -> {
                    if(col == 0 || col == 6){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                case 2 -> {
                    if(col == 6 || col == 12){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                case 3 -> {
                    if(col == 12 || col == 18){
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
    
    public static void colorAstk(int numero){
        if(numero == 0){
            System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
        } else {
            System.out.print(Colors.YELLOW_BACKGROUND+"* "+Colors.RESET);
        }
    }
    
    
    public static void imprimirSeparador(int numero){
        String separador="* * * * * * ";
        for(int i = 1; i<=3; i++){
            if(numero == i){
                System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
            } else{
                System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
            }
        }
        if(numero == 3){
            System.out.print(Colors.YELLOW_BACKGROUND+"* "+Colors.RESET);
        } else {
            System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
        }

    }
    
    // INDICA CUAL DE LOS 3 CUADRANTES ES.
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
