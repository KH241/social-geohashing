package khurt.geohashing.geohash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface GeohashRepository extends JpaRepository<Geohash, Long> {

    @Query(value = "select * from geohashes g where g.date=:date and g.location_x > :pos_x and g.location_x < :pos_x+1 and g.location_y > :pos_y and g.location_y < :pos_y+1", nativeQuery = true)
    Geohash find(@Param("date") LocalDate date, @Param("pos_x") int pos_x, @Param("pos_y") int pos_y);
}
