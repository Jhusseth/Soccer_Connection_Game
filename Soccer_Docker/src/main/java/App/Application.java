package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import handlerServer.ThreadServer;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@RestController
public class Application {
	
	private final Counter myCounter;
	private static ThreadServer sv;
	
	@GetMapping("/")
	public String index() {
		return html();
	}	
	
//	@RequestMapping("/upload")
//	public String uploadFile() {
//		String [] data = new String[3];
//		int match = ThreadServer.getMatches().size();
//		String players = "";
//		
//		String[] test = new String[match];
//		for(int i=0;i<match;i++) {
//			String p1 = "";
//			String p2 = "";
//			if(!ThreadServer.getMatches().get(i).getPlayer(2).getConexion()) {
//			    p1 =ThreadServer.getMatches().get(i).getPlayer(2).getName();
//			}
//            if(!ThreadServer.getMatches().get(i).getPlayer(1).getConexion()) {
//            	p2 =ThreadServer.getMatches().get(i).getPlayer(1).getName();
//			}
//            
//             test[i]="<br>" + p1 + "    vs   " +p2 + "</br>";
//		}
//		
//		for(int j=0;j<ThreadServer.getMatches().size();j++) {
//			if(!ThreadServer.getMatches().get(j).getEnd()==true) {
//            	players += test[j];
//            }
//			else {
//				match--;
//			}
//		}
//		
//		String results ="";
//		for(int k=0;k<ThreadServer.getMatches().size();k++) {
//			results += ThreadServer.getMatches().get(k).getRegister() + "\n";
//		}
//		
//		String resultado = "<br> "+ results+ " </br>" + "\n";
//		
//		data[0]="" + match;
//		data[1]=players;
//		data[2]=resultado;
//		String titulo = "<center><h3> Partidos Activos: </h3>  <br>" + data[0]  +"<br>" ;
//		String frame = "<center>" + "<h1> Jugadores en linea: </h1>"+ "<center><table>" + data[1] + "</table></center>";
//		String result = "<br><br><br><center><Table>" + data[2] + "</table></center>";
//		
//		return titulo + frame + result;
//	}
	
	public StringBuilder test() {
		StringBuilder responseBuffer =  new StringBuilder();
		try {
			String str="";
			BufferedReader buf = new BufferedReader(new FileReader("src/main/resources/templates/index.html"));
			while ((str = buf.readLine()) != null) {
				responseBuffer.append(str);
			}
			buf.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseBuffer;
	}
	
	public String html() {
		String html = "";
		try {
		String str="";
		BufferedReader buf = new BufferedReader(new FileReader("src/main/resources/templates/game.html"));
		BufferedWriter buw = new BufferedWriter(new FileWriter("src/main/resources/templates/index.html"));
		String line = (buf.readLine());
		buw.write(line + "\n"+ buf.readLine() +"\n"+csStyle());
		while ((str = buf.readLine()) != null) {
			buw.write(str + "\n");
			if(str.contains("matchSTD")) {
				buf.readLine();
				buw.write("<center><h2> Juegos Activos:"+ "   " + datos()[0] + "</h2> </center>" +
						"<center><p> "+ datos()[1] + "</p> </center>" + "\n");
				buf.readLine();
			}
			
			if(str.contains("dataGame")) {
				buf.readLine();
				buw.write(datos()[2]);
			}
		}
		buf.close();
		buw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		html= test().toString();
		
		return html;
	}
	
	public String csStyle() {
		String data ="";
		try {
			String str="";
			BufferedReader buf = new BufferedReader(new FileReader("src/main/resources/templates/stylesGame.css"));
			while ((str = buf.readLine()) != null) {
				data += str + "\n";
			}
			buf.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "<style>" + "\n" +data + "</style>" + "\n";
	}
	

	public String[] datos() {
		
		String [] data = new String[3];
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
		
		String results ="";
		for(int k=0;k<ThreadServer.getMatches().size();k++) {
			results += ThreadServer.getMatches().get(k).getRegister() + "\n";
		}
		
		String resultado = "<br> "+ results+ " </br>" + "\n";
		
		data[0]="" + match;
		data[1]=players;
		data[2]=resultado;
		return data;
	}
	
	
	public Application( MeterRegistry registry) {
		// TODO Auto-generated constructor stub
		
		myCounter = registry.counter("counter");
	}
	
	@RequestMapping("/example")
	public String example() {
		return "Hello Jhusseth " + myCounter.count();
	}

	public static void main(String[] args) {
		sv= new ThreadServer();
		sv.start();
		SpringApplication.run(Application.class, args);
		
	}

}
