
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
       
       
       
        Scanner in = new Scanner(System.in);
        
        // Matriz con todas las casillas.
        char[][] tablero =new char[9][9];
        
        // Matriz con el estado actual de cada TaTeTi 
        // 0 -- Sin Resultado
        // 1 -- Jugador X gano la casilla
        // 2 -- Jugador O gano la casilla
        // 3 -- No mas movimientos posibles
        int[][] matrizTestigo = new int[3][3];
        
        // Lleva un conteo de cantidad de jugadas, si llega a 9 y la matriz testigo es val 0, cambia a 3 = no se puede jugar mas.
        int[][] cantJugadasTateti = new int [3][3];
     

        
       
       String[] jugadores = {"Luis", "Juan"};
       
       int turno = 0;
       
       menu.startMenu(tablero,matrizTestigo,cantJugadasTateti,turno, jugadores);

      
       
    }
    

    


    
    
    
    
    
    
    
    
    
    
    
    

}
