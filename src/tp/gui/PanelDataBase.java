package tp.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;



import tp.controller.Mensaje;
import tp.dao.DataBase;
import tp.dao.DataBase.Modo;

public class PanelDataBase extends PanelPersonalizado{

	private static final long serialVersionUID = 1L;
	
	private JLabel titulo = crearTitulo("Conexión a Base de Datos");
	private JLabel texto_url = new JLabel("URL:",SwingConstants.RIGHT);
	private JLabel texto_user= new JLabel("User:",SwingConstants.RIGHT);
	private JLabel texto_password = new JLabel("Password:",SwingConstants.RIGHT);
	private JLabel texto_modo = new JLabel("Modo de operación:",SwingConstants.RIGHT);

	//CAMPOS ENTRADA
	private JTextField campo_url = new JTextField();
	private JTextField campo_user = new JTextField();
	private JTextField campo_password = new JTextField();
	private JRadioButton boton_load = new JRadioButton("Load");
	private JRadioButton boton_reset = new JRadioButton("Reset");
	private ButtonGroup grupo = new ButtonGroup();

	private JTextArea nota = new JTextArea();
	private JButton boton_guardar = botonAgregar("Guardar cambios");

	public PanelDataBase() {
		this.setLayout(new GridBagLayout());

		//RADIO BUTTONS
		grupo.add(boton_load);
		grupo.add(boton_reset);

		if(DataBase.url!=null && DataBase.user!= null && DataBase.password!=null) {
			campo_url.setText(DataBase.url);
			campo_user.setText(DataBase.user);
			campo_password.setText(DataBase.password);
			if(DataBase.modo_operacion == Modo.LOAD)
				boton_load.setSelected(true);
			else
				boton_reset.setSelected(true);
		}
		else{
			boton_load.setSelected(true);
		}
		campo_password.setVisible(true);

		//BOTON GUARDAR

		boton_guardar.addActionListener( e-> {
			if(campo_url.getText() == null || campo_url.getText().length()==0)
				notificacionPopUp(new Mensaje(false, "Ingrese un url"));

			if(campo_user.getText() == null || campo_user.getText().length()==0)
				notificacionPopUp(new Mensaje(false, "Ingrese un usuario"));

			if(campo_password.getText() == null || campo_password.getText().length()==0)
				notificacionPopUp(new Mensaje(false, "Ingrese una contraseña"));

			DataBase.url = campo_url.getText();
			DataBase.user = campo_user.getText();
			DataBase.password = campo_password.getText();
			

			if(boton_load.isSelected())
				DataBase.modo_operacion = Modo.LOAD;
			else
				DataBase.modo_operacion = Modo.RESET;


			Mensaje m = DataBase.probarConexion();

			if(m.exito())
				m = DataBase.escribirJson();
			if(m.exito())
				DataBase.inicializarTablas();


			notificacionPopUp(m);


		});

		//NOTA
		nota.setText("El modo LOAD utiliza normalmente la BD, creando y poblando las tablas solo si es necesario. "
				+ "El modo RESET elimina, crea y puebla todas las tablas cada vez que se reinicia el programa (la aplicación demorará un poco más en arrancar)");
		nota.setAutoscrolls(false);
		nota.setLineWrap(true);
		nota.setWrapStyleWord(true);
		nota.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		nota.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

	//PANEL1
		
		setearFuente(new JComponent[] {texto_url,texto_user,texto_password,texto_modo});

		JPanel panel1 = crearPanelInterno("Configuración");
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel1,texto_url);
		colocar(1,0,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel1,campo_url);

		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel1,texto_user);
		colocar(1,1,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel1,campo_user);

		colocar(0,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel1,texto_password);
		colocar(1,2,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel1,campo_password);

		colocar(0,3,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel1,texto_modo);
		colocar(1,3,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel1,boton_load);
		colocar(2,3,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.WEST,panel1,boton_reset);

		colocar(1,4,2,1,0,0,0,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.EAST,panel1,nota);
		colocar(2,5,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel1,boton_guardar);

	//ORGANIZACION DE PANELES
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,0,0,0 ,GridBagConstraints.BOTH,10,this,panel1);

	}


}
