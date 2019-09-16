package modelClient;

import javax.swing.ImageIcon;

public class Player {
	
	//Atributtes
	private int idPlayer;
	private String name;
	private ImageIcon avatar;
	private int posX ;
	private int posY ;
	private boolean isWinner ;
	

	public Player(int posX, int posY, String name, ImageIcon avatar){
		
		this.posX = posX;
		this.posY = posY;
		this.avatar = avatar;
		this.name =  name;
		this.isWinner = false;
		
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

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", avatar=" + avatar + "]";
	}	
	
}
