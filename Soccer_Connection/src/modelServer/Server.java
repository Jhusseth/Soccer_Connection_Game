package modelServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private Game game;
	private ServerSocket dSock;
	public static final int PORT_TCP = 5650;
	public static final int PORT_AUDIO = 9786;
	private ArrayList<Socket> socketsActives;
	public static final String DIRECCION_MULTICAST = "229.5.6.7";
	public ArrayList<String> clientsToPersist;
	public static final String LOCAL_HOST = "localhost";
	public static final int PORT_BD = 6500;
	
	
	public Server() throws InterruptedException, IOException {
		game = new Game();
		dSock = new ServerSocket(PORT_TCP);
		socketsActives = new ArrayList<>();
		clientsToPersist = new ArrayList<>();
	}
	
	public void addSocketsActives(Socket dtSocket) {
		if (dtSocket != null) {
			socketsActives.add(dtSocket);	
		}
		
	}
	
	public void sendMulticastInitToClient() throws IOException {
		String mensaje = "MATCH";
		for (int i = 0; i < socketsActives.size(); i++) {
			DataOutputStream out = new DataOutputStream(socketsActives.get(i).getOutputStream());
			out.writeUTF(mensaje);
//			System.out.println("ENVIO MENSAJE DE COMENZAR A LOS CLIENTES ::::");
		}
		
	}
	

	public void cerrarSockets() throws IOException {
	
		String menssage = "CLOSE";
		for (int i = 0; i < socketsActives.size(); i++) {
			DataOutputStream out = new DataOutputStream(socketsActives.get(i).getOutputStream());
			out.writeUTF(menssage);
			socketsActives.get(i).close();
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ServerSocket getdSock() {
		return dSock;
	}

	public void setdSock(ServerSocket dSock) {
		this.dSock = dSock;
	}

	public ArrayList<Socket> getSocketsActives() {
		return socketsActives;
	}

	public void setSocketsActives(ArrayList<Socket> socketsActives) {
		this.socketsActives = socketsActives;
	}

	public ArrayList<String> getClientsToPersist() {
		return clientsToPersist;
	}

	public void setClientsToPersist(ArrayList<String> clientsToPersist) {
		this.clientsToPersist = clientsToPersist;
	}
	
	
	
	
}
