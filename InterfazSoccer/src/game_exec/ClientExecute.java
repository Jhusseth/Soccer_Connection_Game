package game_exec;

import gui.GUI_MainGame;
import gui.ViewC;

import javax.swing.JFrame;

import controller.ControllerClient;
import model.Client;

public class ClientExecute extends JFrame {

	public static void main(String[] args) {
		ViewC view = new GUI_MainGame();
		Client model = new Client();
		ControllerClient control = new ControllerClient(view,model);
		
		view.setController(control);
		model.setController(control);
		control.initializeActivity();
		
	}
}