package sociedad2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DialogoInsertarProducto extends JDialog implements ActionListener, ItemListener{
	
	JTextField descripcion,stock,stockMin,stockMedio,precio;
	ButtonGroup datos;
	JRadioButton categoria10,categoria11,categoria12,categoria13;
	String categoria;
	int i;
	
	Controlador controlador;
	
	public DialogoInsertarProducto(Controlador controlador,int i)  {
		
		this.controlador = controlador;
		this.i = i;
		this.setSize(500,700);
		this.setLocation(450,50);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		
		JPanel panel = new JPanel (new BorderLayout(0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		panel.add(crearPanelDatos(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}
	
	
public Container crearGrupoBoton(String titulo) {
		
		JPanel panel = new JPanel(new GridLayout(2,3,0,0));
		/*panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.cyan), titulo));*/
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.cyan), titulo), BorderFactory.createEmptyBorder(0, 0, 0,0)));
	
		datos = new ButtonGroup();
		categoria10 = new JRadioButton("Refrescos");
		categoria10.setSelected(false);
		categoria10.addItemListener(this);
		
		categoria11 = new JRadioButton("Bebidas Alcoholicas");
		categoria11.setSelected(false);
		categoria11.addItemListener(this);
		
		categoria12 = new JRadioButton("Agua");
		categoria12.setSelected(false);
		categoria12.addItemListener(this);
		
		categoria13 = new JRadioButton("Aliños");
		categoria13.setSelected(false);
		categoria13.addItemListener(this);
		
		datos.add(categoria10);
		datos.add(categoria11);
		datos.add(categoria12);
		datos.add(categoria13);
		
		panel.add(categoria10);
		panel.add(categoria11);
		panel.add(categoria12);
		panel.add(categoria13);
		
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
	
	private Component crearPanelDatos() {
		JPanel panel = new JPanel (new GridLayout(7,1,0,0));
		datos = new ButtonGroup();
		descripcion = new JTextField(20);
		stock = new JTextField(20);
		stockMin = new JTextField (20);
		stockMedio = new JTextField(20);
		precio = new JTextField(20);
		
		descripcion.setFont(descripcion.getFont().deriveFont(18.0f));
		stock.setFont(stock.getFont().deriveFont(18.0f));
		stockMin.setFont(stockMin.getFont().deriveFont(18.0f));
		stockMedio.setFont(stockMedio.getFont().deriveFont(18.0f));
		precio.setFont(precio.getFont().deriveFont(18.0f));
		
		panel.add(crearGrupoBoton("CategoriaID"));
		panel.add(crearTextField(descripcion,"Descripcion"));
		panel.add(crearTextField(stock,"Stock"));
		panel.add(crearTextField(stockMin,"Stock_Min"));
		panel.add(crearTextField(stockMedio,"Stock_Medio"));
		panel.add(crearTextField(precio,"Precio"));
		
		return panel;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		String catId = null;
		
		switch(categoria){
		
		case "Refrescos":
			catId = "10";
			break;
		case "Bebidas Alcoholicas":
			catId = "11";
			break;
		case "Agua":
			catId = "12";
			break;
		case "Aliños":
			catId = "13";
			break;
		}
		
		Producto producto = new Producto(i,Integer.valueOf(catId),descripcion.getText(),Integer.valueOf(stock.getText()),
				Integer.valueOf(stockMin.getText()),Integer.valueOf(stockMedio.getText()),true,Float.valueOf(precio.getText()));
		
		
		
		if (e.getActionCommand().equals("ok")){
			
			if(catId != null && producto.getDescripcion() != null && producto.getStock() > 0 &&
					producto.getMinStock() >= 0 && producto.getMedioStock() > 0 && producto.getPrecio() > 0) {
				
			controlador.modelo.insertarDatos("Insert into productos values(" + "'" + producto.getProductoId() + "'" + "," + "'" 
			+ producto.getCategoriaId() + "'" + "," + "'" + producto.getDescripcion() + "'" + "," + "'" + producto.getStock() + "'" + "," 
			+ "'" + producto.getMinStock() + "'" + "," + "'" + producto.getMedioStock() + "'" + "," + "'" + producto.toString() + "'" + "," 
			+ "'" + producto.getPrecio() + "'" + ")", controlador.vista.conexion.conex);
				
			}
			dispose();
		}
		
		if (e.getActionCommand().equals("cancel")){
			dispose();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	
		categoria = ((AbstractButton) e.getItemSelectable()).getActionCommand();
	}

}
