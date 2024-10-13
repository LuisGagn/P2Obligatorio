
package tatetigigante;

import java.util.Arrays;
import java.util.Scanner;
import tatetigigante.Colors;
import tatetigigante.imprimirTablero;
import tatetigigante.juego;

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
        System.out.println("\nINSERTAR MENU INTERACTIVO DSPS");
    }

    public static void main(String[] args)throws InterruptedException {
        
       bienvenida();
        
       ;
       
       
      
       String[] jugadores = {"Luis", "Juan"};
       

       
       menu.startMenu(jugadores);

      
       
    }
    

    


    
    
    
    
    
    
    
    
    
    
    
    

}
