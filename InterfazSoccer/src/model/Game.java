package model;

public class Game implements Runnable{
	
	private Player player;
	private Ball ball;
	private Player player2;
	
	
	
	public Game() {
		this.player = new Player("  1  ",333,253);
		this.ball = new Ball(4,430,260);
		this.player2 = new Player("  2  ",630,253);
	}
	
	public void setPlayerName(String n) {
		player.setName(n);
	}
	
	public void setPlayer2Name(String n) {
		player2.setName(n);
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
	

	


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
