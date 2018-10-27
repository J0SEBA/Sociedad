package sociedad;

public class Categorias {
	
	int categoriaID;
	String descripcion;
	
	public Categorias(int categoriaID, String descripcion) {
		
		this.categoriaID = categoriaID;
		this.descripcion = descripcion;
		
	}

	public int getCategoriaID() {
		return categoriaID;
	}

	public void setCategoriaID(int categoriaID) {
		this.categoriaID = categoriaID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

}
