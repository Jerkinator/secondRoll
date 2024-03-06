package SecondRoll.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    // HELENA: kanske att det här skulle heta seller istället för att vara extra tydligt
    @NotBlank
    @DBRef
    private User user;
    @NotBlank
    private String title;
    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;
    @NotBlank
    @Size(min = 1, max = 50000)
    private int price;
    @NotBlank
    @Size(min = 1, max = 2000)
    private int shippingCost;
    @CreatedDate
    private LocalDate created_at;
    @CreatedDate
    private LocalDate updated_at;

    public List<EGameCategory> gameDetails = new ArrayList<>();
    private EGameCategory genres;
    public GameAds(String genres){

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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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

