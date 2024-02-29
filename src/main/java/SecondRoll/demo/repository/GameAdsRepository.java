package SecondRoll.demo.repository;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameAdsRepository extends MongoRepository<GameAds, String> {


    List<GameAds> findByGameDetailsIn(List<EGameCategory> gameDetails);

    Page<GameAds> findByGameDetailsIn(Pageable pageable);
}

