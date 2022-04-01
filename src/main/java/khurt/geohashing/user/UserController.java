package khurt.geohashing.user;

import khurt.geohashing.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping(ApiPaths.USERS + "/{id}")
    public User getOneUser(@PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping(ApiPaths.USERS)
    public User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @DeleteMapping(ApiPaths.USERS + "/{id}")
    public void deleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping(ApiPaths.USERS + "/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setPos_x(newUser.getPos_x());
            user.setPos_y(newUser.getPos_y());
            return repository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
