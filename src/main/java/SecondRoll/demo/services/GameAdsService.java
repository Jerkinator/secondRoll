package SecondRoll.demo.services;


import SecondRoll.demo.exception.ServiceException;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public List<GameAdResponse> getAllGameAds() {
        List<GameAds> gameAds = gameAdsRepository.findAll();

        return gameAds.stream().map(this::convertToDTO).collect(Collectors.toList());
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

    // DELETE a gameAd
    public String deleteGameAd(String id) {
        gameAdsRepository.deleteById(id);
        return "Game Ad deleted";
    }

    // UPDATED Find all GameAds by user ID.
    public List<GameAdResponse> getUserOrders(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ServiceException("User not found.");
        }
        List<GameAds> userGames = gameAdsRepository.findByUserId(userId);
        return userGames.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // This utility-method converts the content of a GameAd-object into a GameAdResponse-object.
    private GameAdResponse convertToDTO(GameAds gameAd) {
        GameAdResponse gameAdResponse = new GameAdResponse();

        gameAdResponse.setId(gameAd.getId());
        gameAdResponse.setSeller(gameAd.getUser().getUsername());
        gameAdResponse.setSellerId(gameAd.getUser().getId());
        gameAdResponse.setTitle(gameAd.getTitle());
        gameAdResponse.setDescription(gameAd.getDescription());
        gameAdResponse.setPrice(gameAd.getPrice());
        gameAdResponse.setShippingCost(gameAd.getShippingCost());
        gameAdResponse.setCreated_at(gameAd.getCreated_at());
        gameAdResponse.setUpdated_at(gameAd.getUpdated_at());
        gameAdResponse.setGameCreator(gameAd.getGameCreator());
        gameAdResponse.setGamePlayTime(gameAd.getGamePlayTime());
        gameAdResponse.setGameRecommendedAge(gameAd.getGameRecommendedAge());
        gameAdResponse.setGamePlayers(gameAd.getGamePlayers());
        gameAdResponse.setGameGenres(gameAd.getGameGenres());

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

    // Service for sorting games by price and date in ascending/descending order.
    public List<GameAdResponse> findGames(String key) {
        if (key.equals("priceasc")) {
            List<GameAds> availableAds = gameAdsRepository.findByIsAvailable(true);
            Collections.sort(availableAds, Comparator.comparing(GameAds::getPrice));
            return availableAds.stream().map(this::convertToDTO).collect(Collectors.toList());

        } else if (key.equals("pricedesc")) {
            List<GameAds> availableAds = gameAdsRepository.findByIsAvailable(true);
            Collections.sort(availableAds, Comparator.comparing(GameAds::getPrice).reversed());
            return availableAds.stream().map(this::convertToDTO).collect(Collectors.toList());

        } else if (key.equals("dateasc")) {
            List<GameAds> availableAds = gameAdsRepository.findByIsAvailable(true);
            Collections.sort(availableAds, Comparator.comparing(GameAds::getCreated_at));
            return availableAds.stream().map(this::convertToDTO).collect(Collectors.toList());

        } else if (key.equals("datedesc")) {
            List<GameAds> availableAdsDateDesc = gameAdsRepository.findByIsAvailable(true);
            Collections.sort(availableAdsDateDesc, Comparator.comparing(GameAds::getCreated_at).reversed());
            return availableAdsDateDesc.stream().map(this::convertToDTO).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}