package SecondRoll.demo.payload.response;

import java.util.Date;
import java.util.List;

public class BuyerHistoryResponse {

   private String buyerId;


    private List<String> orderedGameIds;

    private Date orderedDate;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }





    public List<String> getOrderedGameIds() {
        return orderedGameIds;
    }

    public void setOrderedGameIds(List<String> orderedGameIds) {
        this.orderedGameIds = orderedGameIds;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }




}
