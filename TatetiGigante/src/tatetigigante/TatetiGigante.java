
package tatetigigante;


import java.util.Scanner;
import tatetigigante.Colors;
import static tatetigigante.Sistema.clearConsole;

public class TatetiGigante {
    // TRYCATCH EVERYTHING
    
    public static void bienvenida() throws InterruptedException {
String[] banner = {
    "  _______           _______             _______          ",
    " |__   __|         |__   __|           |__   __|         ",
    "    | |   _  _  _     | | ____   _  _     | | ___   ___  ",
    "    | |  | || |/ /    | |/  _ \\ | |/ /    | |/ _ \\ / _ \\ ",
    "    | |  | ||   /     | || |_| || | /     | | (_) |  __/ ",
    "    |_|  |_||_|\\_\\    |_||_| |_||_|\\_\\    |_|\\___/ \\___| ",
    "                                                      "
};

        for (String line : banner) {
            System.out.println(line);
            Thread.sleep(100);  // 300 ms delay between each line
        }
        System.out.println(Colors.GREEN+"\nBIENVENIDO AL TATETI GIGANTE"+Colors.RESET);
    }

    public static void main(String[] args)throws InterruptedException {
        
       bienvenida();
        
       ;
       
       

       
       startMenu();
       
      
      
       
    }
    

    


    
    
    
        public static void startMenu(){
        
       Scanner in = new Scanner(System.in);
        int choice;

        do {
            // Opciones menu principal
            System.out.println(Colors.GREEN+"=== Menu Principal ==="+Colors.RESET);
            System.out.println(Colors.PURPLE+"1. Registrar jugador");
            System.out.println("2. Jugar entre 2 jugadores"+Colors.RESET);
            System.out.println(Colors.PURPLE+"3. Vs. Maquina");
            System.out.println("4. Ranking"+Colors.RESET);
            System.out.println(Colors.RED+"0. Salir"+Colors.RESET);
            System.out.print("Indique su opcion: ");

            // Get user input
            choice = in.nextInt();

            // Process the user's choice
            switch (choice) {
                case 1-> {
                    System.out.println("Registrar a un jugador");
                    Sistema.registroJugador();
                }
                 case 2-> {
                    clearConsole();
                    System.out.println("Jugando juego");
                    tatetigigante.Juego.jugar();
                }
                  case 3-> {
                    System.out.println("Contra la maquina");
                    tatetigigante.Juego.jugarMaquina();
                }
                   case 4-> {
                    System.out.println("Ranking");
                    Sistema.imprimirRanking();
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
