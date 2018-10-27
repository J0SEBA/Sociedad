package sociedad;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class DialogoRegistrarConsumicion extends JDialog implements ActionListener,ItemListener {
	
	
	Vista vista;
	Dimension dim;
	String comando,info;
	Connection con;
	JSplitPane pVentana;
	JSplitPane tripleVentana;
	JScrollPane scroll;
	JScrollPane scroll1;
	ImageIcon icono;
	JComboBox<String> combo;
	JLabel label;
	JLabel label1;
	JLabel precioTotal;
	DefaultListModel<Producto> listaModelo;
	JList<Producto> listaPanel;
	DefaultListModel<Producto> listaModelo1;
	JList<Producto> listaPanel1;
	MiAdaptadorProducto adaptador;
	MiAdaptadorRegistrarConsumicion adaptador1;
	Float contador  =0.0f ;
	Float total = 0.0f;
	ArrayList<ProductoConsumicion> listaProCon = null;
	JButton aceptar
	JButton quitar;
	
	
	public DialogoRegistrarConsumicion(Vista vista, String comando, String info, Connection con) throws SQLException {
		dim=this.getToolkit().getScreenSize();
		this.setSize(dim);
		this.setLocation(0,0);
		this.setTitle("Información productos");
		this.vista = vista;
		this.comando = comando;
		this.info = info;
		this.con = con;
		listaProCon = new ArrayList<ProductoConsumicion>();
		label = new JLabel();
		label1 = new JLabel("   Cantidad:" + String.valueOf(contador));
		precioTotal = new JLabel("Precio total:" + String.valueOf(total));
		listaPanel = new JList<Producto>();
		listaModelo = new DefaultListModel<Producto>();
		listaPanel1 = new JList<Producto>();
		listaModelo1 = new DefaultListModel<Producto>();
		
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		scroll1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		adaptador = new MiAdaptadorProducto();
		adaptador1 = new MiAdaptadorRegistrarConsumicion();
		
		
		pVentana = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				crearPanelInfo(),crearPanelScroll());
		pVentana.setDividerLocation(400);
		pVentana.setDividerSize(0);
		
		
		
	
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
		
	
		
		
		tripleVentana = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				scroll,crearPanelConsumicion());
		tripleVentana.setDividerLocation(400);
		tripleVentana.setDividerSize(0);
		
		return tripleVentana;
	}

	private Component crearPanelConsumicion() {
	
		
		JSplitPane panel;
		
		panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
				scroll1,crearPanelValidar());
		
		panel.setDividerLocation(620);
		
		return panel;
	}

	private Component crearPanelValidar() {
		
		JPanel panel = new JPanel(new GridLayout(1,3));
		aceptar = new JButton();
		
		aceptar = (JButton) crearBotones("Aceptar");
		quitar = (JButton) crearBotones("Quitar");
		
		aceptar.setEnabled(false);
		quitar.setEnabled(false);
		panel.add(aceptar);
		panel.add(quitar);
		precioTotal.setFont(precioTotal.getFont().deriveFont(18.0f));
		panel.add(precioTotal);
		
		return panel;
	}


	private Component crearPanelInfo() throws SQLException {
		
		JPanel panel = new JPanel(new GridLayout(3,1,20,20));
		
		combo = new JComboBox<String>();
		
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
	
		panel.add(crearPanelContador());
		panel.add(crearBotones(("Añadir")));
	
		
		return panel;
	}
	
	public Container crearPanelContador(){
		
		JPanel panel = new JPanel(new GridLayout(1,3));
	
		label1.setFont(label1.getFont().deriveFont(18.0f));
		panel.add(crearBotones(("-")));
		panel.add(label1);
		panel.add(crearBotones(("+")));
		
		
		return panel;	
	}
	
	public Producto resertStock(Producto producto){
		
		producto.setStock(producto.getStock() -Math.round(contador));
		
		return producto;
	}
	
	private String categoria(int i) {
		  
		  String str = null;
		 
		  switch(i) {
		  
		  case 0: str = "Refrescos";break;
		  case 1:str = "Bebidas con alcohol";break; 
		  case 2: str = "Bebida";break;
		  case 3:str = "Elementos cocina";break;
		  }
		  
		  return str;
		 }
		 
		 private String elegirComando(String str) {
		  
		  String comando = null;
		  int i=0;
		  switch(str) {
		  
		  case "Refrescos": comando = "Select * from productos where categoriaID = 10";break;
		  case "Bebidas con alcohol":comando = "Select * from productos where categoriaID = 11";break; 
		  case "Bebida": comando = "Select * from productos where categoriaID = 12";break;
		  case "Elementos cocina":comando = "Select * from productos where categoriaID = 13"; break;
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
		}
		
		icono = new ImageIcon("icons/" + str + ".jpg");
		label.setIcon(icono);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch(e.getActionCommand()) {
		
			case "+":
			
			label1.setText("   Cantidad:" + String.valueOf(++contador));
			
				break;
			
			case "-":
			label1.setText("   Cantidad:" +String.valueOf(--contador));
			
				break;
			
			case "Añadir":
				
				String comando1;
				int i =combo.getSelectedIndex();
				  
				String categoria = categoria(i);
				comando1 = elegirComando(categoria);
				
				Producto producto = null;
				
				
				
				try {
					
					if(listaPanel.getSelectedIndex() >= 0) {
						
					aceptar.setEnabled(true);
					quitar.setEnabled(true);
						
					producto = vista.modelo.ConsultarDatosProductos(comando1, info, con).get(listaPanel.getSelectedIndex());
					resertStock(producto);
					total = total + (contador * producto.getPrecio());
					precioTotal.setText("Precio total:" + String.valueOf(total));
					producto.setCantidad(Math.round(contador));
					boolean estado=false;
					for(int k=0;k<listaModelo1.size();k++)
					{
						if(listaModelo1.getElementAt(k).getProductoId()==producto.getProductoId())
						{
							Producto aux=listaModelo1.getElementAt(k);
							aux.setCantidad(aux.getCantidad()+producto.getCantidad());
							listaModelo1.setElementAt(aux, k);
							estado=true;
							
						}
					}
					if(!estado)
					{
						listaModelo1.addElement(producto);
					}
					
					listaPanel1.setModel(listaModelo1);
					
					listaPanel1.setCellRenderer(adaptador1);
					scroll1.setViewportView(listaPanel1);
					contador = 0.0f;
					label1.setText("   Cantidad:" +String.valueOf(contador));
					}
					
				} catch (SQLException e2) {
					
					Logger.getAnonymousLogger().log(Level.INFO,e2.getMessage(),e);
				}
				break;
				
			case "Aceptar":
				
				if (JOptionPane.showConfirmDialog(null, "Confirmar operacion?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					ArrayList<Producto> listaProducto = new ArrayList<>();
					for(int j=0;j<listaModelo1.getSize();j++) {
						
						listaProducto.add(listaModelo1.get(j));
						vista.modelo.insertarDatos("Update productos set stock = " + listaProducto.get(j).getStock() + " where productoID ="
								+ listaProducto.get(j).getProductoId(),con);
						
					}
					
					vista.modelo.insertarDatos("insert into consumiciones values (1,101,null, 'no Pagado',7.2)", con);
					
					JOptionPane.showMessageDialog(null, "Operacion correctamente realizada", "Correcto",
					        JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
					break;
				
			case "Quitar":
				total = total - listaModelo1.get(listaPanel1.getSelectedIndex()).getPrecio()*
				listaModelo1.get(listaPanel1.getSelectedIndex()).getCantidad();
				listaModelo1.removeElementAt(listaPanel1.getSelectedIndex());
				precioTotal.setText("Precio total:" + String.valueOf(total));
				
				if(listaModelo1.getSize() == 0) {
					
					aceptar.setEnabled(false);
					quitar.setEnabled(false);
					
				}
				
				break;
		
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
