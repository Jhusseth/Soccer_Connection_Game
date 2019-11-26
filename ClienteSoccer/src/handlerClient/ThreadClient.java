package handlerClient;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import modelUDP.ThreadAudioUDPClient;
import vista.Client_GUI;


public class ThreadClient extends Thread {
	public static final int PORT = 5001;
	public static final int PORT_AUDIO = 9999;
	public static final String DIRECCION_MULTICAST = "224.0.0.1";
	private Client_GUI gui;
	private Socket socket;
	private DataInputStream reader;
	private DataOutputStream writer;
	
	private MulticastSocket dtSocket;
	private ThreadAudioUDPClient audio;
	
	
	
	public MulticastSocket getDtSocket() {
		return dtSocket;
	}
	public void setDtSocket(MulticastSocket dtSocket) {
		this.dtSocket = dtSocket;
	}
	public ThreadClient(Client_GUI gui) {
		try {
			this.gui=gui;
			socket=new Socket("localhost",PORT);
			createStream();
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
        	 writer = new DataOutputStream(socket.getOutputStream());
            
        	 reader = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	@Override
	public void run() {
		try {
			writer.writeUTF(gui.getPlayerName());
			String state=reader.readUTF();
			while(state.equals("waiting")) {
				state=reader.readUTF();
			}
			
			
			
			String otherName=state;
			gui.setOtherName(otherName);
			state=reader.readUTF();
			String time=reader.readUTF();
			gui.setTime("00:"+ time);
			
			do {
				
				
				Point pos=gui.getPlayer().getPos();
				int posX = (gui.getWidth()-51)-pos.x;
				String msm=posX+" "+pos.y;
				writer.writeUTF(msm);
				String player1[]=reader.readUTF().split(" ");
				gui.setPlayer1(new Point(Integer.parseInt(player1[0]), Integer.parseInt(player1[1])));
				
				//posision balon se envia
				if(gui.getHave1()) {
					pos=gui.getPlayer().getPos();
					int pX=0;

					if(gui.getPlayer().getImage().toString().equals("data/player1D.gif")) {
						pX = pos.x-10;
					}
					else {
						pX = pos.x+40;
					}
					int pY = pos.y+55;
					msm=pX+" "+pY;
					writer.writeUTF(msm);
					
				}
				else if(gui.getHave2()) {
				    pos=gui.getPlayer1().getPos();
				    
				    int pX =0;
				    if(gui.getPlayer1().getImage().toString().equals("data/player2D.gif")) {
				    	pX = pos.x+40;
					}
				    else {	    	  
				    	pX = pos.x-10;
				    }
				    int pY = pos.y+55;
				   
					msm=pX+" "+pY;
					writer.writeUTF(msm);
					
					
				}
				else {
					pos=gui.getBalon();
					msm=pos.x+" "+pos.y;
					writer.writeUTF(msm);
				}
				
				
				writer.writeUTF(""+gui.getDirec());
				
				String img = reader.readUTF();
				
				gui.getPlayer1().setImage(new ImageIcon(img));
				
				String balon[]=reader.readUTF().split(" ");
				gui.setBalon(new Point(Integer.parseInt(balon[0]), Integer.parseInt(balon[1])));
				
			    gui.checkGoal();
				
				state=reader.readUTF();
				time=reader.readUTF();
				gui.setTime("00:"+ time);
				gui.update();
				Thread.sleep(100);
				
			}while(state.equals("continue"));
			
			gui.setTime("00:"+ "0"+time);
			gui.setSize(615,460);
			
			String envReg = gui.registro();
			writer.writeUTF(envReg);
			gui.resultsMatch();
			
			
			System.out.println("Empieza audio");
			audio = new ThreadAudioUDPClient(this);			
			audio.start();
			System.out.println("Termina audio");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}