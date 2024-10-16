
package tatetigigante;

import java.util.Scanner;



// AGREGAR EMPATE

public class ContraMaquina extends Sistema{
       
  
    public static void jugar(){
        int turno = 0;
        char[][] tablero =new char[9][9];
        int[][] matrizTestigo = new int[3][3];
        int[][] cantJugadasTateti = new int [3][3];
        
        Scanner in = new Scanner(System.in);
        String winner = "";
        boolean enJuego = true;
        boolean terminarJuego = false;
        int[] jugada = new int[2];
        int[] posicion = new int[2];




//          -------------------------        
//          |   PRIMER MOVIMIENTO   |
//          -------------------------     
                clearConsole();
                System.out.println("Indique el tateti inicial");
                int[] tateti = seleccionTablero(validador(in.nextLine()));
                imprimirTablero(tablero, tateti, matrizTestigo);
                
                // Jugada
                System.out.println("\nIndique su jugada");
                
                String movimiento = validador(in.nextLine());
                terminarJuego = terminar(movimiento);

                if(!terminarJuego){
                jugada = seleccionJugada(tateti, movimiento);
                tablero = jugadas(tablero,jugada, turno);

                // Nuevo TaTeTi
                tateti =seleccionTablero(movimiento);
                }
                
                
                
//          --------------------------                       
//          | SIGUIENTES MOVIMIENTOS |
//          --------------------------          


        while(enJuego && !terminarJuego) {
            turno++;
            cantJugadasTateti[tateti[0]/3][tateti[1]/3]++;
            
            
//          -----------------------
//          | MOVIMIENTOS JUGADOR |
//          -----------------------

            if(turno%2==0){
                
//          SI EL TABLERO ESTA LLENO PARA EL JUGADOR
                while(cantJugadasTateti[tateti[0]/3][tateti[1]/3]>=9){
                imprimirTablero(tablero, tateti, matrizTestigo);
                System.out.println("El tablero esta completo, elija otro:");
                movimiento= validador(in.nextLine());
                tateti= seleccionTablero(movimiento);
            }
                
            clearConsole();    
            imprimirTablero(tablero, tateti, matrizTestigo);
            System.out.println("\nLa maquina realizo la jugada: " + movimiento);
            System.out.println("\nIndique su jugada o 'X' para salir");
            movimiento = validador(in.nextLine());
            terminarJuego = terminar(movimiento);
            if(!terminarJuego){
            
            posicion = seleccionJugada(tateti, movimiento);
            
            
//          Verifica si la posicion es valida, si no, elige otra.
            while(tablero[posicion[0]][posicion[1]]=='X' || tablero[posicion[0]][posicion[1]]=='O'){
                System.out.println("Posicion invalida, vuelva a seleccionar una");
                movimiento = validador(in.nextLine());
                posicion = seleccionJugada(tateti, movimiento);
            }

            jugada = seleccionJugada(tateti,movimiento);
            }

            
            
            
//          -----------------------
//          | MOVIMIENTOS MAQUINA |
//          -----------------------

            } else {
              // EN CASO DE QUE EL TRABLERO ESTE LLENO
                if(cantJugadasTateti[tateti[0]/3][tateti[1]/3]>=9){
                    String nuevoTablero ="";
                    boolean stop = false;
                    for(int i = 0; i < 3 && !stop; i ++){
                        for(int j = 0; j < 3 &&!stop; j++){
                            if(cantJugadasTateti[i][j]<8){
                                nuevoTablero += switch (i){
                                    case 0 -> 'A';
                                    case 1 -> 'B';
                                    case 2 -> 'C';
                                    default -> '?';
                                };
                                
                                nuevoTablero+= j+1;
                                stop = true;
                            }
                        }
                    }

                    tateti = seleccionTablero(nuevoTablero);
                } 
                
                movimiento = maquinaCasilla(tablero,tateti);
                jugada = seleccionJugada(tateti,movimiento);
                
            }
            
            

            
//          ---------------------------------                       
//          | COMUN ENTRE JUGADOR Y MAQUINA |
//          ---------------------------------          

                tablero = jugadas(tablero,jugada, turno);
                matrizTestigo = estadoTestigo(matrizTestigo,tablero,tateti);
                tateti=seleccionTablero(movimiento);
                
                
//      Tablero empatado.
                if(cantJugadasTateti[tateti[0]/3][tateti[1]/3]==9 && 
                        matrizTestigo[tateti[0]/3][tateti[1]/3] == 0)
                {
                    matrizTestigo[tateti[0]/3][tateti[1]/3]=3;
                }

         
      
            
            // Verifica si hay ganador
        int ganador = estadoJuego(matrizTestigo);
        
        if(ganador== 1){
            enJuego = false;
            winner = "Has perdido!! mejor suerte la proxima";
        } else {
            if(ganador ==2){
                enJuego = false;
                winner = "Felicitaciones Has ganado a la maquina";
            }
        }
         
        if(empate(matrizTestigo)){
            clearConsole();
            System.out.println("EMPATE");
            enJuego = false;
        }
        
        }
        
        
        if(!terminarJuego){
            clearConsole();
            imprimirTablero(tablero, tateti, matrizTestigo);
            System.out.println(winner);
        } else {
            clearConsole();
        }
        
    }
    
    
    
    
    
    

}
