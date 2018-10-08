package sociedad2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoEditarSocio extends JDialog implements ActionListener {
	
	JTextField socioID,usuario,nombre,apellido1,apellido2,poblacion,direccion,telefono,cuentaCorriente;
	Vista vista;
	Socio socio;
	
	
	public DialogoEditarSocio(Vista vista,Socio socio){
		
		this.vista = vista;
		this.socio = socio;
		this.setSize(500,700);
		this.setLocation(450,50);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(0,10));
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		panel.add(crearPanelDatos(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel (new GridLayout(10,1,0,10));
		usuario = new JTextField(socio.getUsuario(),20);
		nombre = new JTextField(socio.getNombre(),20);
		apellido1 = new JTextField(socio.getApellido1(),20);
		apellido2 = new JTextField (socio.getApellido2(),20);
		poblacion = new JTextField(socio.getPoblacion(),20);
		direccion = new JTextField(socio.getDireccion(),20);
		telefono = new JTextField(socio.getTelefono(),20);
		cuentaCorriente = new JTextField(socio.getCuentaCorriente(),20);
		
		panel.add(crearTextField(usuario,"Usuario*"));
		panel.add(crearTextField(nombre,"Nombre*"));
		panel.add(crearTextField(apellido1,"Primer Apellido*"));
		panel.add(crearTextField(apellido2,"Segundo Apellido"));
		panel.add(crearTextField(poblacion,"Poblacion*"));
		panel.add(crearTextField(direccion,"Direccion*"));
		panel.add(crearTextField(telefono,"Telefono"));
		panel.add(crearTextField(cuentaCorriente,"Cuenta Corriente*"));
		
		return panel;
	}

	

	private Component crearTextField(JTextField text, String titulo) {
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.cyan), titulo));
		
		panel.add(text);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new GridLayout(1,2,20,0));
		JButton bOk = new JButton ("Actualizar");
		bOk.setActionCommand("Actualizar");
		bOk.addActionListener(this);
		
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("Cancelar");
		bCancel.addActionListener(this);
		
		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		socio = new Socio(socio.getSocioId(),usuario.getText(),nombre.getText(),apellido1.getText(),apellido2.getText(),
				poblacion.getText(),direccion.getText(),telefono.getText(),true,cuentaCorriente.getText());
		
		if(e.getActionCommand().equals("Actualizar")) {
			
			System.out.println("eeeeee");
	
			if(socio.getNombre() != null && socio.getApellido1() != null && socio.getApellido2() != null 
					&& socio.getPoblacion()!= null && socio.getDireccion() != null &&
					socio.getCuentaCorriente().length() == 5 && socio.getTelefono().length() == 9) {
				
				vista.modelo.insertarDatos("Update socios set " + "usuario = " + "'" + socio.getUsuario()+ "'" + "," +
				"Nombre = " + "'" + socio.getNombre() + "'" + "," +
				"apellido1 = "	+	"'" + socio.getApellido1() + "'" + "," +
				"Apellido2 =" + "'" + socio.getApellido2() + "'" + "," +
				"Poblacion = " + "'" + socio.getPoblacion() + "'" + "," + 
				"Direccion = " + "'" + socio.getDireccion() + "'" + "," +
				"Telefono = " + "'" + socio.getTelefono() +  "'" + "," +
				"CuentaCorriente = " + "'" + socio.getCuentaCorriente() + "'" 
				+" Where socioId = " + socio.getSocioId(), vista.conexion.conex);
				
				dispose();
			}
			
			else {
				
				JOptionPane.showMessageDialog(vista, "Datos Incorrectos/ * Obligatorio");
			}
			
		
		}
		if(e.getActionCommand().equals("Cancelar")) dispose();
		
	}


}
