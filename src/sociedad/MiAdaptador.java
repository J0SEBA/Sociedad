package sociedad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MiAdaptador implements ListCellRenderer<Socio>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Socio> list, Socio p, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel(new BorderLayout(10,10));
		
		
		panel.add(crearDatos(p), BorderLayout.CENTER);
		
        
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),
        		BorderFactory.createLineBorder(Color.BLACK)));
        panel.setBackground(isSelected ? Color.BLUE : Color.white);
        
         panel.setOpaque(true);
       
         return panel;
	}

	private Component crearDatos(Socio p) {
		
		JPanel panel = new JPanel(new GridLayout(5,1,10,10));
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(),"Socio ID:" + p.getSocioId()));
		
		panel.setBackground(Color.WHITE);
	
		panel.add(crearNombres(p.getNombre() + " " + p.getApellido1() + " " + p.getApellido2()));
		panel.add(crearPoblacion(p.getPoblacion() + ":" + p.getDireccion()));
		panel.add(crearTelefono("Telf:" + p.getTelefono()));
		panel.add(crearCuenta("CC:" + p.getCuentaCorriente()));
		
		
		return panel;
	}

	
	private Component crearNombres(String info) {
		JLabel lnombres = new JLabel (info);
		lnombres. setFont(new Font("Arial",Font.BOLD,14));
		lnombres.setForeground (Color.BLACK);
		return lnombres;
	}
	
	private Component crearPoblacion(String info) {
		JLabel lpoblacion = new JLabel (info);
		lpoblacion. setFont(new Font("Arial",Font.ITALIC,14));
		lpoblacion.setForeground (Color.BLUE);
		return lpoblacion;
	}
	
	private Component crearTelefono(String info) {
		JLabel lTelefono = new JLabel (info);
		lTelefono. setFont(new Font("Arial",Font.BOLD,14));
		lTelefono.setForeground (Color.BLACK);
		return lTelefono;
	}
	
	
	private Component crearCuenta(String info) {
		JLabel lCuenta = new JLabel (info);
		lCuenta. setFont(new Font("Arial",Font.BOLD,14));
		lCuenta.setForeground (Color.RED);
		return lCuenta;
	}
	
	
}
