package SecondRoll.demo.repository;

import SecondRoll.demo.models.GameAds;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameAdsRepository extends MongoRepository<GameAds, String> {


    List<GameAds> findByGameDetailsIn(List<GameAds> gameDetails);

}

