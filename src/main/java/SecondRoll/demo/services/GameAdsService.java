package SecondRoll.demo.services;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GameAdsService {
    @Autowired
    GameAdsRepository gameAdsRepository;
    @Autowired
    UserRepository userRepository;

    // Create a gameAd
    public GameAds createGameAd(GameAds gameAds) {
        return gameAdsRepository.save(gameAds);
    }

    // Get all gameAds
    public List<GameAds> getAllGameAds() {
        return gameAdsRepository.findAll();
    }

    // Update a gameAd
    public GameAds updateGameAd(GameAds gameAds) {
        return gameAdsRepository.save(gameAds);
    }

    // Get a gameAd by id

    public Optional<GameAds> getGameAdById(String id) {
        return gameAdsRepository.findById(id);
    }

    // Delete a gameAd
    public String deleteGameAd(String id) {
        gameAdsRepository.deleteById(id);
        return "Game Ad deleted";
    }


    // Filter by tags
    public List<GameAds> findGameAdsByGameDetails(List<EGameCategory> gameDetails) {
        return gameAdsRepository.findByGameDetailsIn(gameDetails);
    }

    // Find GameAds by user ID.
    public Optional<GameAds> getGameByUserId(String userId) {
        return gameAdsRepository.findById(userId);
    }
}