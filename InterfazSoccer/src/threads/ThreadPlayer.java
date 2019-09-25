package threads;

import controller.ControllerGame;
import model.Game;
import model.Player;

public class ThreadPlayer extends Thread {
	

	private Player player ;
	private ControllerGame game;

	public ThreadPlayer(Player player, ControllerGame game) {
		super();
		this.player = player;
		this.game= game;
	}
	
	
	public void  run() {
		
		
		while(!player.isPlaying()){
			try{
			game.paintComponent();
			Thread.sleep(3);
			player.move(game.widthL(),game.height());
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
//				game.getMain().repaint();
			}
		}
		
	}	

}
