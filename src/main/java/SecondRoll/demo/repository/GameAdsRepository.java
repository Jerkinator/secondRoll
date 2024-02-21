package SecondRoll.demo.repository;

import SecondRoll.demo.models.GameAds;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameAdsRepository extends MongoRepository<GameAds, String> {
}