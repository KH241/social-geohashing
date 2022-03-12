package khurt.geohashing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "World") String name){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(name);
    }
}
