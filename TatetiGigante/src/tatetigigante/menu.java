
package tatetigigante;

import java.util.Scanner;
import static tatetigigante.sistema.clearConsole;


public class menu {
    
    
    
    public static void startMenu(String[] jugadores){
        
       Scanner in = new Scanner(System.in);
        int choice;

        do {
            // Opciones menu principal
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Jugar entre 2 jugadores");
            System.out.println("3. Vs. Maquina");
            System.out.println("4. Ranking");
            System.out.println("0. Salir");
            System.out.print("Indique su opcion: ");

            // Get user input
            choice = in.nextInt();

            // Process the user's choice
            switch (choice) {
                case 1-> {
                    System.out.println("Registrar a un jugador");
                    sistema.registroJugador();
                }
                 case 2-> {
                    clearConsole();
                    System.out.println("Jugando juego");
                    tatetigigante.juego.jugar();
                }
                  case 3-> {
                    System.out.println("Contra la maquina");
                    tatetigigante.contraMaquina.jugar();
                }
                   case 4-> {
                    System.out.println("Ranking");
                    sistema.imprimirRanking();
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

        in.close();
        
  
        
    }
    
    
    
}
