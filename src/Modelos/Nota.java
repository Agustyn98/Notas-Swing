package Modelos;

public class Nota {
	public int id;
	public String texto;
	public String fechaModificacion;
	public int idCarpeta;
	
	public Nota() {
		
	}
	
	public Nota(int id, String texto, String fechaModificacion, int idCarpeta) {
		this.id = id;
		this.texto = texto;
		this.fechaModificacion = fechaModificacion;
		this.idCarpeta = idCarpeta;
	}
	
	public Nota(String texto, String fechaModificacion, int idCarpeta) {
		this.texto = texto;
		this.fechaModificacion = fechaModificacion;
		this.idCarpeta = idCarpeta;
	}
	
    @Override
    public String toString() {
    	
    	String titulo = texto;
    	int lenght = titulo.length();
    	
    	for(int i = 0 ; i < lenght ; i++) {
    		if(titulo.charAt(i) == '\r' || titulo.charAt(i) == '\n') {
    			titulo = texto.substring(0, i);
    			break;
    		}
    	}
		if(titulo.length() >= 40)
			titulo = titulo.substring(0, 40) + "...";
        return titulo;
        
    }
	
}
