package SecondRoll.demo.payload;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.payload.response.GameAdResponse;

public class GameAdDTOConverter {

    // Utility method for converting gameAd to gameAd DTO containing only game information for user to see.
    public GameAdResponse convertToDTO(GameAds gameAd) {
        GameAdResponse gameAdResponse = new GameAdResponse();

        gameAdResponse.setId(gameAd.getId());
        gameAdResponse.setSeller(gameAd.getUser().getUsername());
        gameAdResponse.setSellerId(gameAd.getUser().getId());
        gameAdResponse.setTitle(gameAd.getTitle());
        gameAdResponse.setDescription(gameAd.getDescription());
        gameAdResponse.setPrice(gameAd.getPrice());
        gameAdResponse.setShippingCost(gameAd.getShippingCost());
        gameAdResponse.setCreated_at(gameAd.getCreated_at());
        gameAdResponse.setUpdated_at(gameAd.getUpdated_at());
        gameAdResponse.setGameCreator(gameAd.getGameCreator());
        gameAdResponse.setGamePlayTime(gameAd.getGamePlayTime());
        gameAdResponse.setGameRecommendedAge(gameAd.getGameRecommendedAge());
        gameAdResponse.setGamePlayers(gameAd.getGamePlayers());
        gameAdResponse.setGameGenres(gameAd.getGameGenres());

        return gameAdResponse;
    }
}