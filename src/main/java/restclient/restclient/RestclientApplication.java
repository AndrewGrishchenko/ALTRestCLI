package restclient.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RestclientApplication {
	public static final Logger logger = LoggerFactory.getLogger(RestclientApplication.class);

	public static void main(String[] args) {
		new SpringApplicationBuilder(RestclientApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		// SpringApplication.run(RestclientApplication.class, args);
	}
}
