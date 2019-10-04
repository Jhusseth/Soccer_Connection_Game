package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import gui.ViewG;
import model.Server;
import model.ServerAdmin;

public class ControllerServer implements ActionListener{
	
	  ViewG vista;
	  Server model;
	  ServerAdmin admin;
	  boolean initGame;
	  boolean inGame;

	    public ControllerServer(ViewG vista,ServerAdmin adm) {
	        this.vista = vista;
	        initGame = false;
	        this.admin = adm;
	    }
	    
	    public void initializeActivity(){
	        vista.makeVisible();
	        vista.inicialize();
	        vista.addMessage("abriendo el puerto...");
	        admin.openPort();
	        admin.acceptClient();
	        vista.addMessage("Clientes conectados");
	        initializeGame();
	       
	        
	        
	       
	    }
	    
	   
	    public void saveNick(String nck, Socket s) {
	    	
	    	admin.saveClient(nck, s);
	    	
	    }
	    
	    @SuppressWarnings("static-access")
		public void initializeGame() {
	    	while(!initGame) {
	    		
	    		if(admin.getSavePlayers()== admin.MAX_NUMBER_PLAYERS) {
	    			
	    	initGame = true;
	    	admin.sendMessage("INIT");
	    	
	    		}
	    	
	    	}
	    	}
	    
	    public void gameInformation(String msj, Socket s) {
	    	admin.sendMessage2(msj, s);
	    }
	    	
	    	
	    public void endConnection(int reason) {
	    	
	    	switch(reason) {
	    	case 1:
	    		
	    		admin.setDiscon(true);
	    		admin.adminNotify(1);
	    		break;
	    	}
	    }

	    
	    public void addMessage(String mensaje){
	        vista.addMessage(mensaje);
	    }
	    

		public ViewG getVista() {
			return vista;
		}

		public void setVista(ViewG vista) {
			this.vista = vista;
		}

		public Server getModel() {
			return model;
		}

		public void setModel(Server model) {
			this.model = model;
		}

		public ServerAdmin getAdmin() {
			return admin;
		}

		public void setAdmin(ServerAdmin admin) {
			this.admin = admin;
		}

		
		public boolean isInitGame() {
			return initGame;
		}

		public void setInitGame(boolean initGame) {
			this.initGame = initGame;
		}
		
		

		public boolean isInGame() {
			return inGame;
		}

		public void setInGame(boolean inGame) {
			this.inGame = inGame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			  switch(e.getActionCommand()){
	            case ViewG.SEND:
	                vista.addMessage("Enviando mensaje al cliente...");
	                admin.sendMessage(vista.getFieldText());
	                vista.addMessage("Mensaje enviado.");
	                
	                break;
	        }
		}
		
		public void finishClientConnection(String nm) {
			admin.endClientConnection(nm);
		}

}
