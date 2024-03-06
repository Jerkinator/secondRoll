package SecondRoll.demo.services;

import SecondRoll.demo.exception.EntityNotFoundException;
import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameAdsService {

    @Autowired
    GameAdsRepository gameAdsRepository;
    @Autowired
    UserRepository userRepository;

    // POST a gameAd with user reference, using a DTO.
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

        return gameAdsRepository.save(gameAd);
    }

    // UPDATED GET all gameAds.
    public List<GameAdResponse> getAllGameAds() {
        List<GameAds> gameAds = gameAdsRepository.findAll();

        return gameAds.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // UPDATED UPDATE a gameAD
    public GameAds updateGameAd(String id, GameAds updatedGameAd) {
        return gameAdsRepository.findById(id).map(existingGameAd -> {
            if(updatedGameAd.getTitle() != null) {
                existingGameAd.setTitle(updatedGameAd.getTitle());
            }
            if(updatedGameAd.getDescription() != null) {
                existingGameAd.setDescription(updatedGameAd.getDescription());
            }
            if(updatedGameAd.getUpdated_at() != null) {
                existingGameAd.setUpdated_at(updatedGameAd.getUpdated_at());
            }
            if(updatedGameAd.getGameDetails() != null) {
                existingGameAd.setGameDetails(updatedGameAd.getGameDetails());
            }
            existingGameAd.setPrice(updatedGameAd.getPrice());
            existingGameAd.setShippingCost(updatedGameAd.getShippingCost());

            return gameAdsRepository.save(existingGameAd);
        })
                .orElseThrow(() -> new EntityNotFoundException("Game with id " + id + " was not found."));
    }

    // GET a gameAd by id
    public Optional<GameAds> getGameAdById(String id) {
        return Optional.ofNullable(gameAdsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found.")));
    }

    // DELETE a gameAd
    public String deleteGameAd(String id) {
        gameAdsRepository.deleteById(id);
        return "Game Ad deleted";
    }

    // FILTER by enum
    public List<GameAds> findGameAdsByGameDetails(List<EGameCategory> gameDetails) {
        return gameAdsRepository.findByGameDetailsIn(gameDetails);
    }

    // UPDATED Find all GameAds by user ID.
    public List<GameAdResponse> getUserOrders(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        List<GameAds> userGames = gameAdsRepository.findByUserId(userId);
        return userGames.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // This utility-method converts the content of a GameAd-object into a GameAdResponse-object.
    private GameAdResponse convertToDTO(GameAds gameAd) {
        GameAdResponse gameAdResponse = new GameAdResponse();

        gameAdResponse.setUsername(gameAd.getUser().getUsername());
        gameAdResponse.setTitle(gameAd.getTitle());
        gameAdResponse.setDescription(gameAd.getDescription());
        gameAdResponse.setPrice(gameAd.getPrice());
        gameAdResponse.setShippingCost(gameAd.getShippingCost());
        gameAdResponse.setCreated_at(gameAd.getCreated_at());
        gameAdResponse.setUpdated_at(gameAd.getUpdated_at());
        gameAdResponse.setGameDetails(gameAd.gameDetails);

        return gameAdResponse;
    }

    // "Roll the Dice" game ad randomizer
    public GameAds getRandomGameAd() {
        Random randomGameAd = new Random();
        List<GameAds> allGameAds = gameAdsRepository.findAll();
        int maxInt = allGameAds.size();
        GameAds gameAds = allGameAds.get(randomGameAd.nextInt(maxInt));
        return gameAds;
    }
}


   /* // OLD GET ALL gameAds, stored for now, just in case.
    public List<GameAds> getAllGameAds() {
        return gameAdsRepository.findAll();
    } */


   /* // OLD GET all GameAds by user ID, stored for now, just in case.
    public List<GameAds> findGameAdsByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow();

        List<GameAds> gameAds = gameAdsRepository.findAll();
        List<GameAds> foundGames = new ArrayList<>();
        for(GameAds gameAd : gameAds) {
            if(Objects.equals(gameAd.getUser().getId(), user.getId())) {
                foundGames.add(gameAd);
            }
        }
        return foundGames;
    } */


  /*
    // OLD UPDATE a gameAd, stored for now, just in case.
    public GameAds updateGameAd(GameAds gameAds) {
        return gameAdsRepository.save(gameAds);
    } */