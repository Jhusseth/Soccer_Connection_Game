package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.ControllerServer;
//import message.GameMessage;

public class ServerAdmin {
	
	ArrayList<Socket> playerSockets;
    public static final int MAX_NUMBER_PLAYERS = 2;
    private int numberPlayers;
    private int savePlayers;
    Socket socket;
    private boolean discon;
    ServerSocket sk;
    ServerSocket sk2;
    private int numberOfConnectedPlayers;
	public static final int PUERTO = 5650;
	public static final String IP = "localhost";
	ControllerServer controlador;
	private HashMap<String,Socket> map;

    DataInputStream read;
    DataOutputStream write;
	
    
    public ServerAdmin() {
    	playerSockets = new ArrayList<Socket>();
    	map = new HashMap<String, Socket>();
    	
    }

    public void setController(ControllerServer controlador){
        this.controlador = controlador;
    }
    
    public void openPort(){
        try {
        	numberPlayers = 0;
        	savePlayers = 0;
            sk = new ServerSocket(PUERTO,MAX_NUMBER_PLAYERS);
   
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptClient() {
    	try {
			
	    	while(numberPlayers<MAX_NUMBER_PLAYERS) {
	    		
	    		numberPlayers ++;
	    		Socket sock = sk.accept();
	    		new Thread(new Server(sock,controlador)).start();
	    		
	    	}
	    	boolean ct = true;
	    	while(ct) {
	    		System.out.println("");
	    	if(savePlayers== MAX_NUMBER_PLAYERS) {	  
	    	controlador.setInitGame(true);
	    	sendMessage("INIT");
	    	
	    	ct = false;
	    	}
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void saveClient(String msg, Socket s) {
    	savePlayers ++;  
    	map.put(msg, s);
        playerSockets.add(s);       
        numberOfConnectedPlayers ++;
        controlador.addMessage("Clientes conectados " + numberOfConnectedPlayers);
    	  	
    }
    
    public void adminNotify(int reason) {
    	
    	
    	switch(reason) {
    	case 1:
    		
    		numberOfConnectedPlayers --;
    		controlador.addMessage("Clientes conectados " + numberOfConnectedPlayers);
    		
    		break;
    	}
    	
    }
    
    public void sendMessage(String msj) {
    	Socket[] players = new Socket[savePlayers];
    	map.values().toArray(players);
    	for(int i = 0;i<players.length;i++) {
    	       try {
    	            
    	            DataOutputStream dt = new DataOutputStream(players[i].getOutputStream());
    	            dt.writeUTF(msj);
    	        } catch (IOException ex) {
    	            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    	        }
    	}
    }
    
    @SuppressWarnings("unused")
    public void sendMessage2(String msj, Socket s) {
    	Socket[] players = new Socket[savePlayers];
    	map.values().toArray(players);
    	
		String msg = msj;
		
		int cont1 = 0;
		boolean p1 = false;
		boolean p2 = false;
		for(int i = 0; i<players.length;i++) {
			if(s == players[i]) {
				cont1 = i;
			}
		}
		if(cont1<players.length-1) {
			p1 = true;
			msj += ":1";
		}
		else {
			p2 = true;
			msj += ":2";
		}
       
		
    	for(int i = 0;i<players.length;i++) {
    	       try {
    	            DataOutputStream dt = new DataOutputStream(players[i].getOutputStream());
    	            dt.writeUTF(msj);
    	        } catch (IOException ex) {
    	            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    	        }
    	}
    }

    
    
    public void saveSocket(Socket s) {
    	if(s != null) {
    		playerSockets.add(s);
    	}
    	
    }
    
	public int getNumberPlayers() {
		return numberPlayers;
	}

	public void setNumberPlayers(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}

	public int getSavePlayers() {
		return savePlayers;
	}

	public void setSavePlayers(int savePlayers) {
		this.savePlayers = savePlayers;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ServerSocket getSk() {
		return sk;
	}

	public void setSk(ServerSocket sk) {
		this.sk = sk;
	}

	public ControllerServer getControlador() {
		return controlador;
	}

	public void setControlador(ControllerServer controlador) {
		this.controlador = controlador;
	}
	
	

	public boolean isDiscon() {
		return discon;
	}

	public void setDiscon(boolean discon) {
		this.discon = discon;
	}

	public HashMap<String, Socket> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Socket> map) {
		this.map = map;
	}

	public static int getMaxNumberPlayers() {
		return MAX_NUMBER_PLAYERS;
	}

	public static int getPuerto() {
		return PUERTO;
	}

	public static String getIp() {
		return IP;
	}

	public ArrayList<Socket> getPlayerSockets() {
		return playerSockets;
	}

	public void setPlayerSockets(ArrayList<Socket> playerSockets) {
		this.playerSockets = playerSockets;
	}
	
	public void endClientConnection(String nm) {
		
		if(map.containsKey(nm)) {
	        try {
	        	
	            map.get(nm).close();
	        } catch (IOException ex) {
	            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
	        }
		}
	}
}
