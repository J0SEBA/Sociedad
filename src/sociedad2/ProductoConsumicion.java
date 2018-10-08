package sociedad2;

import java.sql.Date;

public class ProductoConsumicion {
	
	 int consumicionID;
	 int productoID;
	 int cantidad;
	 float precioTotal;
	 
	 public ProductoConsumicion(int consumicionID, int productoID, int cantidad, float precioTotal) {
		 
		 this.consumicionID = consumicionID;
		 this.productoID = productoID;
		 this.cantidad = cantidad;
		 this.precioTotal = precioTotal; 
	 }

	public int getConsumicionID() {
		return consumicionID;
	}

	public void setConsumicionID(int consumicionID) {
		this.consumicionID = consumicionID;
	}

	

	public int getProductoID() {
		return productoID;
	}

	public void setProductoID(int productoID) {
		this.productoID = productoID;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	 
	 
	 

}
