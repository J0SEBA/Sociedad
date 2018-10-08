/** @file Controlador.java
 *  @brief Class in which we respond to user interactions
 *  @authors
 *  Name          | Surname        | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Marcos          Azcarate         marcos.azcarate@alumni.mondragon.edu
 *  @date 05/10/2018
 */

/** @brief Packages
 */
package sociedad2;

/** @brief Libraries
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

/**
 * @brief Controlador class
 */
public class Controlador implements ActionListener {
	
	/**
	 * @brief Attributes
	 */
	Vista vista;
	Modelo modelo;
	
	
	/**
	 * @brief The constructor of the class Controlador
	 * @param vista: Is the 'main' class of the project that represent the interface of the user
	 * @param modelo: Handles the data, consulting the database
 	 */
	public Controlador(Vista vista, Modelo modelo) {
		
		this.vista=vista;
		this.modelo = modelo;
		
	}

	/**
	 * @brief Respond to the actions performed in the interface
	 * @param con: It is the connection between the database and the java program
	 * @param bai: It is the id of the registered user
	 * @param e: Contains the action that the user want to do
	 * @param baii: Contains the id of the last consumption made
	 * @param i: Contains the id of the last registered member
	 * @param a: Contains the id of the last registered product
	 * @param dialogoProducto: Opens the interface where we can see the different products of the 'sociedad'
	 * @param dialogoSocio: Opens the interface where we can see the different members of the 'sociedad'
	 * @param dialogoVerTuHistorial: Opens the interface where we can see the different consumptions made in the 'sociedad'
	 * @param consumiciones: Opens the interface where we can register different consumptions that we made in the 'sociedad'
	 * @param dialogoSocio: Opens the interface where we can register a new member in the 'sociedad'
	 * @param dialogoProducto: Opens the interface where we can register a new product in the 'sociedad'
	 * @param dialogoBaja: Opens the interface where we can see the members written off
	 * @param dialogoProdBaja: Opens the interface where we can see the products written off
 	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Connection con = (Connection) vista.getConexion().crearConexionBD(vista.socio.getUsuario());

		switch(e.getActionCommand())
		{
		case "VER PRODUCTOS":
			try {
				
				DialogoVerProductos dialogoProducto = new DialogoVerProductos(vista,"Select * from productos where "
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
		case "CONSUMICIONES":
			
				int bai = vista.modelo.obtenerId("select * from consumiciones where socioId="+vista.getSocio().getSocioId(), con);
				if(bai <= 1) {
					JOptionPane.showMessageDialog(null, "No hay consumiciones registradas");
				}else {
					try {
				DialogoConsumiciones dialogoVerTuHistorial =new DialogoConsumiciones(vista,"select * from consumiciones where socioId="+vista.getSocio().getSocioId()
					+ " and "	,"Consumiciones",con, " ");
					}catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
					}
				}
			
			break;
		case "TODAS LAS CONSUMICIONES":
			int baii = vista.modelo.obtenerId("select * from consumiciones", con);
			if(baii <= 1) {
				JOptionPane.showMessageDialog(null, "No hay consumiciones registradas");
			}else {
				try {
					DialogoConsumiciones consumiciones = new DialogoConsumiciones(vista, "select * from consumiciones", "Consumiciones", con, " where");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			break;
		
		case "REGISTRAR PEDIDO":
			
			modelo.comprobar();
			
			break;
		case "REGISTRAR SOCIO":
			int i = modelo.obtenerId("Select max(socioID) from socios", con);
			DialogoInsertarSocio dialogoSocio = new DialogoInsertarSocio(vista,i); 
			break;
		case "REGISTRAR PRODUCTO":
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
			
		case "Cerrar Sesion": 
			modelo.escribirData();
			modelo.cerrarSesion(); 
			Login log=new Login();
			break;
			
		case "Pasar dia":
			modelo.getFecha().pasarDia();
			vista.getData().setText(modelo.getFecha().toString());
			break;
		}
	}
}
