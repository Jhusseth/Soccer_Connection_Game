package modelClient;


import javax.swing.ImageIcon;


public class Ball {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 0;
	public static final int LEFT = 3;
	
	public ImageIcon img;
	private boolean stopped;
//	public boolean isWithPlayer;
	private int addres;
	
	private int posX;
	private int posY;
	
	
	public Ball(int direccion){
		img = new ImageIcon("adress");
		this.stopped = true;
//		this.isWithPlayer = false;
		this.addres = direccion;
		this.posX=0;
		this.posY=0;

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
}
