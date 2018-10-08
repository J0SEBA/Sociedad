package sociedad2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Conexion {
	

	Connection conex;
	
	public Conexion() {
		
			conex = crearConexion();
		
		}
	
public Connection crearConexion()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/sociedad";
			con=DriverManager.getConnection(url,"root", null);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
