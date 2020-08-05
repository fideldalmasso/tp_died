package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.app.App;
import tp.controller.Mensaje;
import tp.controller.PlantaController;
import tp.controller.RutaController;
import tp.dominio.Empresa;
import tp.dominio.Planta;

public class PanelAnalisis extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;
	
	//DOMINIO
	private PlantaController pc = new PlantaController();
	private RutaController rc= new RutaController();
	private Empresa empresa = new Empresa(rc.getAll(),pc.getAll());
	private String[] items = pc.getAll().parallelStream().map(p->p.getNombre()).collect(Collectors.toList()).toArray(new String[0]);
	private String[] items2 = {"Menor distancia","Menor duracion"};
	//CABECERA
	private JPanel panel_cabecera = new JPanel();
	private JLabel titulo = new JLabel("Analisis",SwingConstants.CENTER);
	
	private JButton boton_plant_rank = new JButton("Plant Rank");
	private JButton boton_caminos = new JButton("Caminos Minimos");
	private JButton boton_matriz = new JButton("Matriz Caminos Minimos");
	private JButton boton_flujo = new JButton("Flujo Maximo");
	
	//PAGE RANK
	private JPanel panel_plantas = new JPanel();
	private PlantaTM table_model_plantas = new PlantaTM();
	private JScrollPane scroll_pane_plantas = new JScrollPane();
	private JTable tabla_plantas = new JTable();
	
	//CAMINOS MINIMOS
	private JPanel panel_caminos = new JPanel();
	private CaminoTM table_model_caminos = new CaminoTM();
	private JScrollPane scroll_pane_caminos = new JScrollPane();
	private JTable tabla_caminos = new JTable();
	private JLabel texto_planta_origen_caminos = new JLabel("Planta Origen:");
	private JComboBox<String> campo_planta_origen_caminos = new JComboBox<String>(items);
	private JLabel texto_planta_destino_caminos = new JLabel("Planta Destino:");
	private JComboBox<String> campo_planta_destino_caminos = new JComboBox<String>(items);
	private JButton boton_calcular_caminos = new JButton("Calcular Caminos");
	
	//MATRIZ CAMINOS MINIMOS
	private JPanel panel_matriz = new JPanel();
	private JPanel matriz_p = new JPanel();
	private JLabel texto_modo = new JLabel("Modo:");
	private JComboBox<String> campo_modo = new JComboBox<String>(items2);
	
	//FLUJO MAXIMO
	private JPanel panel_flujo = new JPanel();
	private JLabel texto_planta_origen_flujo = new JLabel("Planta Origen:");
	private JComboBox<String> campo_planta_origen_flujo = new JComboBox<String>(items);
	private JLabel texto_planta_destino_flujo = new JLabel("Planta Destino:");
	private JComboBox<String> campo_planta_destino_flujo = new JComboBox<String>(items);
	private JLabel texto_flujo = new JLabel("");
	private JButton boton_calcular_flujo = new JButton("Calcular Flujo");
	
	private static Color color_borde =  Color.decode("#33658a");
	private static Color color_titulo =  Color.decode("#dd1c1a");
	private static Color color_letras = Color.BLACK;
	
	private void cambiarPanel(PanelPersonalizado p1) {
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        App app = (App) frame;
        app.cambiarPanel(p1);
	}
	
	private void actualizarTabla(JTable tabla, AbstractTableModel modelo) {
		modelo.fireTableDataChanged();
		tabla.repaint();
	}
	
	public PanelAnalisis() {
			super();
			this.setLayout(new GridBagLayout());
			//CABECERA
			titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
			titulo.setForeground(color_titulo);
			
			Border borde0 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
			borde0 = BorderFactory.createTitledBorder(borde0, "Seleccionar Analisis", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
			panel_cabecera.setBorder(borde0);
			
			Border borde = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
			borde = BorderFactory.createTitledBorder(borde, "", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
			
			//PAGE RANK
			this.table_model_plantas = new PlantaTM();
			this.tabla_plantas = new JTable();
			this.tabla_plantas.setModel(this.table_model_plantas);
			this.tabla_plantas.setIgnoreRepaint(false);
			this.tabla_plantas.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
			this.tabla_plantas.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
			this.tabla_plantas.setRowHeight(20);
			tabla_plantas.getTableHeader().setReorderingAllowed(false);
			this.scroll_pane_plantas = new JScrollPane(this.tabla_plantas);
			
			panel_plantas.setBorder(borde);
			colocar(3,0,1,1,0,0,0,0,GridBagConstraints.BOTH, 10, panel_plantas, scroll_pane_plantas);
			
			//CAMINOS MINIMOS
			panel_caminos.setLayout(new GridBagLayout());
			panel_caminos.setOpaque(false);
			
			this.table_model_caminos = new CaminoTM();
			this.tabla_caminos = new JTable();
			this.tabla_caminos.setModel(this.table_model_caminos);
			this.tabla_caminos.setIgnoreRepaint(false);
			this.tabla_caminos.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
			this.tabla_caminos.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
			this.tabla_caminos.setRowHeight(20);
			tabla_caminos.getTableHeader().setReorderingAllowed(false);
			this.scroll_pane_caminos = new JScrollPane(this.tabla_caminos);
			
			campo_planta_origen_caminos = new JComboBox<String>();
			campo_planta_origen_caminos.setSelectedItem(null);
			AutoCompletion.enable(campo_planta_origen_caminos);
			
			campo_planta_destino_caminos = new JComboBox<String>();
			campo_planta_destino_caminos.setSelectedItem(null);
			AutoCompletion.enable(campo_planta_destino_caminos);
			
			colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_caminos, texto_planta_origen_caminos);
			colocar(1,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_caminos, campo_planta_origen_caminos);
			colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_caminos,	texto_planta_destino_caminos);
			colocar(3,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_caminos, campo_planta_destino_caminos);
			colocar(4,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_caminos, boton_calcular_caminos);
			colocar(0,1,1,5,0,0,0,0,GridBagConstraints.BOTH, 10, panel_caminos, scroll_pane_caminos);
			panel_plantas.setBorder(borde);
			
			//MATRIZ CAMINOS MINIMOS
			panel_matriz.setLayout(new GridBagLayout());
			panel_matriz.setOpaque(false);
			colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_matriz, texto_modo);
			colocar(1,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_matriz, campo_modo);
			panel_matriz.setBorder(borde);
			
			//FLUJO MAXIMO
			panel_flujo.setLayout(new GridBagLayout());
			panel_flujo.setOpaque(false);
			colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_flujo, texto_planta_origen_flujo);
			colocar(1,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_flujo, campo_planta_origen_flujo);
			colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_flujo, texto_planta_destino_flujo);
			colocar(3,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_flujo, campo_planta_destino_flujo);
			colocar(4,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_flujo, boton_calcular_flujo);
			colocar(0,1,5,0,0,0,0,0,GridBagConstraints.NONE, 10, panel_flujo, texto_flujo);
			panel_flujo.setBorder(borde);
			
			//PANEL CABECERA
			panel_cabecera.setLayout(new GridBagLayout());
			panel_cabecera.setOpaque(false);
			colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_cabecera, boton_plant_rank);
			colocar(1,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_cabecera, boton_caminos);
			colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_cabecera, boton_matriz);
			colocar(3,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel_cabecera, boton_flujo);
			
			//ORGANIZACION DE PANELES
			colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
			colocar(0,1,1,1,1,1,0,10 ,GridBagConstraints.NONE,10,this,panel_cabecera);
			
//BOTONES CABECERA
			//BOTON PLANT RANK
			boton_plant_rank.addActionListener(e->{
				this.remove(panel_caminos);
				this.remove(panel_flujo);
				this.remove(panel_matriz);
				colocar(0,1,1,1,1,1,0,10 ,GridBagConstraints.NONE,10,this,panel_plantas);
				this.empresa.plantRank();
				this.empresa.getPlantas().sort((p1,p2)->p1.getPlant_rank().compareTo(p2.getPlant_rank()));
				table_model_plantas = new PlantaTM(this.empresa.getPlantas());
				actualizarTabla(tabla_plantas,table_model_plantas);
			});
			
			//BOTON CAMINOS MINIMOS
			boton_caminos.addActionListener(e->{
				this.remove(panel_plantas);
				this.remove(panel_flujo);
				this.remove(panel_matriz);
				colocar(0,1,1,1,1,1,0,10 ,GridBagConstraints.NONE,10,this,panel_caminos);
			});
			
			//BOTON MATRIZ
			boton_matriz.addActionListener(e->{
				this.remove(panel_caminos);
				this.remove(panel_flujo);
				this.remove(panel_plantas);
				colocar(0,1,1,1,1,1,0,10 ,GridBagConstraints.NONE,10,this,panel_matriz);
			});
			
			//BOTON FLUJO MAXIMO
			boton_flujo.addActionListener(e->{
				this.remove(panel_caminos);
				this.remove(panel_plantas);
				this.remove(panel_matriz);
				colocar(0,1,1,1,1,1,0,10 ,GridBagConstraints.NONE,10,this,panel_flujo);
			});
			
//BOTONES CAMINOS MINIMOS
			//BOTON CALCULAR CAMINOS
			boton_calcular_caminos.addActionListener(e->{
				String nombre_destino = "";
				String nombre_origen = "";
				if(campo_planta_origen_caminos.getSelectedIndex()==-1) {
					notificacionPopUp(new Mensaje(false,"Debe seleccionar una planta de origen."));
					return;
				} else {
					nombre_origen = (String) campo_planta_origen_caminos.getSelectedItem();
				}
				if(campo_planta_destino_caminos.getSelectedIndex()==-1) {
					notificacionPopUp(new Mensaje(false,"Debe seleccionar una planta de destino."));
					return;
				} else {
					nombre_destino = (String) campo_planta_destino_caminos.getSelectedItem();
				}
				table_model_caminos.recargarTabla(nombre_origen, nombre_destino);
				actualizarTabla(tabla_caminos,table_model_caminos);
			});
			
//BOTONES MATRIZ
			campo_modo.addActionListener(e->{
				String modo = (String) campo_modo.getSelectedItem();
				Double[][] matriz_d = null;
				Integer tam =this.empresa.getPlantas().size();
				Map<Integer,Planta> m = new HashMap<Integer,Planta>();
				
				if(modo=="Menor distancia") {
					matriz_d = this.empresa.matrizCaminoMinimo(0,m);
				}else if(modo=="Menor duracion"){
					matriz_d = this.empresa.matrizCaminoMinimo(1,m);
					
				}else {
					notificacionPopUp(new Mensaje(false,"Debe seleccionar un modo."));
					return;
				}
				
				matriz_p.setLayout(new GridLayout(tam+1,tam+1));
				for(int i=1;i<tam+1;i++) {
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx=i;
					gbc.gridy=0;
					matriz_p.add(new JLabel(m.get(i-1).getNombre()));
					GridBagConstraints gbc2 = new GridBagConstraints();
					gbc2.gridx=0;
					gbc2.gridy=i;
					matriz_p.add(new JLabel(m.get(i-1).getNombre()));
				}
				for(int i=1;i<tam+1;i++) {
					for(int j=1;j<tam+1;j++) {
						GridBagConstraints gbc = new GridBagConstraints();
						gbc.gridx=i;
						gbc.gridy=0;
						matriz_p.add(new JLabel(Double.toString(matriz_d[i-1][j-1])),gbc);
					}
				}
			});

//BOTONES FLUJO MAXIMO
			boton_calcular_flujo.addActionListener(e->{
				String nombre_destino = "";
				String nombre_origen = "";
				if(campo_planta_origen_flujo.getSelectedIndex()==-1) {
					notificacionPopUp(new Mensaje(false,"Debe seleccionar una planta de origen."));
					return;
				} else {
					nombre_origen = (String) campo_planta_origen_flujo.getSelectedItem();
				}
				if(campo_planta_destino_flujo.getSelectedIndex()==-1) {
					notificacionPopUp(new Mensaje(false,"Debe seleccionar una planta de destino."));
					return;
				} else {
					nombre_destino = (String) campo_planta_destino_flujo.getSelectedItem();
				}
				Double flujo_maximo = this.empresa.flujoMaximo(nombre_origen, nombre_destino);
				texto_flujo = new JLabel(Double.toString(flujo_maximo));
			});
		}

}
