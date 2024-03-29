/*
 * Autores: 
 * Luis Palomino Trevilla A01228574
 * Emanuel Estrada Larios A01633605 
 * 
 * Porcentaje de participación:
 * Luis Palomino - 55%
 * Emanuel Estrada - 45%
 * 
 * Fecha: 5 de abril de 2017
 * 
 * Comentarios: ninguno
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class PanelResultados extends JPanel{
	private JPanel panelIngresos, panelDeducciones, panelImpuesto, panelBotones;
	private JLabel[] labelsIngresos, labelsDeducciones, labelsImpuestos;
	private JTextField[] textFieldsIngresos, textFieldsDeducciones, textFieldsImpuestos;
	private String printString;
	private JButton  guardar, copiar;
	private Empleado empleado;
	
	public PanelResultados(){
		super();
		this.setPreferredSize(new Dimension(700,600));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,30));
        this.setBackground(new Color(229, 220, 220));
        this.printString = "";
        // Aniadir titulo al panel resultados
        JLabel titleResultados = new JLabel("-----------------------------------------    RESULTADOS    -----------------------------------------");
        titleResultados.setFont(new Font("default", Font.BOLD, 17));
        titleResultados.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        this.add(titleResultados);

        /*    Posiciones de labelsImpuestos y textFieldsImpuestos:
         * 0 - Deduccion Permitida
         * 1 - Monto Isr
         * 2 - Cuota fija
         * 3 - Porcentaje excedente
         * 4 - Pago excedente
         * 5 - Total a pagar
         */
        this.textFieldsImpuestos = new JTextField[6];
        this.labelsImpuestos = new JLabel[6];
        
        /*    Posiciones de labelsIngresos y textFieldsIngresos:
         * 0 - Nombre
         * 1 - RFC
         * 2 - Sueldo mensual
         * 3 - Ingreso anual
         * 4 - Aguinaldo
         * 5 - Aguinaldo excento
         * 6 - Aguinaldo gravado
         * 7 - Prima vacacional
         * 8 - Prima vacacional excenta
         * 9 - Prima vacacional gravada
         * 10 - Total ingresos gravados
         */
        this.textFieldsIngresos = new JTextField[11];
        this.labelsIngresos = new JLabel[11];
        
        /*    Posiciones de labelsDeducciones y textFieldsDeducciones:
         * 0 - Medicos y hospitales
         * 1 - Gastos funerarios
         * 2 - SGMM
         * 3 - Hipotecarios
         * 4 - Donativos
         * 5 - Subcuenta de retiro
         * 6 - Transporte escolar
         * 7 - Nivel Educativo
         * 8 - Maximo a deducir colegiatura
         * 9 - Colegiatura pagada
         * 10 - Total deducciones (sin retiro)
         */
        this.textFieldsDeducciones = new JTextField[11];
        this.labelsDeducciones = new JLabel[11];
        
		// Inicializar paneles
        
        this.panelIngresos = new JPanel(new BorderLayout());
		this.panelIngresos.setPreferredSize(new Dimension(300, 325));
		this.panelIngresos.setBackground(new Color(229, 220, 220));
		this.add(this.panelIngresos);
		
		this.panelDeducciones = new JPanel(new BorderLayout());
        this.panelDeducciones.setPreferredSize(new Dimension(300, 325));
        this.panelDeducciones.setBackground(new Color(229, 220, 220));
        this.add(this.panelDeducciones);
		
		this.panelImpuesto = new JPanel(new BorderLayout());
		this.panelImpuesto.setPreferredSize(new Dimension(300, 200));
		this.panelImpuesto.setBackground(new Color(229, 220, 220));
		this.add(this.panelImpuesto);
		
		this.panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.panelBotones.setPreferredSize(new Dimension(300, 200));
		this.panelBotones.setBackground(new Color(229, 220, 220));
		this.add(this.panelBotones);
		
		
		
		// Inicializar Componentes label
		this.labelsIngresos[0] = new JLabel("Nombre: ");
		this.labelsIngresos[1] = new JLabel("RFC: ");
		this.labelsIngresos[2] = new JLabel("Sueldo mensual:");
		this.labelsIngresos[3] = new JLabel("Ingreso anual: ");
		this.labelsIngresos[4] = new JLabel("Aguinaldo: ");
		this.labelsIngresos[5] = new JLabel("Aguinaldo excento: ");
		this.labelsIngresos[6] = new JLabel("Aguinaldo gravado: ");
		this.labelsIngresos[7] = new JLabel("Prima vacacional: ");
		this.labelsIngresos[8] = new JLabel("Prima vacacional excenta: ");
		this.labelsIngresos[9] = new JLabel("Prima vacacional gravada: ");
		this.labelsIngresos[10] = new JLabel("Total Ingresos Gravados: ");
		this.labelsDeducciones[0] = new JLabel("Medicos y hospitales: ");
		this.labelsDeducciones[1] = new JLabel("Gastis funerarios: ");
		this.labelsDeducciones[2] = new JLabel("SGMM: ");
		this.labelsDeducciones[3] = new JLabel("Hipotecarios: ");
		this.labelsDeducciones[4] = new JLabel("Donativos: ");
		this.labelsDeducciones[5] = new JLabel("Subcuenta de retiro:");
		this.labelsDeducciones[6] = new JLabel("Transporte escolar: ");
		this.labelsDeducciones[7] = new JLabel("Nivel educativo: ");
		this.labelsDeducciones[8] = new JLabel("Maximo a deducir colegiatura: ");
		this.labelsDeducciones[9] = new JLabel("Colegiatura pagada: ");
		this.labelsDeducciones[10] = new JLabel("Total deducciones (sin retiro):");
        this.labelsImpuestos[0] = new JLabel("Deducciones Permitidas:");
        this.labelsImpuestos[1] = new JLabel("Monto calculo de ISR:");
        this.labelsImpuestos[2] = new JLabel("Cuota fija:");
        this.labelsImpuestos[3] = new JLabel("Porcentaje excedente:");
        this.labelsImpuestos[4] = new JLabel("Pago excedente:");
        this.labelsImpuestos[5] = new JLabel("Total a pagar:");
        this.labelsImpuestos[5].setFont(new Font("default", Font.BOLD, 16));
        
        for(int i = 0; i < this.labelsIngresos.length; i++){
        	this.labelsIngresos[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
        }
        for(int i = 0; i < this.labelsDeducciones.length; i++){
        	this.labelsDeducciones[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
        }
        for(int i = 0; i < this.labelsImpuestos.length; i++){
        	this.labelsImpuestos[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
        }
        
        
        // Inicializar Componentes textField
        for(int i = 0; i < this.textFieldsIngresos.length; i++){
        	this.textFieldsIngresos[i] = new JTextField(10);
        	this.textFieldsIngresos[i].setEditable(false);
        	this.textFieldsIngresos[i].setBackground(new Color(249, 249, 249));
        	this.textFieldsIngresos[i].setMaximumSize(this.textFieldsIngresos[i].getPreferredSize());
        }
        for(int i = 0; i < this.textFieldsDeducciones.length; i++){
        	this.textFieldsDeducciones[i] = new JTextField(10);
        	this.textFieldsDeducciones[i].setEditable(false);
        	this.textFieldsDeducciones[i].setBackground(new Color(249, 249, 249));
        	this.textFieldsDeducciones[i].setMaximumSize(this.textFieldsDeducciones[i].getPreferredSize());
        }
        for(int i = 0; i < this.textFieldsImpuestos.length; i++){
        	this.textFieldsImpuestos[i] = new JTextField(10);
        	this.textFieldsImpuestos[i].setEditable(false);
        	this.textFieldsImpuestos[i].setBackground(new Color(249, 249, 249));
        	this.textFieldsImpuestos[i].setMaximumSize(this.textFieldsImpuestos[i].getPreferredSize());
        }
        
        // Inicializar Botones
        this.guardar = new JButton("Exportar html");
        this.guardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					if(PanelResultados.this.printString == ""){
						throw new IOException();
					}
					JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));		// Parte del codigo para poner ubicacion tomada de http://stackoverflow.com/questions/21534515/jfilechooser-open-in-current-directory
					fc.setDialogTitle("Seleccione la ubicacion donde desea guardar el archivo");
					fc.setSelectedFile(new File("resultados.html"));
					fc.setFileFilter(new FileNameExtensionFilter("HTML File", "html"));
					int seleccion = fc.showSaveDialog(PanelResultados.this);
					
					if(seleccion == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile();
						if(!file.toString().endsWith(".html")){
							file = new File(file + ".html");				// En caso de que el archivo no tenga la extension html, ponersela
						}
						PrintWriter pw = new PrintWriter(new FileWriter(file));
						pw.println(PanelResultados.this.printString);
						pw.close();
						Desktop.getDesktop().open(file);		// Abrir en el navegador
					}
				}catch(FileNotFoundException e1){
					JOptionPane.showMessageDialog(PanelResultados.this,"No tienes permiso para guardar en esta ubicacion. Prueba guardar en documentos.", "Error",JOptionPane.WARNING_MESSAGE);
				}catch (IOException e2) {
					JOptionPane.showMessageDialog(PanelResultados.this,"No se puede generar el archivo html.", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
        });
        this.copiar = new JButton("Copiar al portapapeles");
        this.copiar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					if(PanelResultados.this.empleado == null){
						throw new NullPointerException("No hay empleado");
					}
					String copiar = "";
					StringTokenizer tk = new StringTokenizer(PanelResultados.this.empleado.toString(),",");
					for(int i = 0; i < PanelResultados.this.labelsIngresos.length; i++){
						copiar+= PanelResultados.this.labelsIngresos[i].getText().replaceAll("\\s+","") + " " + tk.nextToken() + "\n";
					}
					for(int i = 0; i < PanelResultados.this.labelsDeducciones.length; i++){
						copiar+= PanelResultados.this.labelsDeducciones[i].getText().replaceAll("\\s+","") + " " + tk.nextToken() + "\n";
					}
					for(int i = 0; i < PanelResultados.this.labelsImpuestos.length; i++){
						copiar+= PanelResultados.this.labelsImpuestos[i].getText().replaceAll("\\s+","") + " " + tk.nextToken() + "\n";
					}
					// El siguiente codigo fue tomado de http://stackoverflow.com/questions/6710350/copying-text-to-the-clipboard-using-java
					StringSelection stringSelection = new StringSelection(copiar);
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(PanelResultados.this,"No se pudo copiar al portapapeles debido a que no se ha generado el impuesto.", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
        
        //Aniadir al panel ingresos
        JLabel tituloIngresos = new JLabel("*******************   INGRESOS   ********************");
        tituloIngresos.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        this.panelIngresos.add(tituloIngresos, BorderLayout.NORTH);					// Aniadimos el encabezado para los ingresos
        //Crear paneles para labels y textfields
        JPanel ingresosIzquierda = new JPanel();
        ingresosIzquierda.setLayout(new BoxLayout(ingresosIzquierda, BoxLayout.PAGE_AXIS));
        ingresosIzquierda.setBackground(new Color(229, 220, 220));
        JPanel ingresosDerecha = new JPanel();
        ingresosDerecha.setLayout(new BoxLayout(ingresosDerecha, BoxLayout.PAGE_AXIS));
        ingresosDerecha.setBackground(new Color(229, 220, 220));
        
        ingresosIzquierda.add(Box.createRigidArea(new Dimension(0, 3)));
        for(int i = 0; i < this.labelsIngresos.length; i++){
        	ingresosIzquierda.add(this.labelsIngresos[i]);
        	ingresosIzquierda.add(Box.createRigidArea(new Dimension(0, 9)));
        	ingresosDerecha.add(this.textFieldsIngresos[i]);
        	ingresosDerecha.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        this.panelIngresos.add(ingresosIzquierda, BorderLayout.WEST);
        this.panelIngresos.add(ingresosDerecha, BorderLayout.CENTER);
        
        
        //Aniadir al panel deducciones
        JLabel tituloDeducciones = new JLabel("*******************   DEDUCCIONES   ********************");
        tituloDeducciones.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        this.panelDeducciones.add(tituloDeducciones, BorderLayout.NORTH);
        //Crear paneles para labels y textfields
        JPanel deduccionesIzquierda = new JPanel();
        deduccionesIzquierda.setLayout(new BoxLayout(deduccionesIzquierda, BoxLayout.PAGE_AXIS));
        deduccionesIzquierda.setBackground(new Color(229, 220, 220));
        JPanel deduccionesDerecha = new JPanel();
        deduccionesDerecha.setLayout(new BoxLayout(deduccionesDerecha, BoxLayout.PAGE_AXIS));
        deduccionesDerecha.setBackground(new Color(229, 220, 220));
        
        deduccionesIzquierda.add(Box.createRigidArea(new Dimension(0, 3)));
        for(int i = 0; i < this.labelsDeducciones.length; i++){
        	deduccionesIzquierda.add(this.labelsDeducciones[i]);
        	deduccionesIzquierda.add(Box.createRigidArea(new Dimension(0, 9)));
        	deduccionesDerecha.add(this.textFieldsDeducciones[i]);
        	deduccionesDerecha.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        this.panelDeducciones.add(deduccionesIzquierda, BorderLayout.WEST);
        this.panelDeducciones.add(deduccionesDerecha, BorderLayout.CENTER);
        
        //Aniadir al panel impuestos
        JLabel tituloBalance = new JLabel("*******************   BALANCE   *******************");
        tituloBalance.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        this.panelImpuesto.add(tituloBalance, BorderLayout.NORTH);									// Aniadimos el encabezado para los impuestos
        //Crear paneles para labels y textfields
        JPanel impuestosIzquierda = new JPanel();
        impuestosIzquierda.setLayout(new BoxLayout(impuestosIzquierda, BoxLayout.PAGE_AXIS));
        impuestosIzquierda.setBackground(new Color(229, 220, 220));
        JPanel impuestosDerecha = new JPanel();
        impuestosDerecha.setLayout(new BoxLayout(impuestosDerecha, BoxLayout.PAGE_AXIS));
        impuestosDerecha.setBackground(new Color(229, 220, 220));
        
        impuestosIzquierda.add(Box.createRigidArea(new Dimension(0, 3)));
        for(int i = 0; i < this.labelsImpuestos.length; i++){
        	impuestosIzquierda.add(this.labelsImpuestos[i]);
        	impuestosIzquierda.add(Box.createRigidArea(new Dimension(0, 8)));
        	impuestosDerecha.add(this.textFieldsImpuestos[i]);
        	impuestosDerecha.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        this.panelImpuesto.add(impuestosIzquierda, BorderLayout.WEST);
        this.panelImpuesto.add(impuestosDerecha, BorderLayout.CENTER);
        
        //Aniadir al panel botones
        this.panelBotones.add(this.guardar);
        this.panelBotones.add(this.copiar);
	}
	
	public void calcularResultados(Empleado empleado){
		/*
		 * recibe como parametro un empleado del cual se van a extraer los resultados
		 */
		this.empleado = empleado;
		StringTokenizer token = new StringTokenizer(this.empleado.toString(), ",");
		
		for(int i = 0; i < this.textFieldsIngresos.length; i++){
			if(i == 0 || i == 1){		// Cuando es textField nombre o rfc (String)
				this.textFieldsIngresos[i].setText(token.nextToken());
				continue;
			}
			this.textFieldsIngresos[i].setText(String.format("$ %.2f", Double.parseDouble(token.nextToken())));
		}
		for(int i = 0; i < this.textFieldsDeducciones.length; i++){
			if(i == 7){					// Cuando es textField de nivel educativo (String)
				this.textFieldsDeducciones[i].setText(token.nextToken());
				continue;
			}
			this.textFieldsDeducciones[i].setText(String.format("$ %.2f", Double.parseDouble(token.nextToken())));
		}
		for(int i = 0; i < this.textFieldsImpuestos.length; i++){
			if(i==3){					// Cuando es textField de porcentaje
				this.textFieldsImpuestos[i].setText(String.format("%.2f", Double.parseDouble(token.nextToken()))+" %");
				continue;
			}
			this.textFieldsImpuestos[i].setText(String.format("$ %.2f", Double.parseDouble(token.nextToken())));
		}
		
		
		this.printString = "<html><b>Nombre: </b>" + this.empleado.getNombre() + "<br><b>RFC: </b>" + this.empleado.getRFC() + "<br></html>";
		this.printString +=  this.empleado.getIngresos().desglosar();
		this.printString += this.empleado.getDeduccion().desglosar();
		this.printString += this.empleado.getISR().desglosar();;
	}
	
	public void limpiarCeldas(){
		for(JTextField tf:this.textFieldsIngresos){
			tf.setText("");
		}
		for(JTextField tf:this.textFieldsDeducciones){
			tf.setText("");
		}
		for(JTextField tf:this.textFieldsImpuestos){
			tf.setText("");
		}
		this.printString = "";
		this.empleado = null;
	}
}
