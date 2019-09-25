package threads;

import controller.ControllerGame;
import model.Ball;

public class ThreadBall extends Thread {
	
	private Ball ball;
	private ControllerGame game;
	
	public ThreadBall(ControllerGame game ,Ball ball) {
		super();
		this.ball = ball;
		this.game = game;
	}
	
	public void  run() {
		
		while(!ball.isStopped()){
			try{
			game.paintComponent();
			Thread.sleep(4);
			ball.move(game.width(),game.height());
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
//				game.getMain().repaint();
			}
		}
	}
	

}
