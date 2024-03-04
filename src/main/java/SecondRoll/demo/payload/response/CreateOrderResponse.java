package SecondRoll.demo.payload.response;

public class CreateOrderResponse {
    // add fields that should show in response here
    // make sure they are added to the constructor, remove old constructor and generate new one
    // when adding new fields
    // depending on how many arguments the constructor has you need to pass the same amount of arguments
    // in your service

   private String buyer;


   private int totalPrice;

    public CreateOrderResponse(String buyer, int totalPrice) {
        this.buyer = buyer;
        this.totalPrice = totalPrice;
    }


    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }




    /*public List<GameAds> orderedGames;
    //show price for individual games?
    public int orderTotal;

    public String sellerId;

    public Date orderedAt;

     */
}
