package khurt.geohashing.geohash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GeohashController {
    @Autowired
    GeohashRepository repository;

    @GetMapping("/geohash/{datestring}/{pos_x}/{pos_y}")
    public Geohash getGeoHash(@PathVariable String datestring, @PathVariable int pos_x, @PathVariable int pos_y) {
        LocalDate date = LocalDate.parse(datestring);
        Geohash hash = repository.find(date, pos_x, pos_y);

        if (hash != null){ return hash; }

        hash = GeohashGenerator.generate(date, pos_x, pos_y);
        repository.save(hash);
        return hash;
    }

    //TODO remove
    @GetMapping("/geohash")
    public List<Geohash> getAll() {
        return repository.findAll();
    }
}
