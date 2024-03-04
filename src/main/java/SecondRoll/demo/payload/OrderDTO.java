package SecondRoll.demo.payload;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class OrderDTO {
    @NotBlank
    private String buyerId;

    @NotBlank
    private List<String> gameAdIds;


    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<String> getGameAdIds() {
        return gameAdIds;
    }

    public void setGameAdIds(List<String> gameAdIds) {
        this.gameAdIds = gameAdIds;
    }

}
