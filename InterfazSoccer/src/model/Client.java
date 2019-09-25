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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ControllerClient;

public class Client extends Thread{
	
	ControllerClient controlador;
	public static final int PUERTO = 5650;
	public static final String IP = "localhost";
    ServerSocket sk;
    Socket socket;
    BufferedReader br;
    BufferedWriter bw;
    
    public Client() {
    	
    }
    public void setController(ControllerClient controlador){
        this.controlador = controlador;
    }
    
    public void connectWithServer() {
    	try {
			socket = new Socket(IP,PUERTO);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(String mensaje){
        try {
            bw.write(mensaje);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String receiveMessage(){
        try {
        	
            String mensaje = br.readLine();
            switch(mensaje) {
            case "INIT":
            	controlador.initalizeGame();
            	break;
            	default:
            		return mensaje;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
  public void run() {
      while(true) {
      	String msg = receiveMessage();
      	
      }
  }
  
  public void desconnect() {
	  try {
		socket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
    

}

