package Database;

import java.sql.*;
import java.util.ArrayList;

import Modelos.Carpeta;
import Modelos.Nota;

public class ManejadorDB {
    private String url = "jdbc:sqlite:notas.db";  

    private static ManejadorDB instance = null;
    public static ManejadorDB getInstance() {
    	if(instance == null) {
    		instance = new ManejadorDB();
    	}
    	return instance;
    }
    
    public void crearTablas() {
        String query = "CREATE TABLE IF NOT EXISTS folders(id INTEGER PRIMARY KEY, name TEXT)";  
        String queryNota = "CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY, text TEXT, dateModified TEXT, idFolder INTEGER)";
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(query);  
            stmt.execute(queryNota);
            conn.close();
            
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }
    }
    
    public void agregarCarpeta(Carpeta carpeta) {
    	String query = "INSERT INTO folders(name) VALUES (?)";
        try{  
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.setString(1, carpeta.nombre);  
            pstmt.executeUpdate(); 
            conn.close();
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void editarCarpeta(int idCarpeta, String nombre) {
    	String query = "UPDATE folders SET name='" + nombre + "'WHERE id=" + idCarpeta;    	
    	try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
            
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
    }
    
    public void agregarNota(Nota nota) {
    	String query = "INSERT INTO notes(text,dateModified,idFolder) VALUES (?,?,?)";
        try{  
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.setString(1, nota.texto);  
            pstmt.setString(2, nota.fechaModificacion);  
            pstmt.setInt(3, nota.idCarpeta);  
            pstmt.executeUpdate(); 
            conn.close();
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void editarNota(int idNota, String texto) {
    	
    	String query = "UPDATE notes SET text='" + texto + "'WHERE id=" + idNota;
    	
    	try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
            
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
    }
    
	public ArrayList<Carpeta> seleccionarTodasCarpetas(int ordernarPor) {
		String query = "SELECT * FROM folders";
		switch(ordernarPor) {
		case 1:
			query = "SELECT * FROM folders ORDER BY id asc";
			break;
		case 2:
			query = "SELECT * FROM folders ORDER BY id desc";
			break;
		case 3:
			query = "SELECT * FROM folders ORDER BY name asc";
			break;
		}
		var carpetas = new ArrayList<Carpeta>();
		try {
            Connection conn = DriverManager.getConnection(url); 
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(query);  
              
            
            while (rs.next()) {  
            	/*
                System.out.println(rs.getInt("id") +  "\t" +   
                                   rs.getString("name"));*/
                
                int id = rs.getInt("id");
                String name = rs.getString("name");
                carpetas.add(new Carpeta(id,name));
            }
            conn.close();
		}catch (Exception e) {
            System.out.println(e.getMessage());  
		}
		
		return carpetas;
	}
	
	public ArrayList<Nota> seleccionarNotas(int idCarpeta, int ordenarPor) {
		String query = "SELECT * FROM notes";
		switch(ordenarPor) {
		case 1:
			query = "SELECT * FROM notes WHERE idFolder = " + idCarpeta + " ORDER BY id asc";
			break;
		case 2:
			query = "SELECT * FROM notes WHERE idFolder = " + idCarpeta + " ORDER BY id desc";
			break;
		case 3:
			query = "SELECT * FROM notes WHERE idFolder = " + idCarpeta + " ORDER BY dateModified desc";
			break;

		}
		
		var notas = new ArrayList<Nota>();
		try {
            Connection conn = DriverManager.getConnection(url); 
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(query);  
             
            while (rs.next()) {                  
                int id = rs.getInt("id");
                String texto = rs.getString("text");
                String fechaModificacion = rs.getString("dateModified");

                notas.add(new Nota(id,texto,fechaModificacion,idCarpeta ));
            }
            conn.close();
		}catch (Exception e) {
            System.out.println(e.getMessage());  
		}
		
		return notas;
	}
	
	public void eliminarCarpeta(int id) {
		String query = "DELETE FROM folders WHERE id = " + id;
		try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
            
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
		eliminarNotasDeCarpeta(id);
	}
	
	public void eliminarNotasDeCarpeta(int carpetaId) {
		String query = "DELETE FROM notes WHERE idFolder = " + carpetaId;
		try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
            
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
	}
	
	public void eliminarNota(int id) {
		String query = "DELETE FROM notes WHERE id = " + id;
		try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
            
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
	}
	
	public void eliminarNotas(ArrayList<Integer> ids) {
		String query = "DELETE FROM notes WHERE id = " + ids.get(0);
		for(int i=1;i<ids.size();i++) {
			query+= " OR id = " + ids.get(i);
		}
		try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
            
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
	}
	
	public void actualizarNota(Nota nota) {
		String query = "UPDATE notes SET text = '" + nota.texto + "', dateModified = '" + nota.fechaModificacion + "' WHERE id = " + nota.id;
		try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
	}
	
	public void actualizarUltimaNota(Nota nota) {
		String query = "UPDATE notes SET text = '" + nota.texto + "', dateModified = '" + nota.fechaModificacion + "' WHERE id = (SELECT max(id) FROM notes)";
		try {
            Connection conn = DriverManager.getConnection(url); 
            PreparedStatement pstmt = conn.prepareStatement(query);  
            pstmt.execute();
            conn.close();
		}catch(Exception e) {
            System.out.println(e.getMessage());  
		}
	}
	
	public ArrayList<Nota> buscarNotas(String buscar, int idCarpeta) {

		String query = "SELECT * FROM notes WHERE idFolder = " + idCarpeta + " AND text LIKE '%" + buscar + "%' ORDER BY dateModified desc";
		var notas = new ArrayList<Nota>();
		try {
            Connection conn = DriverManager.getConnection(url); 
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(query);  
              
            while (rs.next()) {   
                int id = rs.getInt("id");
                String texto = rs.getString("text");
                String fechaModificacion = rs.getString("dateModified");

                notas.add(new Nota(id,texto,fechaModificacion,idCarpeta ));
            }
            conn.close();
		}catch (Exception e) {
            System.out.println(e.getMessage());  
		}
		
		return notas;
	}
}





