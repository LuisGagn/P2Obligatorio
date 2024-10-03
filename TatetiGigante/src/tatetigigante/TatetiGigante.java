
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
       
        matrizTestigo[1][0]=1;
        matrizTestigo[2][1]=2;
        matrizTestigo[1][1]=3;
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
              // EVALUAR FOR 0-2
              // //////////////////////////////////////////////////////////
              
              
              
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
