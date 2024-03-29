package SecondRoll.demo.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    @NotBlank
    @DBRef
    private List<GameAds> gameAds;

    @NotBlank
    @DBRef
    private User buyer;

    @DBRef
    private User seller;

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


    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
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


    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
