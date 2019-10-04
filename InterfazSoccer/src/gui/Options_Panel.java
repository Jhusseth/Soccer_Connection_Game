package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;

public class Options_Panel extends JPanel implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GUI_MainGame main;
	public static final String CLOSE = "close";
	public static final String CHANGE = "change";
	public static final String REBOOT = "reboot";
	public static final String SCORE = "score";

	public Options_Panel(GUI_MainGame main){
		setLayout(new BorderLayout());
		
		this.main = main;
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(1,6,5,0));
		add(panel, BorderLayout.CENTER);
		
		panel.add(new JLabel(" "));
		
		JButton btnReboot = new JButton("Reboot");
		panel.add(btnReboot);
		
		JButton btnChangePlayer = new JButton("Change Player");
		panel.add(btnChangePlayer);
		
		JButton btnClose = new JButton("Close");
		panel.add(btnClose);
		
		JButton btnScore = new JButton("Score");
		panel.add(btnScore);
		
		panel.add(new JLabel(" "));
		
		btnChangePlayer.addActionListener(this);
		btnClose.addActionListener(this);
		btnReboot.addActionListener(this);
		btnScore.addActionListener(this);
		
		btnChangePlayer.setActionCommand(CHANGE);
		btnClose.setActionCommand(CLOSE);
		btnReboot.setActionCommand(REBOOT);
		btnScore.setActionCommand(SCORE);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(CHANGE)){
			main.initGame();
		}
		

		else if (command.equals(REBOOT)){
	
		}


		else if (command.equals(CLOSE)){
		}


		else if (command.equals(SCORE)){
	

		}
		
	}

}
