package handlerWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import handlerServer.ThreadServer;

public class ThreadClientWeb implements Runnable{
	private final Socket socket;

	public ThreadClientWeb(Socket socket)
	{
		this.socket =  socket;
	}
	
	@Override
	public void run() {
		System.out.println("\nClientHandler Started for " + this.socket);
		while(true) 
		{
			handleRequest(this.socket);
		}		
	}
	
	public void handleRequest(Socket socket)
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			if(headerLine!=null)
			{
				System.out.println(headerLine);
				StringTokenizer tokenizer =  new StringTokenizer(headerLine);
				String httpMethod = tokenizer.nextToken();
				if(httpMethod.equals("GET"))
				{
					System.out.println("Get method processed");
					String httpQueryString = tokenizer.nextToken();
					System.out.println(httpQueryString);
					if(httpQueryString.equals("/"))
					{
						//StringBuilder responseBuffer =  new StringBuilder();
						String str="";
						BufferedReader buf = new BufferedReader(new FileReader("html/game.html"));
						BufferedWriter buw = new BufferedWriter(new FileWriter("html/game2.html"));
						while ((str = buf.readLine()) != null) {
							buw.write(str + "\n");
							if(str.contains("matchSTD")) {
								buf.readLine();
								buw.write("<center><h2> Cantidad de Partidos:"+ "   " + datos()[0] + "</h2> </center>" +
										"<center><p> "+ datos()[1] + "</p> </center>" + "\n");
								buf.readLine();
							}
							
							if(str.contains("dataGame")) {
								buf.readLine();
								buw.write(datos()[2]);
								buf.readLine();
								buw.write(datos()[3]);
							}
							//responseBuffer.append(str);
						}
						buf.close();
						buw.close();
						sendResponse(socket, 200, test().toString());		 
					}
					if(httpQueryString.contains("/?txtData="))
					{
						String retornoAppend="";
						retornoAppend= "<html>" + 
								"    <style>" + 
								"        .tabla{" + 
								"            text-align: center;" + 
								"            border: 2px solid black;" + 
								"            margin-left: 30%;" + 
								"            margin-right: 30%;" + 
								"            padding: 5px;" + 
								"            border-spacing: 15px 5px;" + 
								"        }" + 
								"" + 
								"    </style>" + 
								"    <head></head>" + 
								"    <body>" + 
								"        <center><h1>Cantidad de Partidos:"+ "   " + datos()[0] + "</h1> </center>" + 
								"        <center><h3>Jugadores Activos:</h3> </center>" + 
								"          <center><p>"+ datos()[1] +"</p></center>";
						retornoAppend+=
								"        </table>" +
								"    </body>" + 
								"</html>";

						StringBuilder responseBuffer =  new StringBuilder();
						responseBuffer.append(retornoAppend);
						sendResponse(socket, 200, responseBuffer.toString());		
					}
			}	
			else
				{
					System.out.println("The HTTP method is not recognized");
					sendResponse(socket, 405, "Method Not Allowed");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	public void sendResponse(Socket socket, int statusCode, String responseString)
	{
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";
		
		try {
			DataOutputStream out =  new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) 
			{
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: "
				+ responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
				} 
			else if (statusCode == 405) 
			{
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} 
			else 
			{
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public StringBuilder test() {
		StringBuilder responseBuffer =  new StringBuilder();
		try {
			String str="";
			BufferedReader buf = new BufferedReader(new FileReader("html/game2.html"));
			while ((str = buf.readLine()) != null) {
				responseBuffer.append(str);
			}
			buf.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseBuffer;
	}
	
	public String csStyle() {
		String data ="";
		try {
			String str="";
			BufferedReader buf = new BufferedReader(new FileReader("css/stylesGame.css"));
			while ((str = buf.readLine()) != null) {
				data += str + "\n";
			}
			buf.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public String[] datos() {
		
		String [] data = new String[4];
		int match = ThreadServer.getMatches().size();
		String players = "";
		
		String[] test = new String[match];
		for(int i=0;i<match;i++) {
			String p1 = "";
			String p2 = "";
			if(!ThreadServer.getMatches().get(i).getPlayer(2).getConexion()) {
			    p1 =ThreadServer.getMatches().get(i).getPlayer(2).getName();
			}
            if(!ThreadServer.getMatches().get(i).getPlayer(1).getConexion()) {
            	p2 =ThreadServer.getMatches().get(i).getPlayer(1).getName();
			}
            
             test[i]="<br>" + p1 + "    vs   " +p2 + "</br>";
		}
		
		for(int j=0;j<ThreadServer.getMatches().size();j++) {
			if(!ThreadServer.getMatches().get(j).getEnd()==true) {
            	players += test[j];
            }
			else {
				match--;
			}
		}
		
		String results1 ="";
		String results2 ="";
		for(int k=0;k<ThreadServer.getMatches().size();k++) {
			ThreadServer.getMatches().get(k).reporte(1);
			results1+=ThreadServer.getMatches().get(k).getReport1();
			ThreadServer.getMatches().get(k).setReport("");
			ThreadServer.getMatches().get(k).reporte(2);
			results2+=ThreadServer.getMatches().get(k).getReport2();
		}
		
		String resultado1 = "<br> "+ results1+ " </br>" + "\n";
		String resultado2 = "<br> "+ results2 +" </br>" + "\n";
		
		data[0]="" + match;
		data[1]=players;
		data[2]=resultado1;
		data[3]=resultado2;
		return data;
	}
	
}
