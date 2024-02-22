package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.repository.GameAdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GameAdsService {
    @Autowired
    GameAdsRepository gameAdsRepository;
    // Create a gameAd
    public GameAds createGameAd(GameAds gameAds) {
        return gameAdsRepository.save(gameAds);
    }

    // Get all gameAds
    public List<GameAds> getAllGameAds() {
        return gameAdsRepository.findAll();
    }

    // Update a book
    public GameAds updateGameAd(GameAds gameAds) {
        return gameAdsRepository.save(gameAds);
    }

    // Get a book by id

    public Optional<GameAds> getGameAdById(String id) {
        return gameAdsRepository.findById(id);
    }

    // Delete a book
    public String deleteGameAd(String id) {
        gameAdsRepository.deleteById(id);
        return "Game Ad deleted";
    }

    // Filter by tags
    public List<GameAds> findGameAdsByTags(List<String> tags) {
        return gameAdsRepository.findByTagsIn(tags);
    }

}
