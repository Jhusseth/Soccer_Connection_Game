package model;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;



public class Player extends Item implements Runnable{
	
	private Socket playerSocket;
	private DataInputStream reader;
	private DataOutputStream writer;
	private Match match;
	private boolean hball;
	public boolean desconectado;
	
	public boolean isHball() {
		return hball;
	}
	public void setHball(boolean hball) {
		this.hball = hball;
	}
	public Player(int id ,Point pos, ImageIcon image,Socket so,Match match)throws Exception {
		super(id, pos,image,"");
		playerSocket=so;
		createStream();
		String name=reader.readUTF();
		this.setName(name);
		this.match=match;
		hball = false;
		desconectado=false;
		
	}
	public void createStream(){
        try {
        	 writer = new DataOutputStream(playerSocket.getOutputStream());
            
        	 reader = new DataInputStream(playerSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public boolean getConexion() {
		return desconectado;
	}
	
	public void setConexion(boolean conexion) {
		desconectado = conexion;
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
			String[] pars=line.split(" ");
			match.setBalon(new Point(Integer.parseInt(pars[0]),Integer.parseInt(pars[1])));
			
			String direct = reader.readUTF();
			
			if(direct.equals("1")) {
				this.setImage(new ImageIcon("data/player2D.gif") );
			}
			else {
				this.setImage(new ImageIcon("data/player2.gif") );
			}
			
			writer.writeUTF(match.getPlayer(getId()).getImage().toString());
			
			
			Point balon=match.getBalon().getPos();
			String balonP=balon.x+" "+balon.y;
			writer.writeUTF(balonP);
			resta=(int)((System.currentTimeMillis()-match.getTime())/1000);		
		}
		while(resta<30);
		System.out.println("se termino el juego envia end");
		match.setEnd(true);
		match.getPlayer(getId()).setConexion(true);
		writer.writeUTF("end");
		System.out.println("El tiempo cambia a 0");
		writer.writeUTF("0");
		System.out.println("se envia el reporte");
		
		String rg = reader.readUTF();
		match.reporte(rg);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			match.setEnd(true);
			match.getPlayer(getId()).setConexion(true);
		}	
	}
}
