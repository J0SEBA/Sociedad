package sociedad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MiAdaptadorProducto implements ListCellRenderer<Producto> {

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
		
		JPanel panel = new JPanel(new GridLayout(5,1,10,10));
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(),"Categoria ID:" + p.getCategoriaId()));
		
		panel.setBackground(Color.WHITE);
	
		panel.add(crearDescripcion(p.getDescripcion()));
		panel.add(crearStock("Stock:" + p.getStock()));
		panel.add(crearStock("Stock Medio:" + p.getMedioStock()));
		panel.add(crearStock("Stock Min:" + p.getMinStock()));
		panel.add(crearPrecio("Precio:" + p.getPrecio()));
		
		
		return panel;
	}

	private Component crearDescripcion(String info) {
		JLabel ldescripcion = new JLabel (info);
		ldescripcion. setFont(new Font("Arial",Font.BOLD,14));
		ldescripcion.setForeground (Color.BLACK);
		return ldescripcion;
	}
	
	
	private Component crearStock(String info) {
		JLabel lStock = new JLabel (info);
		lStock.setFont(new Font("Arial",Font.ITALIC,14));
		lStock.setForeground (Color.BLUE);
		return lStock;
	}
	
	private Component crearPrecio(String info) {
		JLabel lPrecio = new JLabel (info);
		lPrecio. setFont(new Font("Arial",Font.BOLD,14));
		lPrecio.setForeground (Color.BLACK);
		return lPrecio;
	}
}
