package SecondRoll.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "gameAds")
public class GameAds {

    @Id
    private String id;

    @DBRef
    private User user;

    private String title;

    private String description;

    private int price;

    private int shippingCost = 50;
    @CreatedDate
    private LocalDate created_at;
    @CreatedDate
    private LocalDate updated_at;

    // ArrayList of gameAds for picking a randomized game ad for a user
    private List<GameAds> gameAdsList;

    public List<GameAds> getGameAdsList() {
        return gameAdsList;
    }

    // ------------------------------------

    public List<EGameCategory> gameDetails = new ArrayList<>();

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable = true;

   // public List<EGameCategory> tags = new ArrayList<>();


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

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

   public List<EGameCategory> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<EGameCategory> gameDetails) {
        this.gameDetails = gameDetails;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


   /* public List<EGameCategory> getTags() {
        return tags;
    }

    public void setTags(List<EGameCategory> tags) {
        this.tags = tags;
    }*/
}

