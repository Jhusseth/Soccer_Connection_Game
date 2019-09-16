package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MainGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MainGame() {
		
	this.setSize(700,500);
	this.setLayout(new BorderLayout());
	this.init();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	
	public void init(){
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("data/Field.png"));
		panel.add(lblNewLabel);
	}
	
	
	public static void main(String[] args){
		
		MainGame window = new MainGame();
		window.setVisible(true);
		
	}

}
