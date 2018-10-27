package sociedad;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.mysql.jdbc.Connection;

public class DialogoVerProductosBaja extends JDialog implements ActionListener{

	Vista vista;
	JSplitPane pVentana;
	JScrollPane scroll;
	JList<Producto> listaPanel;	
	String comando;
	Connection con;
	String info;
	
	public DialogoVerProductosBaja(Vista vista, String comando, String info, Connection con) throws SQLException {
		
		this.setSize(900,600);
		this.setLocation(200,50);
		this.setTitle("Productos Baja");
		this.vista = vista;
		this.comando = comando;
		this.info = info;
		this.con = con;
		DefaultListModel<Producto> listaModelo = new DefaultListModel<>();
		
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		pVentana = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				   scroll,crearPanelBotones(52));
		pVentana.setDividerLocation(400);
		pVentana.setDividerSize(10);
	
	
		
		this.getContentPane().add(pVentana);
	
	
		
		MiAdaptadorProducto adaptador = new MiAdaptadorProducto();
		listaPanel = new JList<Producto>(listaModelo);
		for(int i = 0;i < vista.modelo.ConsultarDatosProductos(comando, info, con).size();i++) {
			
			listaModelo.addElement(vista.modelo.ConsultarDatosProductos(comando, info, con).get(i));
		
		}

		listaPanel.setCellRenderer(adaptador);
		scroll.setViewportView(listaPanel);
	
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	

	public ArrayList<Producto> InfoProductos(String comando, String info, Connection con) throws SQLException {
		
		return vista.modelo.ConsultarDatosProductos(comando, info, con);
		
		
	}
	
	
	public Container crearBotones(String titulo) {
		
		JButton boton = new JButton(titulo);
		boton.setActionCommand(titulo);
		boton.addActionListener(this);
		
		return boton;
	}
	
	public Container crearPanelBotones() {
		
		JPanel panel = new JPanel(new GridLayout(1,1,10,10));
	

		panel.add(crearBotones(("Dar de alta")));
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Dar de alta")) {
			
			try {
				
				if(listaPanel.getSelectedIndex() >= 0) {
				ArrayList<Producto> lista =  InfoProductos(comando,info,con);
				lista.get(listaPanel.getSelectedIndex()).setEstado(true);
				vista.modelo.insertarDatos("update productos set estado = " + "'" + 
				lista.get(listaPanel.getSelectedIndex()).toString() + "'" +
				"where productoID = " + lista.get(listaPanel.getSelectedIndex()).getProductoId(), con);
				
				dispose();
				
				JOptionPane.showMessageDialog(vista, "Has dado de alta al producto:"
				+ lista.get(listaPanel.getSelectedIndex()).getProductoId()+ "->"
				+ lista.get(listaPanel.getSelectedIndex()).getDescripcion());
				
				
				}else JOptionPane.showMessageDialog(vista, "No has elegido socio");
			
				
			} catch (SQLException e1) {
				
				Logger.getAnonymousLogger().log(Level.INFO,e1.getMessage(),e1);
			}
			
			
			
		}
	}

}
