package SecondRoll.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "gameAds")
public class GameAds {

    @Id
    private String id;

    private  String userId;

    private String title;

    private String description;

    private int price;

    private int shippingCost = 50;
    @CreatedDate
    private LocalDate created_at;
    @CreatedDate
    private LocalDate updated_at;

    public List<EGameCategory> gameDetails = new ArrayList<>();

    public boolean isAvailable = true;

   // public List<EGameCategory> tags = new ArrayList<>();



    public GameAds() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public String getUserId() {
        return userId;
    }

   public List<EGameCategory> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<EGameCategory> gameDetails) {
        this.gameDetails = gameDetails;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


   /* public List<EGameCategory> getTags() {
        return tags;
    }

    public void setTags(List<EGameCategory> tags) {
        this.tags = tags;
    }*/
}

