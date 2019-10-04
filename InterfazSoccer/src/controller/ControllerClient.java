package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.ViewC;
import gui.ViewG;
import model.Client;
import controller.TimeR;

public class ControllerClient implements  ActionListener{
	
	  ViewC vista;
	    Client model;
private int time;
private TimeR tm;
	    public ControllerClient(ViewC vista, Client model) {
	        this.vista = vista;
	        this.model = model;
	    }
	    
	    public void initializeActivity(){
	        vista.makeVisible();
	        model.start();
	
	    }
	    
	    public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public void initalizeGame() {
	    	vista.inGame();
	    	tm = new TimeR(vista);
	    	tm.start();
	    	
	    }
	   

	    
	    public void addMessage(String mensaje){
//	        vista.addMessage(mensaje);
	    }
	    
	    public void sendMessage(String msg) {
	    	model.sendMessage(msg);
	    }
	    
	    public void sendGameInformation(String msj) {
	    	
	    	model.shareGameInformation(msj);
	    }
	    
	    public void stopTime() {
	    	tm.stop();
	    	
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
		
		public void disconnectClient() {
			model.disconnect();
		}
		
		public void closeWindow() {
			vista.close();
		}
		
		public void shareMessage(String msg) {
			String[] msgArray = msg.split(":");
			
			vista.playerMovement(Integer.parseInt(msgArray[0]),Integer.parseInt(msgArray[1]));
		}
		
		public void passMessage(String msg) {
			model.shareInformation(msg);
		}


		
		

}
