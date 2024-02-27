package SecondRoll.demo.payload;

import java.util.List;

public class OrderDTO {
    private String userId;

    private List<String> gameAdIds;

    private String sellerId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getGameAdIds() {
        return gameAdIds;
    }

    public void setGameAdIds(List<String> gameAdIds) {
        this.gameAdIds = gameAdIds;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
