package khurt.geohashing;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "u_id")
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "location_X")
    public float pos_x;

    @Column(name = "location_y")
    public float pos_y;
}
