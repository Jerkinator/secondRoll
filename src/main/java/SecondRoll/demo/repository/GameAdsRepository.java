package SecondRoll.demo.repository;

import SecondRoll.demo.models.GameAds;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameAdsRepository extends MongoRepository<GameAds, String> {



   // List<GameAds> findByGameDetailsIn(List<EGameCategory> gameDetails);

  //  List<GameAds> findByGameDetailsIn(List<EGameCategory> gameDetails);

    List<GameAds> findByIsAvailable(boolean isAvailable);

    List<GameAds> findByUserId(String userId);

    List<GameAds> findByPrice(List<GameAds> price);

    List<GameAds> findByTitle(String title);

    List<GameAds> findByGameGenres(String genre);

    List<GameAds> findByGameCreator(String creator);

    List<GameAds> findByGamePlayTime(String gameTime);

    List<GameAds> findByGameRecommendedAge(String recommendedAge);

    List<GameAds> findByGamePlayers(String players);

}

