package modelTCP;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadServer extends Thread {
	public static final int PORT = 5001;
	private ServerSocket socket;
	private static List<Match> matches;
	
	public static final int PORT_AUDIO = 9999;
	public static final String MUlTICAST = "224.0.0.1";

	public ThreadServer() {
		try {
			socket = new ServerSocket(PORT);		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		System.out.println("Conecting..");
		try {
			matches = new ArrayList<Match>();
			while (true) {
				Socket tmp = socket.accept();
				if (matches.size() == 0) {
					Match match = new Match();
					match.addPlayer(tmp);
					matches.add(match);
				} else {
					Match match = matches.get(matches.size() - 1);
					int players = match.getPlayers();
					if (players == 1) {
						match.addPlayer(tmp);
						match.start();
						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								sendAudio();
								System.out.println("Se empieza el envio del audioz"+true);
							}
						}, 10);
					} else if (players == 2) {
						match = new Match();
						match.addPlayer(tmp);
						matches.add(match);						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void sendAudio() {
		ThreadAudioUDPServer audio = new ThreadAudioUDPServer(MUlTICAST,PORT_AUDIO);
		audio.start();
	}
	
	public static void main(String[] args) {
		new ThreadServer().start();
	}
}
