package SecondRoll.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @DBRef
    private List<GameAds> gameAds;

    @DBRef
    //shows as buyer in database
    @Field(value = "buyer")
    private User user;

    @CreatedDate
    private Date orderedAt;


    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
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

    public List<GameAds> getGameAds() {
        return gameAds;
    }

    public void setGameAds(List<GameAds> gameAds) {
        this.gameAds = gameAds;
    }
}