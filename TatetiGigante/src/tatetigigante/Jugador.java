
package tatetigigante;

import java.util.HashSet;

public class Jugador{
    
    private String nombre;
    private int edad;
    private String alias;
    private int victorias;
    
    
    private static HashSet<String> aliasRegistrados = new HashSet<>();
    
    
    // Constructor
    
    public Jugador(String nombre, int edad, String alias, int victorias) throws Exception{
        this.nombre = nombre;
        this.edad = edad;
        this.victorias = victorias;
        if(aliasRegistrados.contains(alias)){
            throw new Exception("Alias en uso, ingrese otro");
        } else {
            this.alias = alias;
            aliasRegistrados.add(alias);
        }
        
        
    }
    
    public String getAlias(){
        return alias;
    }
    
    public int getVictorias(){
        return victorias;
    }
    
    public void addVictorias(){
        this.victorias++;
    }
    
    
    @Override
    public String toString(){
        return "Nombre: "+ nombre + "Victorias:" +victorias;
    }
    
    
    
    
    
    
    
    
}
