
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

      int separadorImpreso = 0;
      boolean primer = false;
      int[] cuadrante = seleccionCuadrante(seleccion);
      int filaImpresa =0;
      int cuadroImpresoV = 0;
      String divisor = "- + - + - ";
      
      imprimirSeparador(cuadrante,separadorImpreso, cuadroImpresoV);
      separadorImpreso++;
      System.out.println();
      
      for(int i = 0; i < tablero.length; i ++){
          int astkImpreso = 0;
          int cuadroImpresoH = 0;
          
          
          if(filaImpresa== 3){
              cuadroImpresoV++;
              imprimirSeparador(cuadrante,separadorImpreso, cuadroImpresoV);
              separadorImpreso++;
              System.out.println();
              filaImpresa=0;
              
          } else { if(primer){
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso);
              astkImpreso++;
              System.out.print(divisor);
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso);
              astkImpreso++;
              System.out.print(divisor);
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso);
              astkImpreso++;
              System.out.print(divisor);
              imprimirAstk(cuadrante, cuadroImpresoV,astkImpreso);
              astkImpreso++;
              System.out.println();
              astkImpreso=0;
          }
          }
           int elemImpreso =0;
          imprimirAstk(cuadrante,cuadroImpresoV,astkImpreso);
          astkImpreso++;
          
          for(int j = 0; j<tablero.length; j++){
             
              
              System.out.print(tablero[i][j]+" ");
              elemImpreso++;
              
              
              if(elemImpreso==3){
                  imprimirAstk(cuadrante,cuadroImpresoV,astkImpreso);
                  astkImpreso++;
                  cuadroImpresoH++;
                  elemImpreso=0;
              } else {
                  System.out.print("| ");
              }
              
              
          }
          filaImpresa++;
          System.out.println();
          primer = true;
          
          

      }
        
        imprimirSeparador(cuadrante, separadorImpreso, cuadroImpresoV);
        
        
        
        
        
        
        
    }
    

    
   public static void test (int num, int indice){
        switch (num){
                case 1 -> {
                    if(indice == 0 || indice == 1){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                case 2 -> {
                    if(indice == 1 || indice == 2){
                        colorAstk(1);
                    } else {
                        colorAstk(0);
                    }
                }
                case 3 -> {
                    if(indice == 2 || indice == 3){
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
    public static void imprimirAstk(int[] num, int col, int indice){

        switch (num[0]){
                case 1 -> {
                    if(col == 0){
                        test(num[1], indice);
                    } else {
                        colorAstk(0);
                    }
                }
                case 2 -> {
                    if(col == 1){
                        test(num[1], indice);
                    } else {
                        colorAstk(0);
                    }
                }
                case 3 -> {
                    if(col == 2){
                       test(num[1], indice);
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
    // EL ERROR ES QUE TOMA UN SOLO COSO, VERIFICAR Y MODIFICAR APRA QUE TOME 2
    
    public static void imprimirSeparador(int[] numero ,int indice, int a){
        String separador="* * * * * ";
        int astkImpreso =0;
        for(int i = 1; i<=3; i++){
            if(numero[1] == i){
                imprimirAstk(numero,a,astkImpreso);
                astkImpreso++;
                switch (numero[0]) {
                    case 1 -> {
                        
                        if(indice== 0 || indice == 1){
                            System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
                        }else{
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                    
                        }
                    } 
                    case 2 -> {
                    
                        if(indice== 1 || indice == 2){
                                                    
                            System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
                        }else{
                                                
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                        }
                    } 
                    case 3 -> {
                        if(indice== 2 || indice == 3){
                                                  
                            System.out.print(Colors.YELLOW_BACKGROUND+separador+Colors.RESET);
                        }else{
                                                
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                        }
                    }                     
                }
                imprimirAstk(numero,a,astkImpreso);
                        astkImpreso++;
                
                     
                
            } else{                
                imprimirAstk(numero,a,astkImpreso);
                        astkImpreso++;
                            System.out.print(Colors.GREEN_BACKGROUND+separador+Colors.RESET);
                            
                        }
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
