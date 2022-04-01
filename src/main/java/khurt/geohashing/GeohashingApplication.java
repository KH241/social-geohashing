package khurt.geohashing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GeohashingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeohashingApplication.class, args);
	}
}
