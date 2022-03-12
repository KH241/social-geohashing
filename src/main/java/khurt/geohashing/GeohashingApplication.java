package khurt.geohashing;

import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.ui.Model;
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

	@GetMapping("/hello")
	public String hello(){
		return "Hello";
	}

	@Autowired
	UserRepository UserRepository;
	@GetMapping("/users")
	public String listAll(Model model){
		List<User> listUsers = UserRepository.findAll();
		return gson.toJson(listUsers);
	}
}
