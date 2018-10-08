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

public class DialogoEditarProducto extends JDialog implements ActionListener {
 
 JTextField descripcion,stock,stockMin,stockMedio,precio;
 Vista vista;
 Producto producto;
 
 
 public DialogoEditarProducto(Vista vista,Producto producto){
  
  this.vista = vista;
  this.producto = producto;
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
  JPanel panel = new JPanel (new GridLayout(6,1,0,0));
  descripcion = new JTextField(producto.getDescripcion(), 20);
  stock = new JTextField(String.valueOf(producto.getStock()),20);
  stockMin = new JTextField (String.valueOf(producto.getMinStock()),20);
  stockMedio = new JTextField(String.valueOf(producto.getMedioStock()),20);
  precio = new JTextField(String.valueOf(producto.getPrecio()),20);
  
  panel.add(crearTextField(descripcion," Descripcion"));
  panel.add(crearTextField(stock," Stock"));
  panel.add(crearTextField(stockMin," Stock Minimo"));
  panel.add(crearTextField(stockMedio," Stock Medio"));
  panel.add(crearTextField(precio," Precio"));
  
  
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
  
  producto = new Producto(producto.getProductoId(),
    producto.getCategoriaId(),
    descripcion.getText(),
    Integer.valueOf(stock.getText()),
    Integer.valueOf(stockMin.getText()),
    Integer.valueOf(stockMedio.getText()),
    true,
    Float.valueOf(precio.getText()));
  
  if(e.getActionCommand().equals("Actualizar")) {
   
   
 
   if(producto.getDescripcion() != null) {
    
    vista.modelo.insertarDatos("Update productos set " + "descripcion = " + "'" + producto.getDescripcion()+ "'" + "," +
    "Stock = " + "'" + producto.getStock() + "'" + "," +
    "Stock_min = " + "'" + producto.getMinStock() + "'" + "," +
    "Stock_medio =" + "'" + producto.getMedioStock() + "'" + "," +
    "Precio = " + "'" + producto.getPrecio() + "'" 
    +" Where productoId = " + producto.getProductoId(), vista.conexion.conex);
    
    dispose();
    JOptionPane.showMessageDialog(vista, "Producto Actualizado");
    
    
   }
   
   else {
    
    JOptionPane.showMessageDialog(vista, "Datos Incorrectos");
   }
   
  
  }
  if(e.getActionCommand().equals("Cancelar")) dispose();
  
 }


}
