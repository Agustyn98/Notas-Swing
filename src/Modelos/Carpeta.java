package Modelos;

public class Carpeta {

	public int id;
	public String nombre;
	
	public Carpeta(int id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	
	public Carpeta() {
		
	}
	
	public Carpeta(String nombre){
		this.nombre = nombre;
	}
	
    @Override
    public String toString() {
        return this.nombre;
    }
	
}
