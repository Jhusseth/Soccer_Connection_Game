package modelClient;

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
	
	public int addres;
	
	private Ball ball;
	

	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}


	public Player(int posX, int posY, String name){
		
		this.posX = posX;
		this.posY = posY;
		this.avatar = new ImageIcon("adress");
		this.name =  name;
		this.isWinner = false;
		this.addres = 0;
		this.hBall = false;
		
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
			posY++;
			break;
			
		case DOWN:
			posY--;
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
		
		if(posX>=width){
			addres=LEFT;
		}
		
		if(posX<=width){
			addres=RIGHT;
		}

		if(posY>=heigh){
			addres=UP;
		}
		
		if(posY<=heigh){
			addres=DOWN;
		}
	}
	
	private void manageBall(){
		
		if(this.hBall){
			
			switch (ball.getAddres()) {
			case UP:
				ball.setPosY(posY+5);
				
				break;				
			case DOWN:
				ball.setPosY(posY-5);
				break;
				
			case LEFT:
				ball.setPosX(posX-5);
				break;
				
			case RIGHT:
				ball.setPosX(posX+5);
				break; 
				
			default:
				break;
			}
		}
		
		
		
	}

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", avatar=" + avatar + "]";
	}	
	
}
