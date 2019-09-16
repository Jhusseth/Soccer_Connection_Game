package GUI;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class Options_Panel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GUI_MainGame_Cliente main;

	public Options_Panel(GUI_MainGame_Cliente main){
		setLayout(new BorderLayout(0, 0));
		this.main = main;
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		add(panel, BorderLayout.CENTER);
		
		JButton btnClose = new JButton("New button");
		panel.add(btnClose);
		
		JButton btnReboot = new JButton("New button");
		panel.add(btnReboot);
		
		JButton btnChangePlayer = new JButton("New button");
		panel.add(btnChangePlayer);
		
		JButton btnScore = new JButton("New button");
		panel.add(btnScore);
		
	}

}
