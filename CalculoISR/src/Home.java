import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Home extends JFrame{
	private JPanel panel;
	private JRadioButton opcion1, opcion2;
	private JButton continuar;
	private JFrame ventanaGrupal;

	public Home(){
		super("Bienvenido");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(400, 200));
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		this.add(this.panel);
		this.pack();

        // Las siguientes 2 lineas de cdigo fueron tomadas de http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // Inicializar Componentes
        this.opcion1 = new JRadioButton("Input manual", true);
        this.opcion1.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.opcion2 = new JRadioButton("Subir archivo con varios trabajadores");
        this.opcion2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.opcion2.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        ButtonGroup grupo1 = new ButtonGroup();
        grupo1.add(this.opcion1);
        grupo1.add(this.opcion2);
        this.continuar = new JButton("Continuar");
        this.continuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.continuar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(Home.this.opcion1.isSelected()){
					new VentanaPersona();
				}else{
					if (Home.this.ventanaGrupal != null){
						Home.this.ventanaGrupal.setVisible(true);
					}else{
						Home.this.ventanaGrupal = new VentanaGrupal(Home.this);
					}
				}
				Home.this.setVisible(false);
			}
        });
        
        JLabel title = new JLabel("Seleccione la opcion deseada para el caculo del ISR");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 5, 20, 5));	// Anadir borde al componente de titulo

        // Anadir componentes al panel
        this.panel.add(title);
        this.panel.add(this.opcion1);
        this.panel.add(this.opcion2);
        this.panel.add(this.continuar);

        
        this.setVisible(true);
	}

	public static void main(String[] args){
		new Home();
	}

}
