package tp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import tp.controller.Mensaje;

public abstract class PanelPersonalizado extends JPanel{

	private static final long serialVersionUID = 1L;
	String fileFondo;;

	public  PanelPersonalizado() {
		super();
		this.fileFondo =  "icon/fondo.png";
	}
	
	
	//Este metodo dibuja la imagen de fondo
	@Override
	protected void paintComponent(Graphics g) {
		//http://bg.siteorigin.com/
		//https://tips4java.wordpress.com/2008/10/12/background-panel/
		super.paintComponent(g);
		Image imagen = new ImageIcon(fileFondo).getImage();

		Dimension d = this.getSize();
		int width = imagen.getWidth( null );
		int height = imagen.getHeight( null );

		for (int x = 0; x < d.width; x += width)
		{
			for (int y = 0; y < d.height; y += height)
			{
				g.drawImage( imagen, x, y, null, null );
			}
		}
	}

	//CREAR FORMATTEDTEXTS-------------------------------------------------------------------------------------------------------------------------

	public static JPanel crearPanelInterno(String titulo) {
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setOpaque(false);
		
		Border borde2 = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.decode("#33658a"));
		borde2 = BorderFactory.createTitledBorder(borde2, titulo, TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20),  Color.decode("#33658a"));
		panel2.setBorder(borde2);
		return panel2;
	}

	public static JFormattedTextField crearCampoDinero() {

		NumberFormat format3 = NumberFormat.getNumberInstance(Locale.US);
		format3.setGroupingUsed(false);

		JFormattedTextField temp = new JFormattedTextField(
				new DefaultFormatterFactory(
						new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US)), 
						new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US)), 
						new NumberFormatter(format3)));

		temp.addPropertyChangeListener("value", e ->{
			if(temp.getValue() != null) 
				temp.setValue(((Number)temp.getValue()).doubleValue());
		});

		/*
		 * temp.addFocusListener(new FocusAdapter() { public void
		 * focusLost(java.awt.event.FocusEvent e) { if(temp.getValue() != null) {
		 * temp.firePropertyChange("value", 999.0, 999.0); //double valor =
		 * ((Number)temp.getValue()).doubleValue(); //temp.setValue(valor);
		 * 
		 * }else { temp.setValue(0.0); }
		 * 
		 * }; });
		 */

		return temp;

	}

	public static JFormattedTextField crearCampoDouble() {
		//https://stackoverflow.com/questions/27056539/how-to-add-only-double-values-in-jformattedtextfield
		NumberFormat format1 = DecimalFormat.getInstance(Locale.US);
		format1.setMinimumFractionDigits(2);
		format1.setMaximumFractionDigits(2);
		
		//format1.setRoundingMode(RoundingMode.HALF_UP);

		NumberFormat format3 = NumberFormat.getNumberInstance(Locale.US);
		format3.setGroupingUsed(false);

		JFormattedTextField temp = new JFormattedTextField(
				new DefaultFormatterFactory(
						new NumberFormatter(format1), 
						new NumberFormatter(format1), 
						new NumberFormatter(format3)));

		return temp;
	}

	public static  JFormattedTextField crearCampoFecha() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField temp = new JFormattedTextField(format);
		temp.setToolTipText("Formato de fecha: dd/MM/yyyy");
		return temp;
	}

	//CREAR POPUPS-------------------------------------------------------------------------------------------------------------------------

	static public void notificacionPopUp(Mensaje m) {
		if(m.exito()) 
			JOptionPane.showMessageDialog(null, "Operaci�n exitosa","�xito",JOptionPane.INFORMATION_MESSAGE,emoji("icon/success.png", 32,32));			
		else
			JOptionPane.showMessageDialog(null, m.texto(),"Error",JOptionPane.ERROR_MESSAGE,PanelPersonalizado.emoji("icon/error.png", 32,32));
	}

	static public String ingresoPopUp(String mensaje) {
		return (String) JOptionPane.showInputDialog(null, mensaje, "Ingreso", JOptionPane.OK_CANCEL_OPTION, emoji("icon/pencil.png", 32,32), null, null);	 
	}

	
	static public String ingresoComboPopUp(String mensaje, String []lista) {
		return (String) JOptionPane.showInputDialog(null, mensaje, "Ingreso", JOptionPane.OK_CANCEL_OPTION, emoji("icon/pencil.png", 32,32), lista, lista[0]);
	}


	static public int eliminarPopUp(String mensaje) {
		return JOptionPane.showOptionDialog(null, mensaje, "Eliminar",JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE, PanelPersonalizado.emoji("icon/warning.png", 32,32), null, null);
	}

	static public String seleccionPopUp(String[] valores) {
		return (String) JOptionPane.showInputDialog(null, "Seleccione un valor de la lista", "Ingreso",JOptionPane.OK_CANCEL_OPTION, emoji("icon/pencil.png", 32,32), valores, null);
	}

	//CREAR BOTONES-------------------------------------------------------------------------------------------------------------------------
	
	static public JButton botonEliminar(String mensaje) {
		JButton boton = new JButton(mensaje,emoji("icon/trash.png", 24,24));
		boton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		return boton;
	}

	static public JButton botonAgregar(String mensaje) {
		JButton boton = new JButton(mensaje,emoji("icon/save.png", 24,24));
		boton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		return boton;
	}

	static public JButton botonEditar(String mensaje) {
		JButton boton = new JButton(mensaje,emoji("icon/pencil.png",24,24));
		boton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		return boton;
	}
	
	static public JButton botonBusqueda(String mensaje) {
		JButton boton = new JButton(mensaje,emoji("icon/search.png",24,24));
		boton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		return boton;
	}

	//USAR ESTE M�TODO SOLO CON JPANEL QUE TENGAN GRIDBAGLAYOUT
	static public void colocar(
			int column,
			int row, 
			int width, 
			int height, 
			double weightX, 
			double weightY, 
			int ipadx, 
			int ipady, 
			int fill, 
			int anchor, 
			JPanel panel, 
			JComponent comp/*,
			int marginTop, int marginLeft, int marginBottom, int marginRight */){
		//https://stackoverflow.com/questions/45175343/how-do-you-add-empty-cells-to-gridbaglayout
		//GridBagConstraints constraints = new GridBagConstraints();
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = column;      // column to start
		c1.gridy = row;         // row to start
		c1.gridwidth = width;   // number of cells wide
		c1.gridheight = height; // number of cells tall
		c1.weightx = weightX;   // when size is changed, grow in x direction
		c1.weighty = weightY;   // when size is changed, grow in y direction
		c1.ipadx = ipadx;       // espacio extra en x
		c1.ipady = ipady;		  // espacio extra en y
		c1.fill = fill;         // 0 NONE, 1 BOTH, 2 HORIZONTAL, 3 VERTICAL
		c1.anchor = anchor; 	  //10 CENTER
		c1.insets = new Insets(5,10,5,10);
//		c1.insets = new Insets( marginTop, marginLeft, marginBottom, marginRight );
		panel.add(comp,c1);  

	}

	//CARGAR EMOJIS-------------------------------------------------------------------------------------------------------------------------
	
	static public ImageIcon emoji(String fileName, int width, int height) {
		Image imagen = new ImageIcon(fileName).getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
	}


}
