package khurt.geohashing.hashpost;

import khurt.geohashing.ApiPaths;
import khurt.geohashing.geohash.GeohashRepository;
import khurt.geohashing.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class HashPostController {
    private final StorageService storageService = new StorageService();

    @Autowired
    HashPostRepository repository;
    @Autowired
    UserRepository u_repository;
    @Autowired
    GeohashRepository g_repository;

    @GetMapping(ApiPaths.HASHPOST)
    public List<HashPost> getAll() {
        return repository.findAll();
    }

    @GetMapping(ApiPaths.IMAGES + "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) throws ImageNotFoundException {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").contentType(MediaType.IMAGE_PNG)
                .body(file);
    }

    @PostMapping(ApiPaths.HASHPOST)
    public ResponseEntity<String> uploadPost(@RequestParam("user") long u_id,
                                             @RequestParam("geohash") long g_id,
                                             @RequestParam("text") String text,
                                             @RequestParam(value = "image", required = false) MultipartFile image){

        if (!g_repository.existsById(g_id)){ return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Not a valid Geohash");}
        if (!u_repository.existsById(u_id)){ return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Not a valid User");}

        String imagelink = "";

        try {
            if (image != null) {
                String u_id_s = String.format("%020d", u_id);
                String g_id_s = String.format("%020d", g_id);

                imagelink = u_id_s + g_id_s + ".jpg";
                storageService.store(image, imagelink);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file");
        }

        HashPost post = new HashPost(
                g_repository.getById(g_id),
                u_repository.getById(u_id),
                text,
                imagelink
        );
        try {
            repository.save(post);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving the post");
        }
    }
}
