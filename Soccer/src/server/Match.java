package server;

import server.Player;

import javax.swing.ImageIcon;

import client.Ball;

public class Match {
	
	private Player[] players;
	
	public static final int MINUTES_MATCH = 45;

	private boolean isPlaying;
	private Player player;
	private Player player2;
	private boolean isWinner;
	private boolean matchEnded ;
	private Ball ball;
	
	public Match() {
		player=new Player("  1  ",333,253);
		this.ball = new Ball(4,430,240);
		player2= new Player("  2  ",630,253);
		player2.setAvatar(new ImageIcon("data/player2.png"));
		isPlaying=false;
		isWinner=false;
		matchEnded = false;
		players= new Player[2];
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public Player getWinner() {
		return player;
	}

	public void setWinner(Player winner) {
		this.player = winner;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean isMatchEnded() {
		return matchEnded;
	}

	public void setMatchEnded(boolean matchEnded) {
		this.matchEnded = matchEnded;
	}

	public void haveABall() {
		
		if((ball.getPosX()-15<=player.getPosX() && player.getPosX()<=ball.getPosX()+15)){
			if((ball.getPosY()-15<=player.getPosY() && player.getPosY()<=ball.getPosY()+15)){
				player.sethBall(true);
			}
		}
		
		if((ball.getPosX()-15<=player2.getPosX() && player2.getPosX()<=ball.getPosX()+15)){
			if((ball.getPosY()-15<=player2.getPosY() && player2.getPosY()<=ball.getPosY()+15)){
				player2.sethBall(true);
			}
		}
	}

	public void manageBall() {
		// TODO Auto-generated method stub
		if(player.ishBall()){
//			ball.setStopped(true);
			ball.setPosX(player.getPosX()+27);
			ball.setPosY(player.getPosY()+50);
		}						
		if(player2.ishBall()){	 		
			ball.setStopped(true);				
			ball.setPosX(player2.getPosX()-27);				
			ball.setPosY(player2.getPosY()+50);
			}
	}
	
	public void shot(int key){
		if(player.ishBall()){
			ball.setPosX(ball.getPosX());
			ball.setPosY(ball.getPosY());
			ball.setAddres(key);
		}
		
		if(player2.ishBall()){
			ball.setPosX(ball.getPosX());
			ball.setPosY(ball.getPosY());
			ball.setAddres(key);
		}
	}	

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public void goal(){		
		ball.setStopped(true);
		player.setPlaying(true);		
		ball.setPosX(437);
		ball.setPosY(260);
		ball.setAddres(3);		
		player.setPosX(333);
		player.setPosY(253);
		player.setAddres(3);
	}
	
}
