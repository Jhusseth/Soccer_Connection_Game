package modelTCP;

import java.awt.Point;
import java.net.Socket;

import javax.swing.ImageIcon;

public class Match {
	public static final int DURATION=40;
	private Item balon;
	private Player player1;
	private Player player2;
	private long time;
	private boolean start;
	private String report;

	public Match() {
		balon = new Item(0, new Point(427, 240), new ImageIcon("data/ball.png"),"");
		report = "";
		start = false;
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

	public String reporte(int id) {
		int g =0;
		if(id==1) {
			if(player1.getGoles().size()==0) {
				report = player1.getName() + "     Gol n°: " + g + " " + "   Minuto  00:00" + "\n";	
			}
			else {
				for(int i =0;i<player1.getGoles().size();i++) {
					report = player1.getName() + "     Gol n°: " + g+1 + " " + "   Minuto 00:" +player1.getGoles().get(i).getTime() + "\n";	
				}
			}
		}
		else {	
			if(player2.getGoles().size()==0) {
				report = player2.getName() + "     Gol n°: " + g + " " + "   Minuto  00:00" + "\n";	
			}
			else {
				for(int i =0;i<player2.getGoles().size();i++) {
					report = player2.getName() + "     Gol n°: " + g+1 + " " + "   Minuto 00:" +player2.getGoles().get(i).getTime() + "\n";	
				}
			}
		}
		return report;
	}
	
	
	public String winner() {
		if(player1.getGoles().size()>player2.getGoles().size()) {
			return player1.getName();
		}
		else if(player1.getGoles().size()<player2.getGoles().size()) {
			
			return  player2.getName();
		}
		else {
			return "Empate";
		}
	}
}
