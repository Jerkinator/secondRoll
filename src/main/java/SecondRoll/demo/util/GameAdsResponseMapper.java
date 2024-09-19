package SecondRoll.demo.util;

import SecondRoll.demo.models.GameAdsLombok;
import SecondRoll.demo.payload.response.GameAdResponseLombok;

public class GameAdsResponseMapper {

    public static GameAdResponseLombok mapToResponse(GameAdsLombok gameAdsLombok) {
        return GameAdResponseLombok.builder(gameAdsLombok.getTitle(), gameAdsLombok.getDescription(), gameAdsLombok.getPrice(), gameAdsLombok.getShippingCost())
                .gameRecommendedAge(gameAdsLombok.getGameRecommendedAge())
                .gamePlayers(gameAdsLombok.getGamePlayers())
                .gameGenres(gameAdsLombok.getGameGenres()).build();
    }
}
