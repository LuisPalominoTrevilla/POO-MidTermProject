import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaGrupal extends JFrame{
	private JButton retroceder;
	private JTextField prueba;
	private JPanel panel;
	private JFrame home;
	public VentanaGrupal(Home home){
		super("Varios Trabajadores");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.home = home;
		
        
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(400, 400));
		this.retroceder = new JButton("Retroceder");
		this.retroceder.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				VentanaGrupal.this.setVisible(false);
				VentanaGrupal.this.home.setVisible(true);
			}
		});
		this.prueba = new JTextField(10);
		
		this.panel.add(this.prueba);
		this.panel.add(this.retroceder);
		this.add(this.panel);
		this.pack();
		
		// Las siguientes 2 lineas de cdigo fueron tomadas de http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setVisible(true);
	}
}