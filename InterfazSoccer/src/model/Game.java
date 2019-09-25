package model;

public class Game {
	
	private Player player;
	private Ball ball;
	
	
	public Game() {
		this.player = new Player("carlos",333,253);
		this.ball = new Ball(1,437,260);
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}
	
	public void haveABall(){
		if((ball.getPosX()-15<=player.getPosX() && player.getPosX()<=ball.getPosX()+15)){
			if((ball.getPosY()-15<=player.getPosY() && player.getPosY()<=ball.getPosY()+15)){
				player.sethBall(true);
			}
		}
	}
	
	public void manageBall(){
		if(player.ishBall()){
//		 ball.setStopped(true);
		 ball.setPosX(player.getPosX()+27);
		 ball.setPosY(player.getPosY()+50);}
		
	}


	public void shot(int key){
		if(player.ishBall()){
			ball.setPosX(ball.getPosX());
			ball.setPosY(ball.getPosY());
			ball.setAddres(key);
		}
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
