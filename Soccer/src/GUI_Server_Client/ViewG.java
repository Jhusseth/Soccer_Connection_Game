package GUI_Server_Client;

import controller.ControllerServer;

public interface ViewG {
	    final String SEND = "SEND";  
	    public void addMessage(String msg);   
	    public void setController(ControllerServer control);
		public void makeVisible();
	  
}

