package khurt.geohashing.geohash;

import khurt.geohashing.hashpost.HashPost;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "geohashes")
public class Geohash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long g_id;

    @Column(name = "location_x")
    private double pos_x;

    @Column(name = "location_y")
    private double pos_y;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @OneToMany(mappedBy = "geohash")
    private List<HashPost> posts;

    public Geohash(){

    }

    public Geohash(double pos_x, double pos_y, LocalDate date){
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.date = date;
    }

    public double getPos_x() {
        return pos_x;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
    }

    public double getPos_y() {
        return pos_y;
    }

    public void setPos_y(double pos_y) {
        this.pos_y = pos_y;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getG_id() {
        return g_id;
    }
}
