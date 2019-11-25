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
	private String report1;
	private String report2;
	private boolean end;

	public Match() {
		balon = new Item(0, new Point(427, 240), new ImageIcon("data/ball.png"),"");
		report1 = " ";
		report2 = " ";
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

	public void reporte(int id) {
		int g =0;
		
		if(id==1) {
			if(player1.getGoles().size()==0) {
				report1 = "        " + player1.getName() + "     Gol n°: " + g + " " + "   Minuto  00:00" + "\n";	
			}
			else {
				for(int i =0;i<player1.getGoles().size();i++) {
					g = i+1;
					report1 += "        " + player1.getName() + "     Gol n°: " + g + " " + "   Minuto 00:" +player1.getGoles().get(i).getTime() + "\n";	
				}
			}
		}
		else {	
			if(player2.getGoles().size()==0) {
				report2 = "        " + player2.getName() + "     Gol n°: " + g + " " + "   Minuto  00:00" + "\n";	
			}
			else {
				for(int i =0;i<player2.getGoles().size();i++) {
					g=i+1;
					report2 += "        " + player2.getName() + "     Gol n°: " + g + " " + "   Minuto 00:" +player2.getGoles().get(i).getTime() + "\n";	
				}
			}
		}
	}
	
	
	public String getReport1() {
		return report1;
	}

	public void setReport(String report) {
		this.report1 = report;
		this.report2 = report;
	}

	public String getReport2() {
		return report2;
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
