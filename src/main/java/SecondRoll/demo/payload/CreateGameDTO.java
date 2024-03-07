package SecondRoll.demo.payload;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

public class CreateGameDTO {

    private String userId;

    private String title;

    private String description;

    private int price;

    private String gameCreator;

    private String gamePlayTime;

    private String gameRecommendedAge;

    private String gamePlayers;

   // private String gameGenre;
    public List<String> gameGenres;

    private int shippingCost = 50;

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



    //public List<EGameCategory> gameDetails;

    // private boolean isAvailable? Returns twice in postman right now. Looking for a fix.

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

   /* public List<EGameCategory> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<EGameCategory> gameDetails) {
        this.gameDetails = gameDetails;

    }*/

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

   /* public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }*/



}