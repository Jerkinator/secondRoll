package SecondRoll.demo.payload.response;

import SecondRoll.demo.payload.OrderGameDetailsDTO;

import java.util.Date;
import java.util.List;

public class OrderResponse {

    private Date orderedDate;
    private String buyerName;
    private String sellerName;
    private List<OrderGameDetailsDTO> orderedGames;
    private double shippingTotal;
    private double gameTotal;
    private double orderTotal;



    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<OrderGameDetailsDTO> getOrderedGames() {
        return orderedGames;
    }

    public void setOrderedGames(List<OrderGameDetailsDTO> orderedGames) {
        this.orderedGames = orderedGames;
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

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
