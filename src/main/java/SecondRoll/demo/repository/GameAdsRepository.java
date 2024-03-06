package SecondRoll.demo.repository;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameAdsRepository extends MongoRepository<GameAds, String> {

    List<GameAds> findByGameDetailsIn(List<EGameCategory> gameDetails);

    List<GameAds> findByUserId(String userId);

    List<GameAds> findByPrice(List<GameAds> price);

}

