package khurt.geohashing;

import javax.persistence.*;

@Entity
@Table(name = "hashpost")
public class Hashpost {
    @Id
    @Column(name = "g_id")
    public int g_id;

    @Id
    @Column(name = "u_id")
    public int u_id;

    @Column(name = "text")
    public String text;

    @Column(name = "imagelink")
    public String imagelink;
}