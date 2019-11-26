package model;

import java.awt.Point;
import java.net.Socket;

import javax.swing.ImageIcon;

public class Match {
	public static final int DURATION=60;
	private Item balon;
	private Player player1;
	private Player player2;
	private long time;
	private boolean start;
	private boolean end;
	
	private String register;

	public Match() {
		balon = new Item(0, new Point(427, 240), new ImageIcon("data/ball.png"),"");
		register= "";
		start = false;
		end=false;
	}

	public boolean addPlayer(Socket so)throws Exception {
		if (player1 == null) {
			player1 = new Player(1,new Point(300, 185), new ImageIcon("data/player1.gif"), so,this);
			new Thread(player1).start();
			return true;
		} else if (player2 == null) {
			player2 = new Player(2,new Point(525, 185), new ImageIcon("data/player2.gif"), so,this);
			new Thread(player2).start();
			return true;
		}

		return false;

	}

	public int getPlayers() {
		int ret = 0;
		ret += player1 != null ? 1 : 0;
		ret += player2 != null ? 1 : 0;
		return ret;
	}

	public void start() {
		time=System.currentTimeMillis();
		start=true;

	}
	
	public boolean getEnd() {
		return end;
	}
	
	public void setEnd(boolean end) {
		this.end=end;
	}
	
	public synchronized boolean getStart() {
		return start;
	}

	public synchronized Item getBalon() {
		return balon;
	}

	public synchronized void setBalon(Point balon) {
		this.balon.setPos(balon);
	}

	public synchronized Player getPlayer(int id) {
		if(id==1) {
			return player2;

		}else {
			return player1;
		}
	}

	public synchronized long getTime() {
		return time;
	}
	
	public void reporte(String rg) {
		this.register=rg;
	}
	
	public String getRegister() {
		return this.register;
	}
}
