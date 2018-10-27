package sociedad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MiAdaptadorRegistrarConsumicion  implements ListCellRenderer<Producto> {
	
 

	@Override
	public Component getListCellRendererComponent(JList<? extends Producto> list, Producto p, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		String str=p.getDescripcion();
		JLabel labelFoto =new JLabel(new ImageIcon("icons/"+str+".jpg"));
		
		
		panel.add(crearDatos(p), BorderLayout.CENTER);
		panel.add(labelFoto);
		
        
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),
        		BorderFactory.createLineBorder(Color.BLACK)));
        panel.setBackground(isSelected ? Color.BLUE : Color.white);
        
        panel.setOpaque(true);
         
         return panel;
	}

	private Component crearDatos(Producto p) {
		
		JPanel panel = new JPanel(new GridLayout(1,2,10,10));
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(),"Categoria ID:" + p.getCategoriaId()));
		
		
		panel.setBackground(Color.WHITE);
	
		panel.add(crearDescripcion(p.getDescripcion()));
		panel.add(crearPrecio(p.getCantidad()));
		
		return panel;
	}

	private Component crearDescripcion(String info) {
		JLabel ldescripcion = new JLabel (info);
		ldescripcion. setFont(new Font("Arial",Font.BOLD,14));
		ldescripcion.setForeground (Color.BLACK);
		return ldescripcion;
	}
	
	
	private Component crearPrecio(int info) {
		JLabel lPrecio = new JLabel ("Cantidad:" + String.valueOf(info));
		lPrecio. setFont(new Font("Arial",Font.BOLD,14));
		lPrecio.setForeground (Color.RED);
		return lPrecio;
	}
	
}