package sociedad2;

public class Producto {
	 
	 int productoId;
	 int categoriaId;
	 String descripcion;
	 int stock;
	 int minStock;
	 int medioStock;
	 Boolean estado;
	 int cantidad;
	 float precio;
	 
	 public Producto(int productoId, int categoriaId, String descripcion, int stock, int minStock, int medioStock,Boolean estado,
	   float precio) {
	  
	  this.productoId = productoId;
	  this.categoriaId= categoriaId;
	  this.descripcion= descripcion;
	  this.stock = stock;
	  this.minStock= minStock;
	  this.medioStock= medioStock;
	  this.estado = estado;
	  this.precio = precio;
	  
	 }
	 
	 public void setCantidad(int i)
	 {
		 this.cantidad=i;
	 }
	 public int getCantidad()
	 {
		 return cantidad;
	 }

	 public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public int getProductoId() {
	  return productoId;
	 }

	 public void setProductoId(int productoId) {
	  this.productoId = productoId;
	 }

	 public int getCategoriaId() {
	  return categoriaId;
	 }

	 public void setCategoriaId(int categoriaId) {
	  this.categoriaId = categoriaId;
	 }

	 public String getDescripcion() {
	  return descripcion;
	 }

	 public void setDescripcion(String descripcion) {
	  this.descripcion = descripcion;
	 }

	 public int getStock() {
	  return stock;
	 }

	 public void setStock(int stock) {
	  this.stock = stock;
	 }

	 public int getMinStock() {
	  return minStock;
	 }

	 public void setMinStock(int minStock) {
	  this.minStock = minStock;
	 }

	 public int getMedioStock() {
	  return medioStock;
	 }

	 public void setMedioStock(int medioStock) {
	  this.medioStock = medioStock;
	 }

	 public float getPrecio() {
	  return precio;
	 }

	 public void setPrecio(float precio) {
	  this.precio = precio;
	 }
	 
	 
	 @Override
		public String toString() {
			
			if(getEstado().equals(true)) {
				
				return "Activo";
				
			}else return "Baja";
			
		}
	 

	}
