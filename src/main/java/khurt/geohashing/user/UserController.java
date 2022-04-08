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

    @GetMapping(ApiPaths.USERS + "/name/{name}")
    public User getOneUser(@PathVariable String name) {
        return repository.find(name);
    }

    @PostMapping(ApiPaths.USERS)
    public User newUser(@RequestParam("name") String name,
                        @RequestParam("pos_x") float pos_x,
                        @RequestParam("pos_y") float pos_y){
        return repository.save(new User(name, pos_x, pos_y));
    }

    @DeleteMapping(ApiPaths.USERS + "/{id}")
    public void deleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping(ApiPaths.USERS + "/{id}")
    public User replaceUser(@RequestParam("name") String name,
                            @RequestParam("pos_x") float pos_x,
                            @RequestParam("pos_y") float pos_y,
                            @PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id).map(user -> {
            user.setName(name);
            user.setPos_x(pos_x);
            user.setPos_y(pos_y);
            return repository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
