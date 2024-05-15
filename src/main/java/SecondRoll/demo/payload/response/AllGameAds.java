package SecondRoll.demo.payload.response;

public class AllGameAds {

    private String id;

    private String title;

    private String description;

    private int price;

    private int shippingCost;

    private String gameCreator;

    private String gamePlayTime;

    private String gameRecommendedAge;

    private String gamePlayers;


    public AllGameAds(String id, String title, String description, int price, int shippingCost, String gameCreator, String gamePlayTime, String gameRecommendedAge, String gamePlayers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.shippingCost = shippingCost;
        this.gameCreator = gameCreator;
        this.gamePlayTime = gamePlayTime;
        this.gameRecommendedAge = gameRecommendedAge;
        this.gamePlayers = gamePlayers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}