package khurt.geohashing.hashpost;

import khurt.geohashing.ApiPaths;
import khurt.geohashing.images.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class HashPostController {
    private final StorageService storageService = new StorageService();

    @Autowired
    HashPostRepository repository;

    @GetMapping(ApiPaths.HASHPOST)
    public List<HashPost> getAll() {
        return repository.findAll();
    }

    //Move Image Get here / delete Imageuploadcontroller

    @PostMapping(ApiPaths.HASHPOST)
    public ResponseEntity<String> uploadPost(@RequestParam("user") long u_id,
                                             @RequestParam("geohash") long g_id,
                                             @RequestParam("text") String text,
                                             @RequestParam(value = "image", required = false) MultipartFile image){
        //Check if u_id and g_id are valid

        try {
            //Generate imagelink to store the image
            storageService.store(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file");
        }

        //save post in database

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
