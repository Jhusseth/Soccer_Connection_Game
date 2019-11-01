package modelTCP;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Player extends Item implements Runnable{
	
	private List<Goal> goles;
	private Socket playerSocket;
	private DataInputStream reader;
	private DataOutputStream writer;
	private Match match;
	
	public Player(int id ,Point pos, String image,Socket so,Match match)throws Exception {
		super(id, pos, image,"");
		playerSocket=so;
		createStream();
		String name=reader.readUTF();
		this.setName(name);
		goles=new ArrayList<>();
		this.match=match;
		
	}
	public void createStream(){
        try {
        	 writer = new DataOutputStream(playerSocket.getOutputStream());
            
        	 reader = new DataInputStream(playerSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public void addGol(int time) {
		goles.add(new Goal(time));
	}
	
	public List<Goal> getGoles(){
		return goles;
	}

	@Override
	public void run() {
		try {
			while(!match.getStart()) {
				writer.writeUTF("waiting");
			}
			writer.writeUTF(match.getPlayer(getId()).getName());
			int resta=0;
			
		do {
			writer.writeUTF("continue");
			writer.writeUTF(""+resta);
			
			String player1[]=reader.readUTF().split(" ");

			this.setPos(new Point(Integer.parseInt(player1[0]),Integer.parseInt(player1[1])));
			Point player2=match.getPlayer(this.getId()).getPos();
			String pos=player2.x+" "+player2.y;
			
			
			
			writer.writeUTF(pos);
			//posision balon cambia
			String line=reader.readUTF();
			if(!line.equals("don't have")) {
				String[] pars=line.split(" ");
				match.setBalon(new Point(Integer.parseInt(pars[0]),Integer.parseInt(pars[1])));
			}
			Point balon=match.getBalon().getPos();
			String balonP=balon.x+" "+balon.y;
			writer.writeUTF(balonP);
			resta=(int)((System.currentTimeMillis()-match.getTime())/1000);
		}while(resta<20);
		System.out.println("end");
		writer.writeUTF("end");
		writer.writeUTF("0");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
