package model;

public class Game {
	
	private Player player;
	private Player player2;
	private Ball ball;
	
	
	public Game() {
		this.player = new Player("Jhusseth",333,253);
		this.ball = new Ball(4,430,260);
		this.player2 = new Player("Juan",630,253);
	}


	public Player getPlayer2() {
		return player2;
	}


	public void setPlayer2(Player player2) {
		this.player2 = player2;
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
		
		if((ball.getPosX()-15<=player2.getPosX() && player2.getPosX()<=ball.getPosX()+15)){
			if((ball.getPosY()-15<=player2.getPosY() && player2.getPosY()<=ball.getPosY()+15)){
				player2.sethBall(true);
			}
		}
	}
	
	public void manageBall(){
		if(player.ishBall()){
//		 ball.setStopped(true);
		 ball.setPosX(player.getPosX()+27);
		 ball.setPosY(player.getPosY()+50);}
		
		if(player2.ishBall()){
//			 ball.setStopped(true);
			 ball.setPosX(player2.getPosX()-27);
			 ball.setPosY(player2.getPosY()+50);}
		
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
			ball.setAddres(4);
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
