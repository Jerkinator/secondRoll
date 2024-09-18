package SecondRoll.demo.services;


import SecondRoll.demo.exception.ServiceException;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameAdsService  {

    private final GameAdsRepository gameAdsRepository;
    private final UserRepository userRepository;
    public GameAdsService(GameAdsRepository gameAdsRepository, UserRepository userRepository) {
        this.gameAdsRepository = gameAdsRepository;
        this.userRepository = userRepository;
    }


    // POST a gameAd with user reference, using a DTO.
    public GameAds createGameAd(CreateGameDTO createGameDTO) {
        User user = userRepository.findById(createGameDTO.getUserId())
                .orElseThrow(() -> new ServiceException("User not found."));

        GameAds gameAd = new GameAds();
        gameAd.setUser(user);
        gameAd.setTitle(createGameDTO.getTitle());
        gameAd.setDescription(createGameDTO.getDescription());
        gameAd.setPrice(createGameDTO.getPrice());
        gameAd.setShippingCost(createGameDTO.getShippingCost());
        gameAd.setCreated_at(createGameDTO.getCreated_at());
        gameAd.setUpdated_at(createGameDTO.getUpdated_at());
        gameAd.setGameCreator(createGameDTO.getGameCreator());
        gameAd.setGamePlayTime(createGameDTO.getGamePlayTime());
        gameAd.setGameRecommendedAge(createGameDTO.getGameRecommendedAge());
        gameAd.setGamePlayers(createGameDTO.getGamePlayers());
        gameAd.setGameGenres(createGameDTO.getGameGenres());

        return gameAdsRepository.save(gameAd);
    }

    // GET all gameAds.
    public List<GameAds> getAllGameAds() {
        return gameAdsRepository.findAll();
    }

    // UPDATE a gameAD
    public GameAdResponse updateGameAd(String id, GameAds updatedGameAd) {
        return gameAdsRepository.findById(id).map(existingGameAd -> {
                    if (updatedGameAd.getTitle() != null) {
                        existingGameAd.setTitle(updatedGameAd.getTitle());
                    }
                    if (updatedGameAd.getDescription() != null) {
                        existingGameAd.setDescription(updatedGameAd.getDescription());
                    }
                    if (updatedGameAd.getUpdated_at() != null) {
                        existingGameAd.setUpdated_at(updatedGameAd.getUpdated_at());
                    }
                    existingGameAd.setPrice(updatedGameAd.getPrice());
                    existingGameAd.setShippingCost(updatedGameAd.getShippingCost());
                    gameAdsRepository.save(existingGameAd);

            return convertToDTO(Optional.of(existingGameAd));
        })
                .orElseThrow(() -> new ServiceException("Game with id " + id + " was not found."));
    }

    // GET a gameAd by id
    public GameAdResponse getGameAdById(String id) {
        Optional<GameAds> gameAd = gameAdsRepository.findById(id);
        return convertToDTO(gameAd);
    }

    // DELETE a gameAd
    public String deleteGameAd(String id) {
        gameAdsRepository.deleteById(id);
        return "Game Ad deleted";
    }

    // UPDATED Find all GameAds by user ID.
    public List<GameAds> getUserGames(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ServiceException("User not found.");
        }
        return gameAdsRepository.findByUserId(userId);
    }

    // Utility method for converting a gameAd to a gameAd DTO.
    public GameAdResponse convertToDTO(Optional<GameAds> gameAd) {
        GameAdResponse gameAdResponse = new GameAdResponse();

        gameAdResponse.setId(gameAd.get().getId());
        gameAdResponse.setSeller(gameAd.get().getUser().getUsername());
        gameAdResponse.setSellerId(gameAd.get().getUser().getId());
        gameAdResponse.setTitle(gameAd.get().getTitle());
        gameAdResponse.setDescription(gameAd.get().getDescription());
        gameAdResponse.setPrice(gameAd.get().getPrice());
        gameAdResponse.setShippingCost(gameAd.get().getShippingCost());
        gameAdResponse.setCreated_at(gameAd.get().getCreated_at());
        gameAdResponse.setUpdated_at(gameAd.get().getUpdated_at());
        gameAdResponse.setGameCreator(gameAd.get().getGameCreator());
        gameAdResponse.setGamePlayTime(gameAd.get().getGamePlayTime());
        gameAdResponse.setGameRecommendedAge(gameAd.get().getGameRecommendedAge());
        gameAdResponse.setGamePlayers(gameAd.get().getGamePlayers());
        gameAdResponse.setGameGenres(gameAd.get().getGameGenres());

        return gameAdResponse;
    }
}