package SecondRoll.demo.payload.response;

import SecondRoll.demo.models.GameAds;

import java.util.Date;
import java.util.List;

public class CreateOrderResponse {

    public String buyerId;

    public List<GameAds> orderedGames;
    //show price for individual games?
    public int orderTotal;

    public String sellerId;

    public Date orderedAt;
}
