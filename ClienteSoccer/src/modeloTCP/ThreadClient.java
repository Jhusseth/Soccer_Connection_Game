package modeloTCP;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadClient extends Thread {
	private Client_GUI gui;
	private Socket socket;
	private DataInputStream reader;
	private DataOutputStream writer;
	
	public ThreadClient(Client_GUI gui) {
		try {
			this.gui=gui;
			socket=new Socket("localhost",5001);
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
			gui.setTime(time);
			
			do {
			Point pos=gui.getPlayer();
			String msm=pos.x+" "+pos.y;
			writer.writeUTF(msm);
			String player1[]=reader.readUTF().split(" ");
			gui.setPlayer1(new Point(Integer.parseInt(player1[0]), Integer.parseInt(player1[1])));
			
			//posision balon cambia
			if(gui.getHave()) {
				 pos=gui.getBalon();
				 msm=pos.x+" "+pos.y;
				writer.writeUTF(msm);
			}else {
				writer.writeUTF("don't have");

			}

			
			String balon[]=reader.readUTF().split(" ");
			gui.setBalon(new Point(Integer.parseInt(balon[0]), Integer.parseInt(balon[1])));
			
			state=reader.readUTF();
			time=reader.readUTF();
			gui.setTime(time);
			System.out.println(time);
			gui.update();
			Thread.sleep(100);
			}while(state.equals("continue"));
			
			//leeer reporte
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}