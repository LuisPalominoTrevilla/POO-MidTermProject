import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import java.awt.*;

public class VentanaPersona extends JFrame implements ActionListener{
	
	private JPanel columnaIzquierda, columnaDerecha, panelSalir;
	private PanelResultados columnaResultados;
	private JRadioButton primaria, secundaria, bachiller, ninguno;
	private JLabel[] lbDescripciones;
	private JTextField[] tfInputs;
	private JButton calcular, regresar, limpiar;
	
	private JFrame home;
	private Empleado empleado;

	public VentanaPersona(Home home){
		super("Calcular ISR Individual");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.home = home;
		
		/*    Posiciones de tfInputs:
         * 0 - Nombre
         * 1 - RFC
         * 2 - SueldoMensual
         * 3 - Aguinaldo
         * 4 - Prima vacacional
         * 5 - Medicos y hospitales
         * 6 - Gastos funerarios
         * 7 - SGMM
         * 8 - Hipotecarios
         * 9 - donativos
         * 10 - Subcuenta de retiro
         * 11 - Transporte
         * 12 - Colegiatura pagada
         */
		this.tfInputs = new JTextField[13];
		
		/*    Posiciones de lbDescripciones:
         * 0 - Nombre
         * 1 - RFC
         * 2 - SueldoMensual
         * 3 - Aguinaldo
         * 4 - Prima vacacional
         * 5 - Medicos y hospitales
         * 6 - Gastos funerarios
         * 7 - SGMM
         * 8 - Hipotecarios
         * 9 - donativos
         * 10 - Subcuenta de retiro
         * 11 - Transporte
         * 12 - Colegiatura pagada
         * 13 - Nivel escolar
         */
		this.lbDescripciones = new JLabel[14];
		
		//Inicializar y agregar Paneles
		this.columnaIzquierda = new JPanel();
        this.columnaIzquierda.setLayout(new BoxLayout(this.columnaIzquierda, BoxLayout.PAGE_AXIS));
        this.columnaIzquierda.setPreferredSize(new Dimension(250,500));
        this.columnaIzquierda.setBorder(BorderFactory.createEmptyBorder(20,30,20,20));
        this.add(this.columnaIzquierda,BorderLayout.WEST);
        
        this.columnaDerecha = new JPanel();
        this.columnaDerecha.setLayout(new BoxLayout(this.columnaDerecha, BoxLayout.PAGE_AXIS));
        this.columnaDerecha.setPreferredSize(new Dimension(250,500));
        this.columnaDerecha.setBorder(BorderFactory.createEmptyBorder(20,20,20,30));
        this.add(this.columnaDerecha,BorderLayout.CENTER);
        
        this.columnaResultados = new PanelResultados();
        this.add(this.columnaResultados,BorderLayout.EAST);
        
        this.panelSalir = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.panelSalir.setPreferredSize(new Dimension(500, 50));
        this.add(this.panelSalir, BorderLayout.SOUTH);
        
        this.pack();
        
        // Las siguientes 2 lineas de codigo fueron tomadas de http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // Inicializar Componentes
        this.lbDescripciones[0] = new JLabel("Nombre: ");
        this.lbDescripciones[1] = new JLabel("RFC: ");
        this.lbDescripciones[2] = new JLabel("Sueldo mensual: ");
        this.lbDescripciones[3] = new JLabel("Aguinaldo: ");
        this.lbDescripciones[4] = new JLabel("Prima vacacional: ");
        this.lbDescripciones[5] = new JLabel("Medicos y hospitales: ");
        this.lbDescripciones[6] = new JLabel("Gastos funerarios: ");
        this.lbDescripciones[7] = new JLabel("S. Gastos medicos mayores: ");
        this.lbDescripciones[8] = new JLabel("Hipotecarios: ");
        this.lbDescripciones[9] = new JLabel("Donativos: ");
        this.lbDescripciones[10] = new JLabel("Subcuenta de retiro: ");
        this.lbDescripciones[11] = new JLabel("Transporte escolar: ");
        this.lbDescripciones[12] = new JLabel("Colegiatura: ");
        this.lbDescripciones[13] = new JLabel("Nivel escolar: ");
        
        for(JLabel lb:this.lbDescripciones){
        	lb.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        }
        
        for(int i = 0; i < this.tfInputs.length; i++){
        	this.tfInputs[i] = new JTextField(40);
        	this.tfInputs[i].setMaximumSize(this.tfInputs[i].getPreferredSize());
        }
        
        this.ninguno = new JRadioButton("Ninguno", true);
        this.primaria = new JRadioButton("Primaria");
        this.secundaria = new JRadioButton("Secundaria");
        this.bachiller = new JRadioButton("Preparatoria");
        
        ButtonGroup nivelEducativo = new ButtonGroup();        
        nivelEducativo.add(this.ninguno);
        nivelEducativo.add(this.primaria);
        nivelEducativo.add(this.secundaria);
        nivelEducativo.add(this.bachiller);
        
        this.calcular = new JButton("Calcular");
        this.calcular.addActionListener(this);
        
        this.limpiar = new JButton("Limpiar");
        this.limpiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(JTextField tf:VentanaPersona.this.tfInputs){
					tf.setText("");
				}
				VentanaPersona.this.ninguno.setSelected(true);
				VentanaPersona.this.columnaResultados.limpiarCeldas();
			}
        });
        
        this.regresar = new JButton("Regresar");
        this.regresar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				VentanaPersona.this.setVisible(false);
				VentanaPersona.this.home.setVisible(true);
			}
        });

        // Anadir componentes al panel
        for(int i = 0; i < this.tfInputs.length; i++){
        	if(i >= 8){		// Comenzar a poner en la columna derecha
        		this.columnaDerecha.add(this.lbDescripciones[i]);
        		this.columnaDerecha.add(this.tfInputs[i]);
        		continue;
        	}
        	this.columnaIzquierda.add(this.lbDescripciones[i]);
    		this.columnaIzquierda.add(this.tfInputs[i]);
        }
        this.columnaDerecha.add(this.lbDescripciones[this.lbDescripciones.length-1]);			// Aniadir el componente 14 de label al panel
        this.columnaDerecha.add(this.ninguno);
        this.columnaDerecha.add(this.primaria);
        this.columnaDerecha.add(this.secundaria);
        this.columnaDerecha.add(this.bachiller);
        
        // Panel para que los botones esten al lado
        JPanel panelBotones = new JPanel();
        panelBotones.setPreferredSize(new Dimension(250, 50));
        panelBotones.setAlignmentX(LEFT_ALIGNMENT);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        this.columnaDerecha.add(panelBotones);
        
        panelBotones.add(calcular);
        panelBotones.add(limpiar);
        this.panelSalir.add(new JLabel("          "));		// Usado para agregar espacio entre el boton regresar y el borde izquierdo
        this.panelSalir.add(regresar);
        
        this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			// Checar que ningun numero sea negativo y que sean validos los inputs, empezar despues del nombre y el rfc
			for(int i = 2; i < this.tfInputs.length; i++){
				if(Double.parseDouble(this.tfInputs[i].getText()) < 0){
					throw new IllegalArgumentException("Numero negativo introducido");
				}
			}
			
			String nivelEducativo = "ninguno";
			if (this.primaria.isSelected()){
				nivelEducativo = "primaria";
			}
			else if (this.secundaria.isSelected()){
				nivelEducativo = "secundaria";
			}
			else if (this.bachiller.isSelected()){
				nivelEducativo = "preparatoria";
			}
			// Creamos el empleado con sus atributos respectivos
			this.empleado = new Empleado(this.tfInputs[0].getText(),this.tfInputs[1].getText(),
											Double.parseDouble(this.tfInputs[2].getText()),
											Double.parseDouble(this.tfInputs[3].getText()),
											Double.parseDouble(this.tfInputs[4].getText()),
											Double.parseDouble(this.tfInputs[5].getText()),
											Double.parseDouble(this.tfInputs[6].getText()),
											Double.parseDouble(this.tfInputs[7].getText()),
											Double.parseDouble(this.tfInputs[8].getText()),
											Double.parseDouble(this.tfInputs[9].getText()),
											Double.parseDouble(this.tfInputs[10].getText()),
											Double.parseDouble(this.tfInputs[11].getText()),
											nivelEducativo,
											Double.parseDouble(this.tfInputs[12].getText()));
			
			// El empleado debe hacer su declaracion anual
			this.empleado.hacerDeclaracionAnual();
			this.columnaResultados.calcularResultados(this.empleado);			// Calcular y llenar textFields en panelResultados
			
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(VentanaPersona.this,"Favor de llenar todos los campos con el valor adecuado", "Datos incompletos",JOptionPane.WARNING_MESSAGE);
		}catch(IllegalArgumentException ex){
			JOptionPane.showMessageDialog(VentanaPersona.this,ex.getMessage(), "Datos erroneos",JOptionPane.WARNING_MESSAGE);
		}
	}
}
