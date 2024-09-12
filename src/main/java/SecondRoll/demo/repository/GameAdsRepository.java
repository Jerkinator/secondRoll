package SecondRoll.demo.repository;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.payload.response.GameAdSearchResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameAdsRepository extends MongoRepository<GameAds, String> {


    List<GameAds> findByIsAvailable(boolean isAvailable);

    List<GameAds> findByUserId(String userId);

    List<GameAds> findByPrice(int price);

    List<GameAds> findByTitleIgnoreCase(String title);

    List<GameAdSearchResponse> findByGameGenres(String genre);

    List<GameAdSearchResponse> findByGameCreator(String creator);

    List<GameAdSearchResponse> findByGamePlayTime(String gameTime);

    List<GameAdSearchResponse> findByGameRecommendedAge(String recommendedAge);

    List<GameAdSearchResponse> findByGamePlayers(String players);

}

