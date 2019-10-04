package gui;

import controller.ControllerServer;

public interface ViewG {
	   final String SEND = "SEND";
	    
	    public void enableSend();
	    public void disableSend();
	    public void addMessage(String msg);

	    
	    
	    public void setController(ControllerServer control);
	    public void makeVisible();
	    public void inicialize();
	    public String getFieldText();
	  
}
