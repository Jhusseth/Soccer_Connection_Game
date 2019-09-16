package threadServer;

import modelServer.Game;
import modelServer.Player;

public class ThreadPlayer extends Thread {
	
private Player player ;
	
	private Game match;
	
	private boolean goalsComplete;

	public ThreadPlayer(Player player, Game match) {
		super();
		this.player = player;
		this.match = match;
		this.goalsComplete = false;
	}
	
	

	public void  run() {
		
		while(!goalsComplete && player.isPlaying() && !player.isWinner()) {
			int posX = player.getPosX() + 5;
			int posY = player.getPosX() + 5;
			
//			if (posX >= player. && !carrera.isHayGanaador()) {
//				caballo.setGanador(true);
//				caballo.setCorriendo(false);
//				carrera.setHayGanaador(true);
//				carrera.setGanador(caballo);
//			}
//			else if (nuevaPosicionCaballo >= Carrera.DISTANCIA_PARA_GANAR) {
//				llegoALaMeta = true;
//				caballo.setCorriendo(false);
//			}
//			else {
//				//System.out.println("caballo : " + caballo.getIdentificador() + " su pos en x " + nuevaPosicionCaballo);
//				caballo.setPosX(nuevaPosicionCaballo);
//				try {
//					Thread.sleep(generarMilisAleatorio());
//				} catch (InterruptedException e) {
//					
//				}
//			}
//			
		}
//
//		System.out.println("TERMINO CABALLO : "+ caballo.getIdentificador());
//		
	}
	

}
