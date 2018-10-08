package sociedad2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;              

public class DialogoVerSocio extends JDialog implements ActionListener {

	Vista vista;
	JSplitPane pVentana;
	JScrollPane scroll;
	JList<Socio> listaPanel;	
	String comando;
	Connection con;
	String info;
	
	public DialogoVerSocio(Vista vista, String comando, String info, Connection con) throws SQLException {
		
		this.setSize(900,600);
		this.setLocation(200,50);
		this.setTitle("Información socios");
		this.vista = vista;
		this.comando = comando;
		this.info = info;
		this.con = con;
		DefaultListModel<Socio> listaModelo = new DefaultListModel<Socio>();
		
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		pVentana = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				   scroll,crearPanelBotones());
		pVentana.setDividerLocation(400);
		pVentana.setDividerSize(10);
	
	
		
		this.getContentPane().add(pVentana);
	
	
		//scroll=new JScrollPane(crearPanelBotones(52));
		MiAdaptador adaptador = new MiAdaptador();
		listaPanel = new JList<Socio>(listaModelo);
		for(int i = 0;i < vista.modelo.ConsultarDatosSocio(comando, info, con).size();i++) {
			
			listaModelo.addElement(vista.modelo.ConsultarDatosSocio(comando, info, con).get(i));
		
		}

		listaPanel.setCellRenderer(adaptador);
		scroll.setViewportView(listaPanel);
		//scroll.add(crearPanelBotones(52),BorderLayout.SOUTH);
		//this.setContentPane(scroll);
		//this.add(scroll);
	
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	

	public ArrayList<Socio> InfoSocios(String comando, String info, Connection con) throws SQLException {
		
		ArrayList<Socio> listaSocios = vista.modelo.ConsultarDatosSocio(comando, info, con);
		
		return listaSocios;
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Editar")) {
			
			if(listaPanel.getSelectedIndex() >= 0) {
			DialogoEditarSocio dialogoEditSocio = 
					new DialogoEditarSocio(vista,listaPanel.getModel().getElementAt(listaPanel.getSelectedIndex()));
			dispose();
			
			}else JOptionPane.showMessageDialog(vista, "No has elegido socio");
			
		}
		
		if(e.getActionCommand().equals("Dar de baja")) {
			
			try {
				if(listaPanel.getSelectedIndex() >= 0) {
				ArrayList<Socio> lista =  InfoSocios(comando,info,con);
				lista.get(listaPanel.getSelectedIndex()).setEstado(false);
				vista.modelo.insertarDatos("update socios set estado = " + "'" + 
				lista.get(listaPanel.getSelectedIndex()).toString() + "'" +
				"where socioID = " + lista.get(listaPanel.getSelectedIndex()).getSocioId(), con);
				
				JOptionPane.showMessageDialog(vista, "Has dado de baja al socio:"
						+ lista.get(listaPanel.getSelectedIndex()).getSocioId()+ "->" + lista.get(listaPanel.getSelectedIndex()).getNombre()
						+ " " + lista.get(listaPanel.getSelectedIndex()).getApellido1());
				
						dispose();
				
				}else JOptionPane.showMessageDialog(vista, "No has elegido socio");
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	}

}
