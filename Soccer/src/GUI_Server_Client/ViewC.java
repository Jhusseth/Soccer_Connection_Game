package GUI_Server_Client;

import javax.swing.JPanel;

import controller.ControllerClient;

public interface ViewC {
	   final String SEND = "SEND";
	    public void playGame();
	    public void setController(ControllerClient control);
		public void makeVisible();
		public void playerMovement(int t, int p);
		public void initComponents();
		public void passMessage(String msg);
		public JPanel getPanel();
}