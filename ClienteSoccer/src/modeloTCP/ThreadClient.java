package modeloTCP;

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
//			audio = new ThreadAudioUDPClient(this);
			while(state.equals("waiting")) {
//				audio.start();
				state=reader.readUTF();
			}
//			audio.setStop(true);
			
			
			
			String otherName=state;
			gui.setOtherName(otherName);
			state=reader.readUTF();
			String time=reader.readUTF();
			gui.setTime("00:"+ time);
			
			do {
				
				gui.test();
				
				Point pos=gui.getPlayer().getPos();
				String msm=pos.x+" "+pos.y;
				writer.writeUTF(msm);
				String player1[]=reader.readUTF().split(" ");
				gui.setPlayer1(new Point(Integer.parseInt(player1[0]), Integer.parseInt(player1[1])));
				//posision balon cambia
				if(gui.getHave1()|| gui.getHave2()) {
					 pos=gui.getBalon();
					 msm=pos.x+" "+pos.y;
					writer.writeUTF(msm);
				}else {
					writer.writeUTF("don't have");

				}
				
				writer.writeUTF(""+gui.getDirec());
				
				String img = reader.readUTF();
				
				gui.getPlayer1().setImage(new ImageIcon(img));
				
				String balon[]=reader.readUTF().split(" ");
				gui.setBalon(new Point(Integer.parseInt(balon[0]), Integer.parseInt(balon[1])));
				
				String gol = gui.checkGoal();
				
				if(gui.getGoal()) {
					System.out.println("Envio el gol");
					writer.writeUTF(gol);
					System.out.println(gol);
					gui.setGoal(false);
				}
				else {
					writer.writeUTF("no gol");
				}
				
				String score2 = reader.readUTF();
				gui.getPlayer().setScore(Integer.parseInt(score2));
//				String score1 = reader.readUTF();
//				gui.getPlayer1().setScore(Integer.parseInt(score1));
				
				state=reader.readUTF();
				time=reader.readUTF();
				gui.setTime("00:"+ time);
				gui.update();
				Thread.sleep(100);
				
			}while(state.equals("continue"));
			
			gui.setTime("00:"+ "0"+time);
			gui.setSize(615,460);
			
			String rs1 = reader.readUTF(); 
			gui.resultsMatch("        "+rs1);
			String rs2 = reader.readUTF();
			gui.resultsMatch("        " + rs2);
			
			String winner = reader.readUTF();
			gui.resultsMatch( "                Ganador:   "+ winner);
			
			System.out.println("Empieza audio");
			audio = new ThreadAudioUDPClient(this);			
			audio.start();
			System.out.println("Termina audio");
			//leeer reporte
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}