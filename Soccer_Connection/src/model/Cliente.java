package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MulticastSocket;
import java.net.Socket;

import threads.MulticastThread;

public class Cliente {
	
	public static final int PORT_TCP = 5650;
	public static final String IP = "localhost";
	private boolean waitplay;
	private String name;
	private boolean initPlay;
	private Player[] players;
	private Socket socketCliente;
	private MulticastSocket dtSocket;
	public static final String DIRECCION_MULTICAST = "229.5.6.7";
	
	public Cliente(){
		
		this.players = new Player[2];
		this.name = "";
		this.waitplay = false;
		this.initPlay = false;
		
	}

	public boolean isWaitplay() {
		return waitplay;
	}

	public void setWaitplay(boolean waitplay) {
		this.waitplay = waitplay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInitPlay() {
		return initPlay;
	}

	public void setInitPlay(boolean initPlay) {
		this.initPlay = initPlay;
	}

	public Player[] getPlayer() {
		return players;
	}

	public void setPlayer(Player[] player) {
		this.players = player;
	}

	public Socket getSocketCliente() {
		return socketCliente;
	}

	public void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}

	public MulticastSocket getDtSocket() {
		return dtSocket;
	}

	public void setDtSocket(MulticastSocket dtSocket) {
		this.dtSocket = dtSocket;
	}
	
	
	public void startCommunication()throws IOException {
		socketCliente = new Socket("localhost", PORT_TCP);
		DataInputStream in = new DataInputStream(socketCliente.getInputStream());
		DataOutputStream out = new DataOutputStream(socketCliente.getOutputStream());
		String playerServer = this.name;
		out.writeUTF(playerServer);
		String mensaje = in.readUTF();
		System.out.println("MESSAGE OBTAINED FROM THE SERVER. " + mensaje);
		if(mensaje.compareToIgnoreCase("CONFIRMED")==0) {
			waitplay = true;
		}
		
		MulticastThread thread = new MulticastThread(this);
		thread.start();
	}
	
	
	public void ModifyPlayer(String name, String win){
		boolean flag = false;
		for (int i = 0; i < players.length && !flag; i++) {
			if (players[i].getName().compareToIgnoreCase(name)==0) {
				if (win.compareToIgnoreCase("true")==0) {
					players[i].setWinner(true);
				}
				flag = true;
			}
		}
	}
	

}
