
package tatetigigante;

import java.util.ArrayList;



public class Jugador{
    
    private String nombre;
    private int edad;
    private String alias;
    private int victorias;
    private String victoriasNum;
    
    
    private static ArrayList<String> aliasRegistrados = new ArrayList<>();
    
    
    // Constructor
    
    public Jugador(String nombre, int edad, String alias, int victorias){
        this.nombre = nombre;
        this.edad = edad;
        this.victorias = victorias;
        this.victoriasNum = "";
        this.alias = alias;
        aliasRegistrados.add(alias);
        
        
        
    }
    
    public static boolean aliasRegistrado(String alias){
        
        if(aliasRegistrados.contains(alias)){
            return false;
        } else {
            return true;
        }
        
    }
    
    
    
    
    public String getNombre(){
        return nombre;
    }
    
    public String getAlias(){
        return alias;
    }
    
    public int getVictorias(){
        return victorias;
    }
    
    public void addVictorias(){
        this.victorias++;
        this.victoriasNum += "#";
    }
    
    
    
    
    @Override
    public String toString(){
        return nombre + "  |  " +victoriasNum;
    }
    
    
    
    
    
    
    
    
}
