package SecondRoll.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "orders")
public class Order {
    @Id
    private String Id;

    //@DBRef
    //private List<GameAds> gameAds = new ArrayList<>();

    @DBRef
    private GameAds gameAds;

    @DBRef
    private User user;

    @CreatedDate
    private Date orderedAt;


    public Order() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public GameAds getGameAds() {
        return gameAds;
    }

    public void setGameAds(GameAds gameAds) {
        this.gameAds = gameAds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }
}
