package sociedad2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Controlador implements ActionListener {
	
	Vista vista;
	Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo) {
		
		this.vista=vista;
		this.modelo = modelo;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Connection con = (Connection) vista.getConexion().crearConexion();

		switch(e.getActionCommand())
		{
		case "VER PRODUCTOS":
			try {
				
				DialogoVerProducto dialogoProducto = new DialogoVerProducto(vista,"Select * from productos where "
						+ " estado = 'activo' and categoriaID = 10","Productos", con);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "VER SOCIOS":
			try {
				DialogoVerSocio dialogoSocio = new DialogoVerSocio(vista,"select* from socios where"
						+ " estado = 'activo' order by socioID ","Socios", con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "VER TU HISTORIAL DE CONSUMICIONES":
			break;
		case "VER TODAS LAS CONSUMICIONES":
			break;
		case "CAMBIAR INFO SOCIOS":
			break;
		case "CAMBIAR INFO PRODUCTOS":
			break;
		case "REGISTRAR PEDIDO":
			
			modelo.comprobar();
			
			break;
		case "REGISTRAR NUEVO SOCIO":
			int i = modelo.obtenerId("Select max(socioID) from socios", con);
			DialogoInsertarSocio dialogoSocio = new DialogoInsertarSocio(vista,i); 
			break;
		case "REGISTRAR NUEVO PRODUCTO":
			int a = modelo.obtenerId("Select max(productoID) from productos", con);
			DialogoInsertarProducto dialogoProducto = new DialogoInsertarProducto(this,a);
			break;
		case "REGISTRAR CONSUMICION":
			try {
				DialogoRegistrarConsumicion dialogoConsumicion = new DialogoRegistrarConsumicion(vista,"Select * from productos where categoriaID = 10","Productos", con);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "DAR SOCIO ALTA":
			try {
				DialogoVerSociosBaja dialogoBaja = new DialogoVerSociosBaja(vista, "select* from socios where "
						+ "estado = 'baja'  order by socioID ","Socios", con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "DAR PRODUCTO ALTA":
			try {
				DialogoVerProductosBaja dialogoProdBaja = new DialogoVerProductosBaja(vista, "select* from productos where "
							+ "estado = 'baja'  order by productoID ","Productos", con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		}
	}
}
