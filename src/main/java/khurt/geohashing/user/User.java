package khurt.geohashing.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long u_id;

    @Column(name = "name")
    private String name;

    @Column(name = "location_x")
    private double pos_x;

    @Column(name = "location_y")
    private double pos_y;

    public User(){

    }
    public User(String name, double pos_x, double pos_y){
        this.name = name;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public long getId() {
        return u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString(){
        return name + String.format("(%f/%f)", this.pos_x, this.pos_y);
    }
}
