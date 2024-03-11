package SecondRoll.demo.services;


import SecondRoll.demo.exception.EntityNotFoundException;
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
                .orElseThrow(() -> new RuntimeException("User not found."));


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
        // gameAd.setAvailable(createGameDTO.isAvailable);

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

    //HELENA:
    // jag tror ni vet att Enum behöver konverteras till String :)
    // och ni skulle också kunna förbättra er findGameAdsByUserId
    // efter det vi gick igenom på lektionen
    // ni får själva avgöra hur ni väljer att prioritera för metoden fungerar ju som den är
    // tycker ni det är viktigt att ändra den eller inte?

    // :OBS: METODEN FINDGAMEADSBYUSERID ÄR FIXAD NU.

    // BÅDE GAMEADS OCH ORDER SERVICE:
    // bör ni se över hur tex user lägger sig i responses, för jag antar att det är hela usern som syns där?
    // om det är så bör ni nog prioritera att fixa detta dels för att det gör att era response objekt är
    // otroligt stora om alla info om varje objectid referens ska finnas med och dels för att det här
    // kan exposa känslig data och det är inte bra. Se över era responses när det gäller referenser med ObjectId
    // vilken data räcker att ha med i ett respons objekt för respektive metod?

    // :OBS: USERINFON I GAMEADS ÄR FIXAD NU MED GAMEADRESPONSEDTO.

    // Filter by tags
    /*public List<GameAds> findGameAdsByGameDetails(List<EGameCategory> gameDetails) {
        return gameAdsRepository.findByGameDetailsIn(gameDetails);
    }*/

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

        gameAdResponse.setSeller(gameAd.getUser().getUsername());
        gameAdResponse.setTitle(gameAd.getTitle());
        gameAdResponse.setDescription(gameAd.getDescription());
        gameAdResponse.setPrice(gameAd.getPrice());
        gameAdResponse.setShippingCost(gameAd.getShippingCost());
        gameAdResponse.setCreated_at(gameAd.getCreated_at());
        gameAdResponse.setUpdated_at(gameAd.getUpdated_at());
        //   gameAdResponse.setGameDetails(gameAd.gameDetails);

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

    ////method to get available gameAds in ascending price order
    public List<GameAds> findAvailableGameAdsSortedByPriceAsc() {

        List<GameAds> availableAdsPriceAsc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsPriceAsc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Price
        Collections.sort(availableAdsPriceAsc, Comparator.comparing(GameAds::getPrice));

        return availableAdsPriceAsc;

    }

    //method to get available gameAds in descending price order
    public List<GameAds> findAvailableGameAdsSortedByPriceDesc() {

        List<GameAds> availableAdsPriceDesc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsPriceDesc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Price descending by chaining .reversed
        Collections.sort(availableAdsPriceDesc, Comparator.comparing(GameAds::getPrice).reversed());

        return availableAdsPriceDesc;

    }

    //sorting available ads based on date created ascending order
    public List<GameAds> availableGameAdsSortedByDateAsc() {

        List<GameAds> availableAdsDateAsc = new ArrayList<>();
        //populates the list by using findByIsAvailable method where boolean is set to true
        availableAdsDateAsc = gameAdsRepository.findByIsAvailable(true);

        // sorting the ads in available ads array based on Created_at
        Collections.sort(availableAdsDateAsc, Comparator.comparing(GameAds::getCreated_at));

        return availableAdsDateAsc;
    }

    //sorting available ads based on date created descending order
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

   /* //Unfinished method for finding all games by price.
    public List<GameAds> findGameAdsByPrice(List price) {
        List<GameAds> gamePrice = gameAdsRepository.findByPrice(price);
        return gamePrice;
    } */

        // Converter method
   /* private GameAds convertToDTO(GameAds gameAds) {
        GameAds gameAd = new GameAds();
        gameAd.set;




}

        return gameAd;
    } */



