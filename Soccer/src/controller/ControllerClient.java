package controller;

import GUI_Server_Client.ViewC;
import client.Ball;
import client.Client;
import server.Player;
import server.Match;

public class ControllerClient {
	
	private Client client;
	private ViewC vista;
	private Match game;
	
	private int cont;
	
	public ControllerClient(ViewC vista,Client client) {
		this.client = client;
		this.vista = vista;
		game = new Match();
		cont =0;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void disconnectClient() {
		client.disconnect();	
	}

	public void initalizeGame() {
		vista.playGame();
	}

	public void initializeActivity() {
		// TODO Auto-generated method stub
		vista.makeVisible();
        client.start();
	}
	
	public void shareMessage(String msg) {
		String[] msgArray = msg.split(":");
		
		vista.playerMovement(Integer.parseInt(msgArray[0]),Integer.parseInt(msgArray[1]));
	}
	
	public void sendMessage(String msg) {
    	client.sendMessage(msg);
    }
	
	public void passMessage(String msg) {
		client.shareInformation(msg);
	}

	public void sendGameInformation(String msg) {
		// TODO Auto-generated method stub
		client.shareGameInformation(msg);
	}
	
	public void catchBall(){
		game.haveABall();
		game.manageBall();
	}
	
	public void shot(int key){
		
		game.shot(key);
		game.getBall().setStopped(false);
		game.getPlayer().sethBall(false);
		
	}
	
	public void move(){
		game.getPlayer().setPlaying(true);
	}
	
	public void messageD(String msg) {
		vista.passMessage(msg);
	}
	

	public int width(){
		
		int rest = vista.getPanel().getWidth()-850;
		
		return 850 + rest;
	}
	

	public int height(){
		
		int rest = vista.getPanel().getHeight()-455;
		
		return 455 + rest;
	}
	
	public boolean isPlaying() {
		return game.isPlaying();
	}

	public void setPlaying(boolean isPlaying) {
		game.setPlaying(isPlaying);
	}

	public Player getWinner() {
		return game.getWinner();
	}

	public void setWinner(Player winner) {
		game.setWinner(winner);
	}

	public boolean isWinner() {
		return game.isWinner();
	}

	public void setWinner(boolean isWinner) {
		game.setWinner(isWinner);
	}

	public boolean isMatchEnded() {
		return game.isMatchEnded();
	}

	public void setMatchEnded(boolean matchEnded) {
		game.setMatchEnded(matchEnded);
	}

	public void haveABall() {
		game.haveABall();
	}

	public int hashCode() {
		return game.hashCode();
	}

	public void manageBall() {
		game.manageBall();
	}

	public Player getPlayer() {
		return game.getPlayer();
	}

	public void setPlayer(Player player) {
		game.setPlayer(player);
	}

	public Player getPlayer2() {
		return game.getPlayer2();
	}

	public void setPlayer2(Player player2) {
		game.setPlayer2(player2);
	}

	public Ball getBall() {
		return game.getBall();
	}

	public void setBall(Ball ball) {
		game.setBall(ball);
	}

	public void goal() {
		game.goal();
	}

	public boolean equals(Object obj) {
		return game.equals(obj);
	}

	public String toString() {
		return game.toString();
	}
}
