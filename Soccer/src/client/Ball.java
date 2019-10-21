package client;


import javax.swing.ImageIcon;


public class Ball {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	
	public ImageIcon img;
	private boolean stopped;
//	public boolean isWithPlayer;
	private int addres;
	
	private int posX;
	private int posY;
	
	
	public Ball(int direccion,int x, int y){
		img = new ImageIcon("data/ball.png");
		this.stopped = false;
//		this.isWithPlayer = false;
		this.addres = direccion;
		this.posX=x;
		this.posY=y;

	}


	public ImageIcon getImg() {
		return img;
	}


	public void setImg(ImageIcon img) {
		this.img = img;
	}


	public boolean isStopped() {
		return stopped;
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


	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}



//	public boolean isWithPlayer() {
//		return isWithPlayer;
//	}



//	public void setWithPlayer(boolean isWithPlayer) {
//		this.isWithPlayer = isWithPlayer;
//	}



	public int getAddres() {
		return addres;
	}


	public void setAddres(int addres) {
		this.addres = addres;
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
		
		
		if(posX>width-13){
			addres=LEFT;
		}
		
		else if(posX<=0){
			addres=RIGHT;
			
		}

		if(posY>=heigh){
			addres=UP;
			
		}
		
		else if(posY<30){
			addres=DOWN;
		}
	}
}
