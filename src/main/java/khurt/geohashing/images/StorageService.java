package khurt.geohashing.images;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StorageService {
    private final Path root;


    public StorageService(){
       root = Paths.get("files");
       if (!Files.exists(root)){
           try {
               Files.createDirectory(root);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public void store(MultipartFile file){
        try {
            if (file.isEmpty()) { return; }
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path load(String filename) {
        return root.resolve(filename);
    }

    public Resource loadAsResource(String filename) throws ImageNotFoundException {
            Path file = load(filename);
            try {
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) return resource;
                else throw new ImageNotFoundException();
            }catch (Exception e){
                throw new ImageNotFoundException();
            }
    }
}
