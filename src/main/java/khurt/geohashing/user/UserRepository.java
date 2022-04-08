package khurt.geohashing.user;

import khurt.geohashing.geohash.Geohash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users g where g.name=:name", nativeQuery = true)
    User find(@Param("name") String name);
}
