package khurt.geohashing.hashpost;

import khurt.geohashing.geohash.Geohash;
import khurt.geohashing.user.User;

import javax.persistence.*;

@Entity
@Table(name = "hashpost")
public class HashPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long h_id;

    @ManyToOne
    @JoinColumn(name = "g_id")
    private Geohash geohash;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "imagelink")
    private String imagelink;

    public HashPost(){

    }

    public HashPost(Geohash geohash, User user, String text, String imagelink){
        this.geohash = geohash;
        this.user = user;
        this.text = text;
        this.imagelink = imagelink;
    }

    public long getH_id() {
        return h_id;
    }

    public Geohash getGeohash() {
        return geohash;
    }

    public void setGeohash(Geohash geohash) {
        this.geohash = geohash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
