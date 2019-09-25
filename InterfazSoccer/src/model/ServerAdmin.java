package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.ControllerServer;

public class ServerAdmin {
	
	ArrayList<Socket> playerSockets;
    public static final int MAX_NUMBER_PLAYERS = 2;
    private int numberPlayers;
    private int savePlayers;
    Socket socket;
    ServerSocket sk;
	public static final int PUERTO = 5650;
	public static final String IP = "localhost";
	ControllerServer controlador;
	private HashMap<String,Socket> map;
	
    
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
            sk = new ServerSocket(PUERTO);

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptClient() {
    	try {
			
	    	while(numberPlayers<MAX_NUMBER_PLAYERS) {
	    		Socket sock = sk.accept();
	    		new Thread(new Server(sock,controlador)).start();
	    		
	            playerSockets.add(sock);
	            numberPlayers ++;
	            controlador.addMessage("Clientes conectados " + numberPlayers);
	          
	    	}
	    	boolean ct = true;
	    	
	    	while(ct) {
	    		System.out.println(savePlayers);
	    	if(savePlayers== MAX_NUMBER_PLAYERS) {
	    	
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
    	
    	map.put(msg, s);
    	savePlayers ++;
    	

    }
    
    public void sendMessage(String msj) {
    	Socket[] players = new Socket[savePlayers];
    	map.values().toArray(players);
    	for(int i = 0;i<players.length;i++) {
    	       try {
    	            
    	            
    	            OutputStream os = players[i].getOutputStream();
    	            OutputStreamWriter osw = new OutputStreamWriter(os);
    	            BufferedWriter bw = new BufferedWriter(osw);
    	            bw.write(msj);
    	            bw.newLine();
    	            bw.flush();
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
