package modelClient;

import javax.swing.ImageIcon;

public class Ball {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 0;
	public static final int LEFT = 3;
	
	public ImageIcon img;
	public boolean stopped;
//	public boolean isWithPlayer;
	public int addres;
	
	public boolean moving;
	
	
	
	public Ball(){
		img = new ImageIcon("adress");
		this.stopped = true;
//		this.isWithPlayer = false;
		this.addres =0;
		this.moving = false;
	}



	public boolean isStopped() {
		return stopped;
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

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}	
	
	public void move(){
		
	}
}
