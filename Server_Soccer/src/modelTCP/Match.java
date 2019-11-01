package modelTCP;

import java.awt.Point;
import java.net.Socket;

public class Match {
	public static final long DURATION=1*1000*60;
	private Item balon;
	private Player player1;
	private Player player2;
	private long time;
	private boolean start;
	
	private boolean goal;
	private String report;

	public Match() {
		balon = new Item(0, new Point(427, 240), "Ball","");
		report = "RESULTADOS \n \n";
	}

	public boolean addPlayer(Socket so)throws Exception {

		if (player1 == null) {
			player1 = new Player(1,new Point(300, 185), "", so,this);
			new Thread(player1).start();
			return true;
		} else if (player2 == null) {
			player2 = new Player(2,new Point(525, 185), "", so,this);
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

	public void reporte(String rp) {
		report += "Estadisticas del partido \n";
		report+= rp + "\n \n";
	}


}
