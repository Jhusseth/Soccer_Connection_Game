package model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ControllerServer;

public class Server extends Thread {
	
	ControllerServer controlador;
	public static final int PUERTO = 5650;
	public static final String IP = "localhost";
    ServerSocket sk;
    private String clientNick;
    Socket socket;
    DataInputStream read;
    DataOutputStream write;
    public static final int MAX_NUMBER_PLAYERS = 4;
    private int numberPlayers;
    ArrayList<Socket> playerSockets;
    private boolean execute;
 
   
    public Server(Socket sc,ControllerServer cs) {
    	socket = sc;
    	controlador = cs;
    	createStream();
    
    }
    

    
    public ControllerServer getControlador() {
		return controlador;
	}



	public void setControlador(ControllerServer controlador) {
		this.controlador = controlador;
	}



	public ServerSocket getSk() {
		return sk;
	}



	public void setSk(ServerSocket sk) {
		this.sk = sk;
	}



	public String getClientNick() {
		return clientNick;
	}



	public void setClientNick(String clientNick) {
		this.clientNick = clientNick;
	}



	public Socket getSocket() {
		return socket;
	}



	public void setSocket(Socket socket) {
		this.socket = socket;
	}






	public int getNumberPlayers() {
		return numberPlayers;
	}



	public void setNumberPlayers(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}



	public ArrayList<Socket> getPlayerSockets() {
		return playerSockets;
	}



	public void setPlayerSockets(ArrayList<Socket> playerSockets) {
		this.playerSockets = playerSockets;
	}



	public boolean isExecute() {
		return execute;
	}



	public void setExecute(boolean execute) {
		this.execute = execute;
	}



	public static int getPuerto() {
		return PUERTO;
	}



	public static String getIp() {
		return IP;
	}



	public static int getMaxNumberPlayers() {
		return MAX_NUMBER_PLAYERS;
	}



	public void createStream(){
        try {
        	 write = new DataOutputStream(socket.getOutputStream());
            
        	 read = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(String mensaje){
        try {
            write.writeUTF(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String receiveMessage() {
        try {
        	if(!socket.isClosed()) {
            String mensaje = read.readUTF();
            switch(mensaje) {
            
          
            
            case "CLOSED":
            	clientDisconected(2);
            	
            	break;
            	
            case "GAME":
            	controlador.initializeGame();
            	default:
            	
            		
            		return mensaje;
            }
        	}
        	
           
        } catch (IOException ex) {
        	
        	
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        	
        }
        return "";
    }
    
    public void run(){
    	
    	clientNick = receiveMessage();
    	execute = true;
    	if(clientNick == null) {
    		clientDisconected(1);
    	}
    	else {    	
        	controlador.saveNick(clientNick, socket);
        	controlador.addMessage("Jugador: "+ clientNick + " esta listo...");
        	
    	}    	
   

   	while(true) {
   		if(controlador.isInitGame()) {
   			String msg;
			try {
				msg = read.readUTF();
				
				controlador.gameInformation(msg, socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			
   		}
	
    	}    
    }
    
	public void clientDisconected(int reason) {
    	try {
    		
    		switch(reason) {
    		case 1:
    			controlador.addMessage("Cliente "+ clientNick + " se ha desconectado.");
    			socket.close();
    			stop();
    			setExecute(false);
    			controlador.endConnection(1);
    		

    			break;
    		case 2:
    			controlador.addMessage("Cliente: "+ clientNick + " se ha desconectado.");
    			controlador.endConnection(1);
    			setExecute(false);
    			socket.close();
    			stop();
    			break;
    		case 3:
    			sendMessage("CLOSE");
    			controlador.addMessage("Cliente "+ clientNick + " ha sido desconectado.");
    			setExecute(false);
    			socket.close();
    			stop();
    			break;
    		}
    	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
