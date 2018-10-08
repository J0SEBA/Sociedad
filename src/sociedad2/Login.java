package sociedad2;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JDialog implements ActionListener {

	JTextField usuario;
	JPasswordField contraseña;
	Vista vista;
	public Login(Vista vista)
	{
		 this.vista=vista;
		 this.setUndecorated(false);
		 this.setLocation(400, 100);
		 this.setSize(500,500);
		  
		 
	
		 this.setContentPane(crearPanelVentana());
		  
		 this.setVisible(true);
		  
		 this.setResizable(true);
		 this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	private Container crearPanelVentana() {
		
		JPanel panel = new JPanel(new GridLayout(3,1,0,0));
		panel.setBorder(BorderFactory.createTitledBorder("Sociedad"));
		
		usuario = new JTextField(20);
		contraseña = new JPasswordField(20);
		
		usuario.setFont(usuario.getFont().deriveFont(36.0f));
		contraseña.setEchoChar('*');
		contraseña.setFont(contraseña.getFont().deriveFont(36.0f));
		
		panel.add(login(usuario,"usuario"));
		panel.add(login(contraseña,"contraseña"));
		
		panel.add(panelBotones());
		
		return panel;
	}
	
	private Component login(JTextField text, String titulo) {
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(), titulo));
		
		panel.add(text);
		return panel;
	}
	
	
	public Container panelBotones() {
		
		JPanel panel = new JPanel(new GridLayout(1,1,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		panel.add(crearBotones("Aceptar"));
	
		
		return panel;
	}
	public Container crearBotones(String titulo) {
		
		JButton boton = new JButton(titulo);
		boton.setActionCommand(titulo);
		boton.addActionListener(this);
		
		
		return boton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("Aceptar")) {
		   
		   String user = usuario.getText();
		   String password = contraseña.getText();
		   
		   System.out.println(password);
		   
		  //  try {
		    /*for(int j = 0;j < vista.modelo.ConsultarDatosSocio("Select * from socios where estado = 'activo'", "Socios", vista.conexion.conex).size();j++) {
		        Socio socio;
		        socio = vista.modelo.ConsultarDatosSocio("Select * from socios where estado = 'Activo'", "Socios",
		        vista.conexion.conex).get(j);*/
		     Socio  socio=new Socio( 1, "j", "j", "j", "j", "j", 
	   "j", "j",true, "j");
		    if(/*socio.getUsuario().equals(user) 
		  && (socio.getSocioId() == Integer.valueOf(password))*/true){
		  
		  
		  vista.go(socio);
		  this.dispose();
		  JOptionPane.showMessageDialog(usuario, socio.getSocio());
		 
		  
		 }
		      //  }
		   } /*catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		   }
		  
		  }*/
	}
}
