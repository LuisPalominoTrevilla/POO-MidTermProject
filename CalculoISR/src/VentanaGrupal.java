import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaGrupal extends JFrame{
	private JButton retroceder, openFile, saveFile;
	private JPanel panelOpciones, panelSalir;
	private JFrame home;
	private JLabel empleados;
	private JFileChooser fc;
	private Empresa miEmpresa;
	
	public VentanaGrupal(Home home){
		super("Calcular ISR varios trabajadores");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.home = home;
		this.fc = new JFileChooser("C:\\");
		this.fc.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        
		this.panelOpciones = new JPanel();
		this.panelSalir = new JPanel();
		this.panelOpciones.setPreferredSize(new Dimension(400, 130));
		this.panelSalir.setPreferredSize(new Dimension(400, 50));
		this.empleados = new JLabel("");
		this.empleados.setBorder(BorderFactory.createEmptyBorder(10, 400, 15, 400));			// Agregar borde para estetica de la ventana
		this.retroceder = new JButton("Retroceder");
		this.retroceder.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				VentanaGrupal.this.setVisible(false);				// Hide the current window
				VentanaGrupal.this.home.setVisible(true);			// Set visible the home window
			}
		});
		
		this.openFile = new JButton("Cargar Archivo");
		this.saveFile = new JButton("Calcular ISR");
		this.saveFile.setEnabled(false);
		this.openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					VentanaGrupal.this.fc.setDialogTitle("Seleccione el archivo CSV que desea abrir");
					VentanaGrupal.this.fc.setSelectedFile(new File(""));
					int seleccion = VentanaGrupal.this.fc.showOpenDialog(VentanaGrupal.this);
					if(seleccion == JFileChooser.APPROVE_OPTION){
						VentanaGrupal.this.crearEmpresa(VentanaGrupal.this.fc.getSelectedFile());
						VentanaGrupal.this.saveFile.setEnabled(true);
						VentanaGrupal.this.empleados.setText("Se han agregado " + VentanaGrupal.this.miEmpresa.getEmpleadosActivos() + " empleados");
					}
				}catch(IOException ex){
					JOptionPane.showMessageDialog(VentanaGrupal.this,"Ocurrió un error al leer el archivo.", "Error de lectura",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGrupal.this.miEmpresa.hacerDeclaracionAnual();		// Mandar a hacer a todos los empleados su declaración anual
				try{
					VentanaGrupal.this.fc.setDialogTitle("Seleccione la ubicacion donde desea guardar el archivo generador");
					VentanaGrupal.this.fc.setSelectedFile(new File("resultados.csv"));
					int seleccion = VentanaGrupal.this.fc.showSaveDialog(VentanaGrupal.this);
					if(seleccion == JFileChooser.APPROVE_OPTION){
						VentanaGrupal.this.generarImpuestos(VentanaGrupal.this.fc.getSelectedFile());
					}
				}catch(IOException ex){
					JOptionPane.showMessageDialog(VentanaGrupal.this,"Ocurrió un error al guardar el archivo.", "Error de escritura",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		// Add components to panel
		this.panelOpciones.add(this.empleados);
		this.panelOpciones.add(this.openFile);
		this.panelOpciones.add(this.saveFile);
		this.panelSalir.add(this.retroceder);
		
		
		// Add panels to frame
		this.add(this.panelOpciones);
		this.add(this.panelSalir, BorderLayout.SOUTH);
		
		this.pack();
		
		// Las siguientes 2 lineas de cdigo fueron tomadas de http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setVisible(true);
	}
	
	private void crearEmpresa(File file) throws IOException{
		if(!file.toString().endsWith(".csv")){
			throw new IOException();					// Asegurarse que el metodo trabaja solo con archivos CSV
		}
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		this.miEmpresa = new Empresa();
		while((line = in.readLine()) != null){
			StringTokenizer token = new StringTokenizer(line, ",");
			this.miEmpresa.addEmpleado(token.nextToken(), token.nextToken(),Double.parseDouble(token.nextToken()), 
									 Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken()), 
									 Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken()), 
									 Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken()), 
									 Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken()), 
									 Double.parseDouble(token.nextToken()), token.nextToken(), Double.parseDouble(token.nextToken()));
		}
		
		in.close();
	}
	
	private void generarImpuestos(File file) throws IOException{
		if(!file.toString().endsWith(".csv")){
			file = new File(file + ".csv");				// En caso de que el archivo no tenga la extension csv, ponersela
		}
		PrintWriter out = new PrintWriter(new FileWriter(file));
		out.print("Nombre,RFC,Sueldo mensual,Ingreso anual,Aguinaldo,Aguinaldo exento,Aguinaldo gravado,Prima vacacional,Prima vacacional excenta,Prima vacacional gravada,Total ingresos gravan,Medicos y hospitales,Gastos funerarios,SGMM,Hipotecarios,Donativos,Subcuenta retiro,Transporte escolar,Nivel educativo,Maximo a deducir colegiatura,Colegiatura pagada,Total deducciones (sin retiro),Deduccion permitida 10%,Monto ISR,Cuota fija,Porcentaje excedente,Pago excedente,Total a pagar\n"); 		// Poner el encabezado del archivo 
		out.print(this.miEmpresa);
		out.close();
		
		// Abrir el archivo, recuperado de http://stackoverflow.com/questions/17276688/open-excel-from-java-application
		if (Desktop.isDesktopSupported()) {
		    Desktop.getDesktop().open(file);
		} else {
			JOptionPane.showMessageDialog(VentanaGrupal.this, "El archivo se ha generado y guardado con éxito.", "", JOptionPane.PLAIN_MESSAGE);
			
		}
	}
}
