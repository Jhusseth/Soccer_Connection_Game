package model;

import javax.swing.ImageIcon;

public class Player {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 0;
	public static final int LEFT = 3;
	
	private int idPlayer;
	private String name;
	private ImageIcon avatar;
	private int posX ;
	private int posY ;
	private boolean isWinner ;
	private boolean hBall;
	
	private boolean isPlaying;
	
	public boolean isPlaying() {
		return isPlaying;
	}


	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}


	private int goal;
	
	public int addres;
	
	private Ball ball;
	

	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}


	public Player(String name,int x, int y){
		
		this.posX = x ;
		this.posY = y;
		this.avatar = new ImageIcon("data/player1.png");
		this.name =  name;
		this.isWinner = false;
		this.addres = 0;
		this.hBall = false;
		this.goal =0;
		this.isPlaying=false;
		
	}

	
	public int getGoal() {
		return goal;
	}


	public void setGoal(int goal) {
		this.goal = goal;
	}


	public boolean ishBall() {
		return hBall;
	}


	public void sethBall(boolean hBall) {
		this.hBall = hBall;
	}


	public int getAddres() {
		return addres;
	}


	public void setAddres(int addres) {
		this.addres = addres;
	}


	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageIcon avatar) {
		this.avatar = avatar;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	public void move(int width, int heigh){
		switch (addres) {
		case UP:
			posY--;
			break;
			
		case DOWN:
			posY++;
			break;
			
		case LEFT:
			posX--;
			break;
			
		case RIGHT:
			posX++;
			break;

		default:
			break;
		}
		
		if(posX>width-27){
			addres=LEFT;
		}
		
		if(posX<=0){
			addres=RIGHT;
		}

		if(posY>=heigh-56){
			addres=UP;
		}
		
		if(posY<30){
			addres=DOWN;
		}
	}
	

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", avatar=" + avatar + "]";
	}	
	
}
