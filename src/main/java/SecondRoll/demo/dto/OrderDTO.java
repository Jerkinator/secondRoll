package SecondRoll.demo.dto;

public class OrderDTO {
    private String userId;

    //private List<String> gameAdIds;

    private String gameAdId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameAdId() {
        return gameAdId;
    }

    public void setGameAdId(String gameAdId) {
        this.gameAdId = gameAdId;
    }
}
