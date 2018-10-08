/** @file Conexion.java
 *  @brief Class where we make the connection between the database and the java aplication
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @brief Conexion class
 */
public class Conexion {
	
	/**
	 * @brief Attributes
	 */
	Connection conex;
	String usuario;
	
	/**
	 * @brief The constructor of the class Conexion
	 * @param usuario: The username of the person who is going to login
	 * @param conex: It is the connection between the database and the java program
 	 */
	public Conexion(String usuario) {
		
			this.usuario = usuario;
			conex = crearConexionBD(usuario);
		
		}
	
	
	/**
	 * @brief Create the connection between the database and java
	 * @param usuario: The username of the person who is going to login
	 * @param con: It is the connection between the database and the java program
	 * @param url: It is the location of our database
 	 */
public Connection crearConexionBD(String usuario)
	{
		
		Connection con=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/sociedad";
			con=DriverManager.getConnection(url,usuario, "321");
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
