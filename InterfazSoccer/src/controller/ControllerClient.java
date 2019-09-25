package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.ViewC;
import gui.ViewG;
import model.Client;

public class ControllerClient implements ActionListener{
	
	  ViewC vista;
	    Client model;

	    public ControllerClient(ViewC vista, Client model) {
	        this.vista = vista;
	        this.model = model;
	    }
	    
	    public void initializeActivity(){
	        vista.makeVisible();
//	        vista.inicialize();
//	        vista.addMessage("abriendo el puerto...");
	        model.connectWithServer();
//	        vista.addMessage("Esperando al cliente...");
	        model.createStream();
	        model.start();
	
	    }
	    
	    public void initalizeGame() {
	    	vista.inGame();
	    }
	   

	    
	    public void addMessage(String mensaje){
//	        vista.addMessage(mensaje);
	    }
	    
	    public void sendMessage(String msg) {
	    	model.sendMessage(msg);
	    }
	    
	 

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			  switch(e.getActionCommand()){
	            case ViewC.SEND:
	                vista.addMessage("Enviando mensaje al cliente...");
	               
	                vista.addMessage("Mensaje enviado.");
	                
	                break;
	        }
		}
		
		

}
