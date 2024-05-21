package SecondRoll.demo.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

public class CreateGameDTO {
    @NotBlank
    private String userId;

    @NotBlank
    @Size(min = 1, max = 30)
    private String title;

    @NotBlank
    @Size(min = 1, max = 999)
    private String description;

    @NotNull
    @Range(min = 1, max = 50000)
    private int price;

    @NotBlank
    @Size(min = 1, max = 200)
    private String gameCreator;

    @NotBlank
    @Size(min = 1, max = 200)
    private String gamePlayTime;

    @NotBlank
    @Size(min = 1, max = 200)
    private String gameRecommendedAge;

    @NotBlank
    @Size(min = 1, max = 200)
    private String gamePlayers;

    public List<String> gameGenres;

 //   @NotBlank
   // private String photoURL;

    @NotNull
    @Range(min = 1, max = 500)
    private int shippingCost;

    @CreatedDate
    private LocalDate created_at;

    @CreatedDate
    private LocalDate updated_at;

    private boolean isAvailable = true;

    public List<String> getGameGenres() {
        return gameGenres;
    }

    public void setGameGenres(List<String> gameGenres) {
        this.gameGenres = gameGenres;
    }

    // GETTERS & SETTERS.

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public String getGameCreator() {
        return gameCreator;
    }

    public void setGameCreator(String gameCreator) {
        this.gameCreator = gameCreator;
    }

    public String getGamePlayTime() {
        return gamePlayTime;
    }

    public void setGamePlayTime(String gamePlayTime) {
        this.gamePlayTime = gamePlayTime;
    }

    public String getGameRecommendedAge() {
        return gameRecommendedAge;
    }

    public void setGameRecommendedAge(String gameRecommendedAge) {
        this.gameRecommendedAge = gameRecommendedAge;
    }

    public String getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(String gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    /*public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }*/
}