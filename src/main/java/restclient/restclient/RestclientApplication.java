package restclient.restclient;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RestclientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(RestclientApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		// SpringApplication.run(RestclientApplication.class, args);
	}
}
