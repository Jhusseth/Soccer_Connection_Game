package model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.ControllerClient;

public class Client extends Thread{
	
	ControllerClient controlador;
	public static final int PUERTO = 5650;
	public static final String IP = "localhost";
    Socket socket;
    private boolean connected;
    DataInputStream read;
    DataOutputStream write;
    private boolean stopThread;
    private boolean cont;
    
    public Client() {
    	connected = false;
    	stopThread = false;
    }
    public void setController(ControllerClient controlador){
        this.controlador = controlador;
    }
    
    
    
    public boolean isCont() {
		return cont;
	}
	public void setCont(boolean cont) {
		this.cont = cont;
	}
	public void connectWithServer() {
    	try {
			socket = new Socket(IP,PUERTO);
			connected = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		
		}
    	
    	
    	
    }
    
    
    public void createStream(){
        try {
       	 write = new DataOutputStream(socket.getOutputStream());
         
       	 read = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void sendMessage(String mensaje){
        try {
            write.writeUTF(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String receiveMessage(){
        try {
        
        	if(!socket.isClosed()) {
            String mensaje = read.readUTF();
            switch(mensaje) {
            case "INIT":
            		
            	controlador.initalizeGame();
            	
            	cont = false;
            	break;
            	
            case "CLOSE":
            	controlador.disconnectClient();
            	break;
            	default:
            		
            		shareInformation(mensaje);
            		return mensaje;
            }
        	}
        	

        } catch (IOException ex) {
        	controlador.closeWindow();
        	stopThread = true;
        }
       
        
        return "";
    }
    @SuppressWarnings("unused")
 
    public void run() {
	  
	  while(!connected&&!stopThread) {
		 connectWithServer();

	  }
	  if(!stopThread) {
		 createStream();
	  }
	 
	  cont = true;
      while(!stopThread) {
		String msg = receiveMessage();	
      }
 
    }
  
  public void disconnect() {
	  try {
		sendMessage("CLOSED");  
		socket.close();
		stopThread = true;
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		stopThread = true;
	}
	  catch(NullPointerException e) {
		  stopThread = true;
	  }
  }
  
  public void shareInformation(String msg) {
	  controlador.shareMessage(msg);
  }
  
  public void shareGameInformation(String msg) {
	  
	  try {
			
		write.writeUTF(msg);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
    

}

