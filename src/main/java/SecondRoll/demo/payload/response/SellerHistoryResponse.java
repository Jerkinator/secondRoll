package SecondRoll.demo.payload.response;

import SecondRoll.demo.payload.OrderGameDetailsDTO;

import java.util.Date;
import java.util.List;

public class SellerHistoryResponse {
    private String seller;
    private String buyer;
    private List<OrderGameDetailsDTO> orderedGames;
    private double shippingTotal;
    private double gameTotal;
    private double orderTotal;
    private Date orderedDate;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public List<OrderGameDetailsDTO> getOrderedGames() {
        return orderedGames;
    }

    public void setOrderedGames(List<OrderGameDetailsDTO> orderedGames) {
        this.orderedGames = orderedGames;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public double getShippingTotal() {
        return shippingTotal;
    }

    public void setShippingTotal(double shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    public double getGameTotal() {
        return gameTotal;
    }

    public void setGameTotal(double gameTotal) {
        this.gameTotal = gameTotal;
    }
}