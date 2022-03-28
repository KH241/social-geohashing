package khurt.geohashing;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RestController
public class GeohashingApplication {

	public Gson gson = new Gson();

	public static void main(String[] args) {
		SpringApplication.run(GeohashingApplication.class, args);
	}

	@GetMapping("/")
	public String hello(){
		return "Hello";
	}
}
