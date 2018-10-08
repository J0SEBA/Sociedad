package sociedad2;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Vista extends JFrame implements Observer{
	
	Controlador controlador;
	Modelo modelo;
	Dimension dim;
	Image fondo;
	JPanel panel;
	JTabbedPane tabbed;
	Conexion conexion;
	Socio socio;
	
	public Vista(String s)
	{
		super(s);
		modelo = new Modelo(this);
		controlador=new Controlador(this,modelo);
		//conexion=new Conexion();
		this.setJMenuBar(crearBarraMenu());
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image fondo = toolkit.createImage("icons/sozidadia.jpg"); 
		panel = new JPanel(new BorderLayout());
		panel.add(crearPanelBotones(),BorderLayout.CENTER);
		this.setContentPane(panel);
		dim=this.getToolkit().getScreenSize();
		this.setSize(dim);	
		this.setLocation(0,0);
	
		this.setVisible(false);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JMenu crearMenuSalir() {
		JMenuItem op;
		JMenu menuSalir = new JMenu ("Cerrar sesion");
		
		op=menuSalir.add("Salir");
		
		
		return menuSalir;
	}
	
	public Conexion getConexion()
	{
		return conexion;
	}

	
	private Container crearPanelBotones() {
		MiPanel panelBotones=new MiPanel(fondo);
		panelBotones.setLayout(new GridLayout(6,2,10,10));
		
		
		panelBotones.add(crearBoton("VER PRODUCTOS",5,20,300,150));
		panelBotones.add(crearBoton("VER SOCIOS",355,20,300,150));
		panelBotones.add(crearBoton("VER TU HISTORIAL DE CONSUMICIONES",705,20,300,150));
		panelBotones.add(crearBoton("VER TODAS LAS CONSUMICIONES",5,180,300,150));
		
		
		panelBotones.add(crearBoton("REGISTRAR CONSUMICION",5,340,300,150));
		panelBotones.add(crearBoton("REGISTRAR PEDIDO",355,340,300,150));
		panelBotones.add(crearBoton("REGISTRAR NUEVO SOCIO",705,340,300,150));
		panelBotones.add(crearBoton("REGISTRAR NUEVO PRODUCTO",5,500,300,150));
		
		panelBotones.add(crearBoton("DAR SOCIO ALTA",355,500,300,150));
		panelBotones.add(crearBoton("DAR PRODUCTO ALTA",705,500,300,150));
		
		
		return panelBotones;
	}
	
	
	private Component crearBoton(String titulo, int posx,int posy,int tama�ox,int tama�oy) {
		
		JButton botones=new JButton(titulo);

		botones.addActionListener(controlador);
		
		botones.setLocation(posx, posy);
		botones.setSize(tama�ox, tama�oy);
	
		return botones;
	}
	
	private JMenuBar crearBarraMenu() {
		JMenuBar barra = new JMenuBar();
		
		barra.add(Box.createHorizontalGlue());
		barra.add (crearMenuSalir());
		
		return barra;
		
	}
	public void go(Socio socio)
	{
		this.socio = socio;
		this.setVisible(true);
	}
	
	public Socio getSocio() {
		
		return socio;
		
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vista vista=new Vista("Testeo");
		Login log=new Login(vista);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
