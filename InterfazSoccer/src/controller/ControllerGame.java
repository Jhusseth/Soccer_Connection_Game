package controller;

import gui.GUI_MainGame;

import model.Game;

import threads.ThreadBall;
import threads.ThreadPlayer;

public class ControllerGame{
	
	private GUI_MainGame main;
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public GUI_MainGame getMain() {
		return main;
	}

	public void setMain(GUI_MainGame main) {
		this.main = main;
	}

	public ControllerGame(GUI_MainGame main) {
		super();
		this.main = main;
		game = new Game();
	}
	
	public void paintComponent(){
//		main.paintBall(main.getGraphics(), game, game.getBall().getPosX(),game.getBall().getPosY());
//		main.paintPlayer(main.getGraphics(), game,game.getPlayer().getPosX(),game.getPlayer().getPosY());
//		main.repaint();
		main.paintComponents(main.getGraphics());
		catchBall();
	}
	
	public ThreadBall starThreadBall(){
		ThreadBall thread = new ThreadBall(this,game.getBall());
		return thread;
	}
	
	public ThreadPlayer starThreadPlayer(){
		ThreadPlayer thread = new ThreadPlayer(game.getPlayer(),this);
		return thread;
	}
	public ThreadPlayer starThreadPlayer2(){
		ThreadPlayer thread = new ThreadPlayer(game.getPlayer2(),this);
		return thread;
	}
	
	public int width(){
		
		int rest = main.getPanel().getWidth()-850;
		
		return 850 + rest;
	}
	

	public int height(){
		
		int rest = main.getPanel().getHeight()-455;
		
		return 455 + rest;
	}

	public void initGame() {
		
		game.getBall().setStopped(false);
		game.getPlayer().setPlaying(false);
		game.getPlayer().setWinner(false);

//		game.getPlayer2().setPlaying(false);
//		game.getPlayer2().setWinner(false);
//		starThreadPlayer2().start();
		starThreadBall().start();
		starThreadPlayer().start();
		
	}
	
	public void prueba(){
		game.getBall().setStopped(true);
		game.getPlayer().setWinner(true);
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
		main.passMessage(msg);
	}
	
	public void setPlayer2Name(String n) {
		game.setPlayer2Name(n);
	}
	public void setPlayerName(String n) {
		game.setPlayerName(n);
	}
}
