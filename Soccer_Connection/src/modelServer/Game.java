package modelServer;

import threadServer.ThreadPlayer;

public class Game {
	
	private  Player[] players;
	
	public static final int GOALS_TO_WIN = 5;
	
	public static final String ON_GOING = "the match has started!";
	
	public static final String  FINISHED_MATCH = "the match finished!";
	
	public boolean isPlaying;
	
	public Player playerWinner;
	
	private ThreadPlayer[] threads ;

//	private String status;
	
	private boolean winner;
	
	private boolean finishedMatch;
	
	public Game(){
		
		players = new Player[2];
		this.isPlaying = false;
		this.playerWinner = null;
		this.finishedMatch = false;
		
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public Player getPlayerWinner() {
		return playerWinner;
	}

	public void setPlayerWinner(Player playerWinner) {
		this.playerWinner = playerWinner;
	}

	public ThreadPlayer[] getThreads() {
		return threads;
	}

	public void setThreads(ThreadPlayer[] threads) {
		this.threads = threads;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public boolean isFinishedMatch() {
		return finishedMatch;
	}

	public void setFinishedMatch(boolean finishedMatch) {
		this.finishedMatch = finishedMatch;
	}
	

	public void starMAtch() throws InterruptedException {
		
		isPlaying = true;
		threads = new ThreadPlayer[6];
//		status = EN_MARCHA;
		for (int i = 0; i < players.length; i++) {
			
			players[i].setPlaying(true);
			ThreadPlayer thread = new ThreadPlayer(players[i], this);
			threads[i] = thread;
			thread.start();
	
		}
		
		
	}

}
