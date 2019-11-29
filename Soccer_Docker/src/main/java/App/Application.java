package App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	@RequestMapping("/")
	public String home() {
	
		return "Hello Docker World";
	}
	
	public Application( MeterRegistry registry) {
		// TODO Auto-generated constructor stub
		sv= new ThreadServer();
		myCounter = registry.counter("counter");
	}
	
	@RequestMapping("/example")
	public String example() {
		return "Hello Jhusseth" + myCounter;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		sv.start();
	}

}
