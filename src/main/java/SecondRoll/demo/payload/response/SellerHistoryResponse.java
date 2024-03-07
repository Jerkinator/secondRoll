package SecondRoll.demo.payload.response;

import java.util.Date;
import java.util.List;

public class SellerHistoryResponse {
    private String sellerId;


    private List<String> soldGameIds;

    private Date saleDate;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<String> getSoldGameIds() {
        return soldGameIds;
    }

    public void setSoldGameIds(List<String> soldGameIds) {
        this.soldGameIds = soldGameIds;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
