package SecondRoll.demo.payload.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameAdResponse {

    private String seller;

    private String title;

    private String description;

    private int price;

    private int shippingCost;

    private String gameCreator;

    private String gamePlayTime;

    private String gameRecommendedAge;

    private String gamePlayers;

    private List<String> gameGenres = new ArrayList<>();

    private String photoURL;

    private LocalDate created_at;

    private LocalDate updated_at;

    //public List<EGameCategory> gameDetails = new ArrayList<>();


    



    public GameAdResponse() {
    }

   // public GameAdResponse(String seller, String title, String description, int price, int shippingCost, String gameCreator, String gamePlayTime, String gameRecommendedAge, String gamePlayers, List<String> gameGenres, LocalDate created_at, LocalDate updated_at) {
    
      public GameAdResponse(String seller, String title, String description, int price, int shippingCost,
        String gameCreator, String gamePlayTime, String gameRecommendedAge, String gamePlayers, List<String> gameGenres,
        String photoURL, LocalDate created_at, LocalDate updated_at) {
        
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.price = price;
        this.shippingCost = shippingCost;
        this.gameCreator = gameCreator;
        this.gamePlayTime = gamePlayTime;
        this.gameRecommendedAge = gameRecommendedAge;
        this.gamePlayers = gamePlayers;
        this.gameGenres = gameGenres;
        this.price = price;
        this.shippingCost = shippingCost;
        this.photoURL = photoURL;

        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    // private boolean isAvailable? Returns twice in postman right now. Looking for a fix.

    // GETTERS & SETTERS.

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

  /*  public List<EGameCategory> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<EGameCategory> gameDetails) {
        this.gameDetails = gameDetails;
    }*/
}