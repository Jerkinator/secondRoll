package SecondRoll.demo.payload;

//HELENA:
// fundera på hur ni vill visa er data i frontend? ska man se id på alla GameAds i sin favorit-lista?
// så kan ni tänka och se över samtliga DTO och/eller payloads

//Data Transfer Object for GameAds.
public class WishlistDTO {

    private String gameId;

    //Getters.
    public String getGameId() {
        return gameId;
    }

    //Setters.
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}