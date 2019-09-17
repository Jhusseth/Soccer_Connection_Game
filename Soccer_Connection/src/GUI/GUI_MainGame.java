package GUI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;

public class GUI_MainGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Options_Panel op;

	public GUI_MainGame() {
		getContentPane().setBackground(Color.WHITE);
		
	this.setSize(860,525);
	this.setTitle("SFCB                                                                                  <<<<<< SOCCER FULL HD 4k GAME >>>>>>>");
	this.op = new Options_Panel();
	op.setBackground(Color.WHITE);
	getContentPane().setLayout(new BorderLayout());
	this.init();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	
	}
	
	
	public void init(){
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setLayout(new BorderLayout());
		JLabel lblNewLabel = new JLabel(new ImageIcon("data/Field.png"));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(op, BorderLayout.SOUTH);
		
	}
	
	
	public static void main(String[] args){
		
		GUI_MainGame window = new GUI_MainGame();
		window.setVisible(true);
		
	}

}
