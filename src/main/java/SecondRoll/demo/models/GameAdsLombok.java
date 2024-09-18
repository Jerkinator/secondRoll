package SecondRoll.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class GameAdsLombok {

    // these parameters will generate their own references through the @id, @DBRef and @CreatedDate annotations
    @Id
    @NotBlank
    private long id;

    @NotBlank
    @DBRef
    private User user;

    @NotBlank
    @CreatedDate
    private LocalDate createdAt;

    @NotBlank
    @CreatedDate
    private LocalDate updatedAt;


    // mandatory parameters when creating a game ad
    @NonNull
    @Size(min = 1, max = 30)
    public String title;

    @NonNull
    @Size(min = 1, max = 999)
    private String description;

    @NonNull
    @Range(min = 1, max = 50000)
    private int price;

    @NotNull
    @Range(min = 1, max = 500)
    private int shippingCost;


    // non-mandatory parameters
    @Size(min = 1, max = 200)
    private String gameCreator;

    @Size(min = 1, max = 200)
    private String gamePlayTime;

    @Size(min = 1, max = 200)
    private String gameRecommendedAge;

    @Size(min = 1, max = 200)
    private String gamePlayers;

    @Size(min = 1, max = 200)
    public List<String> gameGenres = new ArrayList<>();

    private String photoURL;


    public GameAdsLombok() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
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

    public List<String> getGameGenres() {
        return gameGenres;
    }

    public void setGameGenres(List<String> gameGenres) {
        this.gameGenres = gameGenres;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
