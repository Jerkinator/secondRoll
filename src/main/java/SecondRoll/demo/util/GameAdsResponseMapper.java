package SecondRoll.demo.util;

import SecondRoll.demo.models.GameAdsLombok;
import SecondRoll.demo.payload.response.GameAdResponseLombok;

public class GameAdsResponseMapper {

    // a static helper method for mapping database object to response object
    public static GameAdResponseLombok mapToResponse(GameAdsLombok gameAdsLombok) {
        return GameAdResponseLombok.builder()
                .title(gameAdsLombok.getTitle())
                .description(gameAdsLombok.getDescription())
                .price(gameAdsLombok.getPrice())
                .shippingCost(gameAdsLombok.getShippingCost())
                .gameCreator(gameAdsLombok.getGameCreator())
                .gamePlayTime(gameAdsLombok.getGamePlayTime())
                .gameRecommendedAge(gameAdsLombok.getGameRecommendedAge())
                .gamePlayers(gameAdsLombok.getGamePlayers())
                .gameGenres(gameAdsLombok.getGameGenres()).build();
    }
}
