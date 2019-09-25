package game_exec;

import gui.ViewG;
import gui.ViewPanel;

import javax.swing.JFrame;

import controller.ControllerServer;
import model.Server;
import model.ServerAdmin;

public class ServerExecute {


	public static void main(String[] args) {
		ViewG view = new ViewPanel();
		ServerAdmin model = new ServerAdmin();
		ControllerServer control = new ControllerServer(view,model);
		
		view.setController(control);
		model.setController(control);
		control.initializeActivity();
		
		
	}
}