package khurt.geohashing.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }
    @GetMapping("/users/{id}")
    public User getOneUser(@PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setPos_x(newUser.getPos_x());
            user.setPos_y(newUser.getPos_y());
            return repository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
