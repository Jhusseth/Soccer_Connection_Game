package handlerWeb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServerWeb implements Runnable{

	public ThreadServerWeb() {
		System.out.println("Webserver Started");
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			while (true) {
				System.out.println("Waiting for the client request");
				Socket remote = serverSocket.accept();
				System.out.println("Connection made");
				new Thread(new ThreadClientWeb(remote)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
