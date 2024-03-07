package SecondRoll.demo.payload.response;

import java.time.LocalDate;

public class GameAdResponse {

    private String seller;

    private String title;

    private String description;

    private int price;

    private int shippingCost;

    private LocalDate created_at;

    private LocalDate updated_at;

    //public List<EGameCategory> gameDetails = new ArrayList<>();

    public GameAdResponse(String seller, String title, String description, int price, int shippingCost,
        LocalDate created_at, LocalDate updated_at) {
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.price = price;
        this.shippingCost = shippingCost;
       // this.gameDetails = gameDetails;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GameAdResponse() {
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

  /*  public List<EGameCategory> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<EGameCategory> gameDetails) {
        this.gameDetails = gameDetails;
    }*/
}