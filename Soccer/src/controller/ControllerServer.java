package controller;

import java.net.Socket;

import GUI_Server_Client.ViewG;
import server.ServerAdmin;

public class ControllerServer {
	
	private ViewG vista;
	private ServerAdmin server;
	boolean initGame;
	boolean inGame;
	
	
	public ControllerServer(ViewG vista,ServerAdmin ad) {
		server = ad;
		initGame = false;
		this.vista = vista;
	}


	public ServerAdmin getServer() {
		return server;
	}


	public void setServer(ServerAdmin server) {
		this.server = server;
	}


	public void initializeGame() {
		// TODO Auto-generated method stub
		while(!initGame) {
    		if(server.getSavePlayers()== server.MAX_NUMBER_PLAYERS) {
    			initGame = true;
    			server.sendMessage("INIT");
    		}
    	
    	}
	}


	public void saveNick(String clientNick, Socket socket) {
		// TODO Auto-generated method stub
		server.saveClient(clientNick, socket);
	}


	public void addMessage(String msg) {
		// TODO Auto-generated method stub
		vista.addMessage(msg);
	}


	public boolean isInitGame() {
		// TODO Auto-generated method stub
		return initGame;
	}


	public void gameInformation(String msg, Socket socket) {
		// TODO Auto-generated method stub
		server.sendMessage2(msg, socket);
	}


	public void endConnection(int i) {
		// TODO Auto-generated method stub
		switch(i) {
    	case 1:
    		
    		server.setDiscon(true);
    		server.adminNotify(1);
    		break;
    	}
	}


	public void setInitGame(boolean b) {
		// TODO Auto-generated method stub
		this.inGame = b;
	}


	public void initializeActivity() {
		// TODO Auto-generated method stub
		vista.makeVisible();
        vista.addMessage("abriendo el puerto...");
        server.openPort();
        server.acceptClient();
        vista.addMessage("Conexion Establecida");
        initializeGame();
	}
	
	

}
