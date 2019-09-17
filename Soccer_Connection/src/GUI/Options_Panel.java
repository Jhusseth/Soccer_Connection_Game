package GUI;

import javax.swing.JPanel;

import com.sun.javafx.iio.gif.GIFImageLoader2;

import controller.ControllerOptions;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Options_Panel extends JPanel implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControllerOptions controller;
	public static final String CLOSE = "close";
	public static final String CHANGE = "change";
	public static final String REBOOT = "reboot";
	public static final String SCORE = "score";

	public Options_Panel(){
		setLayout(new BorderLayout());
		
		setController(new ControllerOptions());
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
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
		
		btnChangePlayer.addActionListener(this);
		btnClose.addActionListener(this);
		btnReboot.addActionListener(this);
		btnScore.addActionListener(this);
		
		btnChangePlayer.setActionCommand(CHANGE);
		btnClose.setActionCommand(CLOSE);
		btnReboot.setActionCommand(REBOOT);
		btnScore.setActionCommand(SCORE);
		
	}

	public ControllerOptions getController() {
		return controller;
	}

	public void setController(ControllerOptions controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(CHANGE)){
			
		}
		

		else if (command.equals(REBOOT)){
			
		}


		else if (command.equals(CLOSE)){
	

		}


		else if (command.equals(SCORE)){
	

		}
		
	}

}
