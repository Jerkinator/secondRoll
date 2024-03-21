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
        gameAd.setPhotoURL(createGameDTO.getPhotoURL());

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

        gameAdResponse.setSeller(gameAd.getUser().getUsername());
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
        gameAdResponse.setPhotoURL(gameAd.getPhotoURL());

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

    // Method to get available gameAds in ascending price order.
    public List<GameAds> findAvailableGameAdsSortedByPriceAsc() {

        List<GameAds> availableAdsPriceAsc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsPriceAsc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Price
        Collections.sort(availableAdsPriceAsc, Comparator.comparing(GameAds::getPrice));

        return availableAdsPriceAsc;
    }

    // Method to get available gameAds in descending price order.
    public List<GameAds> findAvailableGameAdsSortedByPriceDesc() {

        List<GameAds> availableAdsPriceDesc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsPriceDesc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Price descending by chaining .reversed
        Collections.sort(availableAdsPriceDesc, Comparator.comparing(GameAds::getPrice).reversed());

        return availableAdsPriceDesc;
    }

    // Sorting available ads based on date created ascending order.
    public List<GameAds> availableGameAdsSortedByDateAsc() {

        List<GameAds> availableAdsDateAsc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsDateAsc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Created_at
        Collections.sort(availableAdsDateAsc, Comparator.comparing(GameAds::getCreated_at));

        return availableAdsDateAsc;
    }

    // Sorting available ads based on date created descending order.
    public List<GameAds> availableGameAdsSortedByDateDesc() {

        List<GameAds> availableAdsDateDesc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsDateDesc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Created_at
        Collections.sort(availableAdsDateDesc, Comparator.comparing(GameAds::getCreated_at).reversed());

        return availableAdsDateDesc;
    }

    public List<GameAds> getGameAdsByGenre(String genre) {
        List<GameAds> AdsByGenre = gameAdsRepository.findByGameGenres(genre);
        return AdsByGenre;
    }
}