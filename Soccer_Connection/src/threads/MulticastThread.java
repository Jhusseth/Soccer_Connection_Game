package threads;

import java.io.DataInputStream;

import model.Cliente;

public class MulticastThread extends Thread{
	
	private Cliente cliente ;

	public MulticastThread(Cliente cliente) {
		this.cliente = cliente;
	}
	
public void run() {
		
		while(cliente.isWaitplay() || cliente.isInitPlay()) {
			try {
//				System.out.println("CLIENTE ESPERANDO PARA ENTRAR A PINTAR LA CARRERA :::");
				DataInputStream in = new DataInputStream(cliente.getSocketCliente().getInputStream());
				String mensaje = in.readUTF();
				if (mensaje.compareToIgnoreCase("GAME")==0) {
					cliente.setInitPlay(true);
					Thread.sleep(1);
				}
				else if (mensaje.compareToIgnoreCase("END")==0) {
					cliente.getSocketCliente().close();
				}
				else {
					String[] ndatos = mensaje.split(";");
					for (int i = 0; i < ndatos.length; i++) {
						
//						System.out.println("Caballo : " + ndatos[i].split(",")[0] + " x : " + ndatos[i].split(",")[1] + " MONTO: " + ndatos[i].split(",")[2] + "GANO: " + ndatos[i].split(",")[3]);
//						cliente.modificarCaballo(ndatos[i].split(",")[0],ndatos[i].split(",")[1],ndatos[i].split(",")[2],ndatos[i].split(",")[3]);
//						System.out.println("server esta enviando las coordenadas de los caballos");
						Thread.sleep(1);
					}
					
					
				
				}
			
				
			} catch (Exception e) {

			}	
		}
		
	}
	
	

}
