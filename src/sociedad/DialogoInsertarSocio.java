package sociedad;

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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DialogoInsertarSocio extends JDialog implements ActionListener{
	
	JTextField socioID;
	JTextField usuarionombre;
	JTextField apellido1;
	JTextField apellido2;
	JTextField poblacion;
	JTextField direccion;
	JTextField telefono;
	JTextField cuentaCorriente;
	Vista vista;
	int id;
	
	
	public DialogoInsertarSocio(Vista vista,int id){
		
		this.vista = vista;
		this.id = id;
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
		usuario = new JTextField(20);
		nombre = new JTextField(20);
		apellido1 = new JTextField(20);
		apellido2 = new JTextField (20);
		poblacion = new JTextField(20);
		direccion = new JTextField(20);
		telefono = new JTextField(20);
		cuentaCorriente = new JTextField(20);
		
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
		JButton bOk = new JButton ("OK");
		bOk.setActionCommand("ok");
		bOk.addActionListener(this);
		
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("cancel");
		bCancel.addActionListener(this);
		
		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		Socio socio = new Socio(id,usuario.getText(),nombre.getText(),apellido1.getText(),apellido2.getText(),
				poblacion.getText(),direccion.getText(),telefono.getText(),true,cuentaCorriente.getText());
		
		if (e.getActionCommand().equals("ok")){
			
			if(socio.getNombre() != null && socio.getApellido1() != null && socio.getApellido2() != null 
					&& socio.getPoblacion()!= null && socio.getDireccion() != null &&
					socio.getCuentaCorriente().length() == 5 && socio.getTelefono().length() == 9) {
				
				vista.modelo.insertarDatos("Insert into socios values(" + "'" + socio.getSocioId() + "'" + "," + "'" 
				+ socio.getUsuario() + "'" + "," + "'" + socio.getNombre() + "'" + "," + "'" + socio.getApellido1() + "'" + "," 
						+ "'" + socio.getApellido2() + "'" + "," + "'" + socio.getPoblacion() + "'" + "," + "'" + socio.getDireccion() + "'" + "," 
						+ "'" + socio.getTelefono() +  "'" + "," + "'" + socio.toString() + "'" + "," + "'" 
						+ socio.getCuentaCorriente() + "'" + ")", vista.conexion.conex);
				
				dispose();
			}
			
			else {
				
				JOptionPane.showMessageDialog(vista, "Datos Incorrectos/ * Obligatorio");
			}
			
			
		}
		if (e.getActionCommand().equals("cancel")){
			dispose();
		}	
	}

}
