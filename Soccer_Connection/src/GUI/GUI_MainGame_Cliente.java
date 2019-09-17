package GUI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class GUI_MainGame_Cliente extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Options_Panel op;

	public GUI_MainGame_Cliente() {
		
	this.setSize(717,550);
	this.setTitle("                                                               <<<<<< SOCCER FULL HD 4k GAME >>>>>>>");
	this.op = new Options_Panel();
	this.setLayout(new BorderLayout());
	this.init();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	}
	
	
	public void init(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel lblNewLabel = new JLabel(new ImageIcon("data/Field.png"));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(op, BorderLayout.SOUTH);
		
	}
	
	
	public static void main(String[] args){
		
		GUI_MainGame_Cliente window = new GUI_MainGame_Cliente();
		window.setVisible(true);
		
	}

}
