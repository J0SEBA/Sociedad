package sociedad;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Modelo extends Observable{
	
	Vista vista;
	int pedidoCont = 1;
	
	public Modelo(Vista vista) {
		
		this.vista = vista;
		
	}
	
	public List<Socio> ConsultarDatosSocio(String comando,String info,Connection con) throws SQLException {
		
		ArrayList<Socio> listaSocio = new ArrayList<>();
		Statement stmt = null;
		try {
			stmt=(Statement) con.createStatement();
		
		}catch(Exception e) {
			Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
		}
		ResultSet rs=stmt.executeQuery(comando);
		
		while(rs.next())
		{
			Socio socio = new Socio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9),rs.getString(10));
			
			listaSocio.add(socio);
		}
		return listaSocio;
	
	}
	
	
	public List<Producto> ConsultarDatosProductos(String comando,String info,Connection con) throws SQLException {
		
		ArrayList<Producto> listaProductos = new ArrayList<>();
		
		Statement stmt = null;
		try {
			stmt=(Statement) con.createStatement();
		
		}catch(Exception e) {
			Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
		}
		ResultSet rs=stmt.executeQuery(comando);
		
		while(rs.next())
		{
			Producto producto = new Producto(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),
					rs.getInt(6),rs.getBoolean(7),rs.getFloat(8));
			
			listaProductos.add(producto);
		}
		return listaProductos;
	
	}
	
	
public List<Categorias> ConsultarDatosCategorias(String comando,String info,Connection con) throws SQLException {
		
		ArrayList<Categorias> listaCategorias = new ArrayList<>();
		
		Statement stmt = null;
		try {
			stmt=(Statement) con.createStatement();
		
		}catch(Exception e) {
			Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
		}
		ResultSet rs=stmt.executeQuery("select * from Categorias");
		
		while(rs.next())
		{
			Categorias categorias = new Categorias(rs.getInt(1),rs.getString(2));
			
			listaCategorias.add(categorias);
		}
		return listaCategorias;
	
	}
	
	public void insertarDatos(String comando,Connection conn) {
		
	    try{
	      
	      Statement st = (Statement) conn.createStatement();
	      st.executeUpdate(comando);

	    }
	    catch (Exception e)
	    {
	    	Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
	    }
	  }
	
	
	public int obtenerId(String comando,Connection conn) {
		
		int valor = 0;
		int valorOficial =0;
		
		
		try{
		Statement stmt=(Statement) conn.createStatement();
		ResultSet rs= stmt.executeQuery(comando);
	
		
		
			while( rs.next()) {
				
			valor = Integer.parseInt(rs.getString(1));
			
			}
		
		
		}
		catch (SQLException e) {
			
			Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
		}
		valorOficial = valor+1;
		return (valorOficial);
	}
	
	
	
	public void comprobar()
	{

		Conexion con=new Conexion("admin");
		
		
		List<Producto> lista=new ArrayList<>();
		try {
			lista=ConsultarDatosProductos("select * from productos where estado='activo';",null,con.crearConexionBD("admin"));
		} catch (SQLException e) {
			
			Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
		}
		List<Producto> array=new ArrayList<>();
		
		boolean estado=false;
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getStock()<lista.get(i).getMinStock())
			{
				array.add(lista.get(i));
				estado=true;
			}
		}
		if(estado)
		{
			sacarAlertaProductosMinimo(array);
		}
	}
	
	public void sacarAlertaProductosMinimo(List<Producto> lista)
	{
		String str="Los productos ";
		
		for(int i=0;i<lista.size()-1;i++)
		{
			str=str+lista.get(i).getDescripcion()+" ";
		}
		str=str+" y "+lista.get(lista.size()-1).getDescripcion()+" estan en minimos. Quieres hacer un pedido?";
	
		int i=JOptionPane.showConfirmDialog(null, str);

		if(i==0)
		{
			hacerPedido(lista);
		}
	}
	
	public void hacerPedido(List<Producto> lista)
	{
		JDialog dialogo=new JDialog(vista,"Hacer Pedido",false);
		boolean [] estado=new boolean[lista.size()];
		JPanel panelCentral=new JPanel(new BorderLayout());
		JPanel panel=new JPanel(new GridLayout(2,1));
		JLabel label;
		JButton boton;
		JPanel panelSec;
		JCheckBox check;
		for(int i=0;i<lista.size();i++)
		{
			label=new JLabel(lista.get(i).getDescripcion());
			panelSec=new JPanel(new GridLayout(1,2));
			check=new JCheckBox();
			check.setText(String.valueOf(lista.get(i).getProductoId()));
			check.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					JCheckBox box=(JCheckBox) e.getSource();
					String str =box.getText();
					for(int i=0;i<lista.size();i++)
					{
						if(lista.get(i).getProductoId()==Integer.parseInt(str))
						{
							estado[i]=!estado[i];
						}
					}
				}
				
			});
			panelSec.add(label);
			panelSec.add(check);
			panel.add(panelSec);
			
		}
		panelCentral.add(panel,BorderLayout.NORTH);
		boton=new JButton("Aceptar");
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Producto> listaSec=new ArrayList<>();
				for(int i=0;i<lista.size();i++)
				{
					if(estado[i])
					{
						listaSec.add(lista.get(i));
					}
				}
				addPedido(listaSec);
			}
			
		});
		panelCentral.add(boton,BorderLayout.SOUTH);
		
		dialogo.setUndecorated(false);
		dialogo.setLocation(400, 100);
		dialogo.setSize(500,500);
		dialogo.setContentPane(panelCentral);
		dialogo.setVisible(true);
		dialogo.setResizable(true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	public void addPedido(List<Producto> lista) {
		String str="insert into pedidos(pedidoID,precioTotal,proveedor) values(";
		String [] s=new String [lista.size()];
		float contPrecio=0;
		int pedidoID=pedidoCont;
		for(int i=0;i<lista.size();i++)
		{
			s[i]="insert into pedido_producto(pedidoID, productoID, cantidad) values(";
			s[i]=s[i]+String.valueOf(pedidoID)+","+String.valueOf(lista.get(i).getProductoId())+","+String.valueOf(lista.get(i).getMedioStock())+")";
			
			contPrecio=contPrecio+lista.get(i).getPrecio();
		}
		
		str=str+String.valueOf(pedidoID)+","+String.valueOf(contPrecio)+","+"'Alguien')";
		
		Conexion con=new Conexion("admin");
		Connection conn=con.crearConexionBD("admin");
		
		insertarDatos(str,conn);
		System.out.println(str);
		for(int i=0;i<lista.size();i++)
		{
			con=new Conexion("admin");
			conn=con.crearConexionBD("admin");
			insertarDatos(s[i],conn);
			
		}
		
		
		
		pedidoCont++;
	}
	
	public boolean identificarse(String s1,String s2,Connection con)
	{
		ArrayList<String[]> lista = new ArrayList<>();
		
		try {
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery("select nombre from socios");
			
			ResultSetMetaData result;
			result=(ResultSetMetaData) rs.getMetaData();
		
			
			while(rs.next())
			{
				
				for(int i=0;i<result.getColumnCount();i=i+2)
				{
					String[] s=new String[2];
					
					s[0]=rs.getString(i+1);
					s[1]=rs.getString(i+2);
					
					lista.add(s);
				}
				if(s1.equals(lista.get(lista.size()-1)[0]) && s2.equals(lista.get(lista.size()-1)[1])) return true;
			}
			con.close();
		} catch (SQLException e) {
			
			Logger.getAnonymousLogger().log(Level.INFO,e.getMessage(),e);
		}
		return false;
	}

}
