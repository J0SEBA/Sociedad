package sociedad;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.mysql.jdbc.Connection;

public class DialogoVerProducto extends JDialog implements ActionListener,ItemListener{
	
	Vista vista;
	String comando;
	String info;
	Connection con;
	JSplitPane pVentana;
	JScrollPane scroll;
	ImageIcon icono;
	JComboBox<String> combo;
	JLabel label;
	DefaultListModel<Producto> listaModelo;
	JList<Producto> listaPanel;
	MiAdaptadorProducto adaptador;
	
	public DialogoVerProducto(Vista vista, String comando, String info, Connection con) throws SQLException {
		
		this.setSize(900,600);
		this.setLocation(200,50);
		this.setTitle("Información productos");
		this.vista = vista;
		this.comando = comando;
		this.info = info;
		this.con = con;
		
		label = new JLabel();
		listaPanel = new JList<>();
		listaModelo = new DefaultListModel<>();
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		
		adaptador = new MiAdaptadorProducto();
		
		pVentana = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				crearPanelInfo(),crearPanelScroll());
		pVentana.setDividerLocation(400);
		pVentana.setDividerSize(10);
	
		this.setContentPane(pVentana);
	
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public Component crearPanelScroll() throws SQLException {
		
		for(int i = 0;i < vista.modelo.ConsultarDatosProductos(comando, info, con).size();i++) {
			
			listaModelo.addElement(vista.modelo.ConsultarDatosProductos(comando, info, con).get(i));
		
		}

		listaPanel.setModel(listaModelo);
		listaPanel.setCellRenderer(adaptador);
		scroll.setViewportView(listaPanel);
		
		return scroll;
	}

	private Component crearPanelInfo() throws SQLException {
		
		JPanel panel = new JPanel(new GridLayout(3,1,20,20));
		
		combo = new JComboBox<>();
		
		for(int i = 0;i<vista.modelo.ConsultarDatosCategorias(comando, info, con).size();i++) {
			
			combo.addItem(vista.modelo.ConsultarDatosCategorias(comando, info, con).get(i).getDescripcion());
			
		}
		combo.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.BLACK),"Categorias"));
		
		crearCaratula(combo.getSelectedIndex());
		combo.setFont(combo.getFont().deriveFont(18.0f));
		
		panel.add(combo);
		panel.add(label);
		panel.add(crearPanelBotones());
		combo.addItemListener(this);
		
		return panel;
	}
	
	
	public Container crearBotones(String titulo) {
		
		JButton boton = new JButton(titulo);
		boton.setActionCommand(titulo);
		boton.addActionListener(this);
		
		return boton;
	}
	
	public Container crearPanelBotones() {
		
		JPanel panel = new JPanel(new GridLayout(2,1,10,10));
	
		panel.add(crearBotones(("Editar")));
		panel.add(crearBotones(("Dar de baja")));
		
		return panel;
	}
	
	
	
	public List<Producto> infoProducto(String comando, String info, Connection con) throws SQLException {
		
		return  vista.modelo.ConsultarDatosProductos(comando, info, con);
		
		
	}
	
	
	private String categoria(int i) {
		  
		  String str = null;
		 
		  switch(i) {
		  
		  case 0: str = "Refrescos";break;
		  case 1:str = "Bebidas con alcohol";break; 
		  case 2: str = "Bebida";break;
		  case 3:str = "Elementos cocina";break;
		  default:
		  }
		  
		  return str;
		 }
		 
		 private String elegirComando(String str) {
		  
		  String comando = null;
		 
		  switch(str) {
		  
		  case "Refrescos": comando = "Select * from productos where categoriaID = 10";break;
		  case "Bebidas con alcohol":comando = "Select * from productos where categoriaID = 11";break; 
		  case "Bebida": comando = "Select * from productos where categoriaID = 12";break;
		  case "Elementos cocina":comando = "Select * from productos where categoriaID = 13"; break;
		 default:
		  }
		  
		  return comando;
		 }
	
	private void crearCaratula(int i) {
	
		String str = null;

		switch(i) {
		case 0:
			str = "Refrescos";
			break;
		case 1:
			str = "Bebidas con alcohol";
			break;			
		case 2: 
			str = "Bebida";
			break;
		case 3:
			str = "Elementos cocina";
			break;
			default:
		}
		
		icono = new ImageIcon("icons/" + str + ".jpg");
		label.setIcon(icono);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Editar")) {
			
			if(listaPanel.getSelectedIndex() >= 0) {
				DialogoEditarProducto dialogoEditProducto = 
					new DialogoEditarProducto(vista,listaPanel.getModel().getElementAt(listaPanel.getSelectedIndex()));
			dispose();
			}else JOptionPane.showMessageDialog(vista, "No has elegido Producto");
			
			}
		
		
	if(e.getActionCommand().equals("Dar de baja")) {
			
			try {
				if(listaPanel.getSelectedIndex() >= 0) {
				ArrayList<Producto> lista =  InfoProducto(comando,info,con);
				lista.get(listaPanel.getSelectedIndex()).setEstado(false);
				vista.modelo.insertarDatos("update productos set estado = " + "'" + 
				lista.get(listaPanel.getSelectedIndex()).toString() + "'" +
				"where productoID = " + lista.get(listaPanel.getSelectedIndex()).getProductoId(), con);
				
				dispose();
				
				JOptionPane.showMessageDialog(vista, "Has dado de baja al Producto:"
						+ lista.get(listaPanel.getSelectedIndex()).getProductoId()+ "->"
						+ lista.get(listaPanel.getSelectedIndex()).getDescripcion());
				
						
				
				}else JOptionPane.showMessageDialog(vista, "No has elegido socio");
				
				
				
			} catch (SQLException e1) {
				
				Logger.getAnonymousLogger().log(Level.INFO,e1.getMessage(),e);
			}
			
			
			
		}
		
	}

	@Override
	 public void itemStateChanged(ItemEvent e) {
	 
	  String comando1;
	  int i =combo.getSelectedIndex();
	  crearCaratula(i);
	  listaModelo.removeAllElements();
	  
	  String categoria = categoria(i);
	  comando1 = elegirComando(categoria);
	  
	  
	  try {
	   for(int j = 0;j < vista.modelo.ConsultarDatosProductos(comando1, info, con).size();j++) {
	    
	     listaModelo.addElement(vista.modelo.ConsultarDatosProductos(comando1, info, con).get(j));
	    }
	    
	  
	  } catch (SQLException e1) {
	   
		  Logger.getAnonymousLogger().log(Level.INFO,e1.getMessage(),e);
	  }
	}
}