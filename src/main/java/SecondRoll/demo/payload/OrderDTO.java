package SecondRoll.demo.payload;

import java.util.Date;
import java.util.List;

public class OrderDTO {



    private String buyerId;

    private List<String> gameAdIds;

    private Date orderedDate;


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


    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
