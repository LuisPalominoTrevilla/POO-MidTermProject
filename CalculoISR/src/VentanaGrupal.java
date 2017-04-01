import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaGrupal extends JFrame{
	private JButton retroceder, openFile, saveFile;
	private JPanel panelOpciones, panelSalir;
	private JFrame home;
	private JFileChooser fc;
	private Empresa miEmpresa;
	
	public VentanaGrupal(Home home){
		super("Varios Trabajadores");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.home = home;
		this.fc = new JFileChooser("C:\\Users\\Luis Palomino\\Desktop\\TEC\\Segundo semestre");
		this.fc.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        
		this.panelOpciones = new JPanel();
		this.panelSalir = new JPanel();
		this.panelOpciones.setPreferredSize(new Dimension(400, 300));
		this.panelSalir.setPreferredSize(new Dimension(400, 100));
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
						JOptionPane.showMessageDialog(VentanaGrupal.this, "El archivo se ha cargado con éxito.", "", JOptionPane.PLAIN_MESSAGE);
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
					VentanaGrupal.this.fc.setSelectedFile(new File(".csv"));
					int seleccion = VentanaGrupal.this.fc.showSaveDialog(VentanaGrupal.this);
					if(seleccion == JFileChooser.APPROVE_OPTION){
						VentanaGrupal.this.generarImpuestos(VentanaGrupal.this.fc.getSelectedFile());
						JOptionPane.showMessageDialog(VentanaGrupal.this, "El archivo se ha generado y guardado con éxito.", "", JOptionPane.PLAIN_MESSAGE);
					}
				}catch(IOException ex){
					JOptionPane.showMessageDialog(VentanaGrupal.this,"Ocurrió un error al guardar el archivo.", "Error de lectura",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		// Add components to panel
		this.panelOpciones.add(this.openFile);
		this.panelSalir.add(this.retroceder);
		this.panelOpciones.add(this.saveFile);
		
		
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
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
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
		out.print(this.miEmpresa);
		out.close();
	}
}
