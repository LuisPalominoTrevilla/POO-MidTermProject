import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import javax.swing.*;
import java.awt.*;

public class VentanaPersona extends JFrame{
	private JPanel columnaIzquiera, columnaDerecha;
	private JRadioButton preescolar, primaria, secundaria, bachiller, tecnico, ninguno;
	private	JTextField tfNombre, tfRFC, tfSueldoMensual, tfAguinaldo,tfPrimaVacacional, tfHospitales, tfFuneral, tfSGMM, tfHipotecarios, tfDonativos, tfSubRetiro, tfTransporte, tfColegiatura;
	private JLabel lbNombre, lbRFC, lbSueldoMensual, lbAguinaldo, lbPrimaVacacional, lbHospitales, lbFuneral, lbSGMM, lbHipotecarios, lbDonativos, lbSubRetiro, lbTransporte, lbNivelEscolar, lbColegiatura, saltoDeLinea;
	private JButton calcular;

	public VentanaPersona(){
		super("Calcular ISR Individual");
		this.setSize(500, 600);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

        // Las siguientes 2 lineas de codigo fueron tomadas de http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // Inicializar Componentes
        this.columnaIzquiera = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Linea de codigo recuperada de http://stackoverflow.com/questions/2714663/how-can-i-align-all-elements-to-the-left-in-jpanel
        this.columnaIzquiera.setPreferredSize(new Dimension(250,600));
        this.columnaIzquiera.setBorder(BorderFactory.createEmptyBorder(20,30,20,20));
        this.add(this.columnaIzquiera,BorderLayout.WEST);
        
        this.columnaDerecha = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Linea de codigo recuperada de http://stackoverflow.com/questions/2714663/how-can-i-align-all-elements-to-the-left-in-jpanel
        this.columnaDerecha.setPreferredSize(new Dimension(250,600));
        this.columnaDerecha.setBorder(BorderFactory.createEmptyBorder(20,20,20,30));
        this.add(this.columnaDerecha,BorderLayout.EAST);
        
        this.lbNombre = new JLabel("Nombre: ");
        this.tfNombre = new JTextField(15);
        
        this.lbRFC = new JLabel("RFC: ");
        this.tfRFC = new JTextField(15);
        
        this.lbSueldoMensual = new JLabel("Sueldo mensual: ");
        this.tfSueldoMensual = new JTextField(15);
        
        this.lbAguinaldo = new JLabel("Aguinaldo: ");
        this.tfAguinaldo = new JTextField(15);
        
        this.lbPrimaVacacional = new JLabel("Prima Vacacional: ");
        this.tfPrimaVacacional = new JTextField(15);
        
        this.lbHospitales = new JLabel("Medicos y Hopsitales: ");
        this.tfHospitales = new JTextField(15);
        
        this.lbFuneral = new JLabel("Gastos Funerarios: ");
        this.tfFuneral = new JTextField(15);
        
        this.lbSGMM = new JLabel("S. Gastos Medicos Mayores: ");
        this.tfSGMM = new JTextField(15);
        
        this.lbHipotecarios = new JLabel("Hipotecarios: ");
        this.tfHipotecarios = new JTextField(15);
        
        this.lbDonativos = new JLabel("Donativos: ");
        this.tfDonativos = new JTextField(15);
        
        this.lbSubRetiro = new JLabel("Subuenta de Retiro: ");
        this.tfSubRetiro = new JTextField(15);
        
        this.lbTransporte = new JLabel("Transporte Escolar: ");
        this.tfTransporte = new JTextField(15);
        
        this.lbColegiatura = new JLabel("Colegiatura: ");
        this.tfColegiatura = new JTextField(15);
        
        this.lbNivelEscolar = new JLabel("Nivel Escolar: ");
        this.ninguno = new JRadioButton("Ninguno", true);
        this.ninguno.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        this.preescolar = new JRadioButton("Preescolar");
        this.preescolar.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        this.primaria = new JRadioButton("Primaria");
        this.primaria.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        this.secundaria = new JRadioButton("Secundaria");
        this.secundaria.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        this.bachiller = new JRadioButton("Bachillerato");
        this.bachiller.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        this.tecnico = new JRadioButton("Tecnico");
        this.tecnico.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        ButtonGroup nivelEducativo = new ButtonGroup();        
        nivelEducativo.add(this.ninguno);
        nivelEducativo.add(this.preescolar);
        nivelEducativo.add(this.primaria);
        nivelEducativo.add(this.secundaria);
        nivelEducativo.add(this.bachiller);
        nivelEducativo.add(this.tecnico);
        
        
        this.calcular = new JButton("Calcular");
        // this.continuar.addActionListener(new Action(this));
        
        // Anadir componentes al panel
        this.columnaIzquiera.add(lbNombre);
        this.columnaIzquiera.add(tfNombre);
        this.columnaIzquiera.add(lbRFC);
        this.columnaIzquiera.add(tfRFC);
        this.columnaIzquiera.add(lbSueldoMensual);
        this.columnaIzquiera.add(tfSueldoMensual);
        this.columnaIzquiera.add(lbAguinaldo);
        this.columnaIzquiera.add(tfAguinaldo);
        this.columnaIzquiera.add(lbPrimaVacacional);
        this.columnaIzquiera.add(tfPrimaVacacional);
        this.columnaIzquiera.add(lbHospitales);
        this.columnaIzquiera.add(tfHospitales);
        this.columnaIzquiera.add(lbFuneral);
        this.columnaIzquiera.add(tfFuneral);
        this.columnaIzquiera.add(lbSGMM);
        this.columnaIzquiera.add(tfSGMM);
        this.columnaDerecha.add(lbHipotecarios);
        this.columnaDerecha.add(tfHipotecarios);
        this.columnaDerecha.add(lbDonativos);
        this.columnaDerecha.add(tfDonativos);
        this.columnaDerecha.add(lbSubRetiro);
        this.columnaDerecha.add(tfSubRetiro);
        this.columnaDerecha.add(lbTransporte);
        this.columnaDerecha.add(tfTransporte);
        this.columnaDerecha.add(lbColegiatura);
        this.columnaDerecha.add(tfColegiatura);
        this.columnaDerecha.add(lbNivelEscolar);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(this.ninguno);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(this.preescolar);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(this.primaria);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(this.secundaria);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(this.bachiller);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(this.tecnico);
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(saltoDeLinea = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀"));
        this.columnaDerecha.add(calcular);
    
        this.setVisible(true);
	}

	 static class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	 	// Home home;
	 	// public Action(Home h){
	 	// 	this.home = h;
	 	}
	
	 	public void actionPerformed(ActionEvent event) {
	 		// if(this.home.opcion1.isSelected()){
	 		// 	// Codigo opcion 1 (crear ventana)
	 		// }else{
	 		// 	// Codigo opcion 2 (crear ventana)
	 		// }
	 		// this.home.dispose();
	 	//}
	 }

	public static void main(String[] args){
		VentanaPersona newWin = new VentanaPersona();
	}

}
