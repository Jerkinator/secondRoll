package SecondRoll.demo.services;


import SecondRoll.demo.exception.ServiceException;
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
public class GameAdsService  {

    @Autowired
    GameAdsRepository gameAdsRepository;
    @Autowired
    UserRepository userRepository;

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
        List<GameAds> gameAds = gameAdsRepository.findAll();
        return gameAds;
    }

    // UPDATE a gameAD
    public GameAds updateGameAd(String id, GameAds updatedGameAd) {
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
                    //  if(updatedGameAd.getGameDetails() != null) {
                    //    existingGameAd.setGameDetails(updatedGameAd.getGameDetails());
                    // }
                    existingGameAd.setPrice(updatedGameAd.getPrice());
                    existingGameAd.setShippingCost(updatedGameAd.getShippingCost());

            return gameAdsRepository.save(existingGameAd);
        })
                .orElseThrow(() -> new ServiceException("Game with id " + id + " was not found."));
    }

    // GET a gameAd by id
    public Optional<GameAds> getGameAdById(String id) {
        return Optional.ofNullable(gameAdsRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Game not found.")));
    }

    /* // TEST get gameAd by ID, can be scrapped.
    public GameAds getGameAdById(String id) {
        return gameAdsRepository.findById(id).orElseThrow(() -> new ServiceException("Game not found."));
    } */


    // DELETE a gameAd
    public String deleteGameAd(String id) {
        gameAdsRepository.deleteById(id);
        return "Game Ad deleted";
    }

    // UPDATED Find all GameAds by user ID.
    public List<GameAds> getUserGames(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ServiceException("User not found.");
        }
        List<GameAds> userGames = gameAdsRepository.findByUserId(userId);
        return userGames;
    }
}