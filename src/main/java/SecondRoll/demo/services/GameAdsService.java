package SecondRoll.demo.services;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
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

    // Create a gameAd with user reference, using a DTO.
    public GameAds createGameAd(CreateGameDTO createGameDTO) {
        User user = userRepository.findById(createGameDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        GameAds gameAd = new GameAds();
        gameAd.setUser(user);
        gameAd.setTitle(createGameDTO.getTitle());
        gameAd.setDescription(createGameDTO.getDescription());
        gameAd.setPrice(createGameDTO.getPrice());
        gameAd.setShippingCost(createGameDTO.getShippingCost());
        gameAd.setCreated_at(createGameDTO.getCreated_at());
        gameAd.setUpdated_at(createGameDTO.getUpdated_at());
        gameAd.setGameDetails(createGameDTO.getGameDetails());
        gameAd.setAvailable(createGameDTO.isAvailable);

        return gameAdsRepository.save(gameAd);
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

    // Find all GameAds by user ID. //TestComment.
    public Optional<GameAds> findAllGamesByUserId(String userId) {
       return gameAdsRepository.findById(userId);
    }
}