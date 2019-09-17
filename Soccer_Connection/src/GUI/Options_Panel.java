package GUI;

import javax.swing.JPanel;

import controller.ControllerOptions;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options_Panel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControllerOptions controller;
	public static final String CLOSE = "close";

	public Options_Panel(){
		setLayout(new BorderLayout(0, 0));
		
		setController(new ControllerOptions());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		add(panel, BorderLayout.CENTER);
		
		JButton btnClose = new JButton("Reboot");
		panel.add(btnClose);
		
		JButton btnReboot = new JButton("Change Player");
		panel.add(btnReboot);
		
		JButton btnChangePlayer = new JButton("Score");
		panel.add(btnChangePlayer);
		
		JButton btnScore = new JButton("Exit");
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnScore);
		
	}

	public ControllerOptions getController() {
		return controller;
	}

	public void setController(ControllerOptions controller) {
		this.controller = controller;
	}

}
