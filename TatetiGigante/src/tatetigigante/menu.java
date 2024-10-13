
package tatetigigante;

import java.util.Scanner;
import static tatetigigante.juego.sistema;


public class menu {
    
    
    
    public static void startMenu(char[][] tablero, int[][] matrizTestigo, int[][] cantJugadasTateti, int turno, String[] jugadores){
        
       Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Print the menu options
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Jugar entre 2 jugadores");
            System.out.println("3. Vs. Maquina");
            System.out.println("4. Ranking");
            System.out.println("0. Salir");
            System.out.print("Indique su opcion: ");

            // Get user input
            choice = scanner.nextInt();

            // Process the user's choice
            switch (choice) {
                case 1-> {
                    System.out.println("Registrar a un jugador");
                }
                 case 2-> {
                    System.out.println("Jugar juego");
                    tatetigigante.juego.sistema(tablero,matrizTestigo,cantJugadasTateti,turno,jugadores);
                }
                  case 3-> {
                    System.out.println("Contra la maquina");
                    tatetigigante.contraMaquina.sistema(tablero,matrizTestigo,cantJugadasTateti,turno);
                }
                   case 4-> {
                    System.out.println("Ranking");
                }
                    case 0-> {
                    System.out.println("Saliendo");
                }
                default->{
                    System.out.println("Opcion invalida, intente con otra.");
                }
            }

            // Add a line break for clarity
            System.out.println();

        } while (choice != 0); // Continue until the user chooses to exit

        scanner.close();
        
  
        
    }
    
    
    
}
