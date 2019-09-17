package GUI;

import javax.swing.JPanel;

import controller.ControllerOptions;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class Options_Panel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControllerOptions controller;

	public Options_Panel(){
		setLayout(new BorderLayout(0, 0));
		
		setController(new ControllerOptions());
		
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

	public ControllerOptions getController() {
		return controller;
	}

	public void setController(ControllerOptions controller) {
		this.controller = controller;
	}

}
