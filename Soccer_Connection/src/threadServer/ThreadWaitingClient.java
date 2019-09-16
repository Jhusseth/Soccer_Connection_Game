package threadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import modelServer.Server;

public class ThreadWaitingClient extends Thread {
	

	private Server server;
	
	public ThreadWaitingClient (Server server) {	
		this.server = server;
	}
	
	public void run() {
		System.out.println("SERVER ESCUCHANDO APUESTAS DE CLIENTES :::");
		
		while(!server.getGame().isPlaying() && !server.getGame().isFinishedMatch() && server.getdSock().isClosed() == false )
		 {
			try {
				//TCP COMMUNICATION WITH CLIENT
				if (!server.getGame().isFinishedMatch() && !server.getGame().isWinner()) {
					if (!server.getdSock().isClosed()) {
						Socket socket = server.getdSock().accept();
						server.addSocketsActives(socket);
						DataInputStream in = new DataInputStream(socket.getInputStream());
						DataOutputStream out = new DataOutputStream(socket.getOutputStream());
						String mensaje = in.readUTF();
						System.out.println("Cedula : " + mensaje.split(",")[0] + " Caballo : " +  mensaje.split(",")[1] + " Monto : $" + mensaje.split(",")[2] ) ;
//						server.agregarClienteParaPersistir(mensaje);
//						server.apostarPorCaballo(mensaje);
						out.writeUTF("CONFIRMADO");
					}
					
				}
				else {
					//System.out.println("hola");
				}
			
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		 }
	}	

}
