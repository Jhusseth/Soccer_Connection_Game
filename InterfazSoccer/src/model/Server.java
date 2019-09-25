package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ControllerServer;

public class Server extends Thread{
	
	ControllerServer controlador;
	public static final int PUERTO = 5650;
	public static final String IP = "localhost";
    ServerSocket sk;
    Socket socket;
    BufferedReader br;
    BufferedWriter bw;
    public static final int MAX_NUMBER_PLAYERS = 4;
    private int numberPlayers;
    ArrayList<Socket> playerSockets;
   
    public Server(Socket sc,ControllerServer cs) {
    	socket = sc;
    	controlador = cs;
    	createStream();
    }
    

    
    public void createStream(){
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(String mensaje){
        try {
            bw.write(mensaje);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String receiveMessage(){
        try {
            String mensaje = br.readLine();
            
            return mensaje;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public void run(){
    	String msg = receiveMessage();
    	int cont = 0;
        
        	
        	
        	controlador.saveNick(msg, socket);
        	controlador.addMessage("Jugador: "+ msg + " esta listo...");
        	cont++;
        	
        
        
        controlador.setInGame(true);
      
//        	sendMessage("INIT");
        
      
        
    }
    
    public void initGame() {
    	
    }


}
