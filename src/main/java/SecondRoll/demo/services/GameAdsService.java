package SecondRoll.demo.services;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        // gameAd.setAvailable(createGameDTO.isAvailable);

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

    //HELENA:
    // jag tror ni vet att Enum behöver konverteras till String :)
    // och ni skulle också kunna förbättra er findGameAdsByUserId
    // efter det vi gick igenom på lektionen
    // ni får själva avgöra hur ni väljer att prioritera för metoden fungerar ju som den är
    // tycker ni det är viktigt att ändra den eller inte?

    // BÅDE GAMEADS OCH ORDER SERVICE:
    // bör ni se över hur tex user lägger sig i responses, för jag antar att det är hela usern som syns där?
    // om det är så bör ni nog prioritera att fixa detta dels för att det gör att era response objekt är
    // otroligt stora om alla info om varje objectid referens ska finnas med och dels för att det här
    // kan exposa känslig data och det är inte bra. Se över era responses när det gäller referenser med ObjectId
    // vilken data räcker att ha med i ett respons objekt för respektive metod?

    // Filter by tags
    public List<GameAds> findGameAdsByGameDetails(List<EGameCategory> gameDetails) {
        return gameAdsRepository.findByGameDetailsIn(gameDetails);
    }


    public List<GameAds> findGameAdsByPrice(List price) {
        List<GameAds> gamePrice = gameAdsRepository.findByPrice(price);
        return gamePrice;
    }


    // Find GameAds by user ID.
    public Optional<GameAds> getGameByUserId(String userId) {
        return gameAdsRepository.findById(userId);
    }
        // Find all GameAds by user ID.
        public List<GameAds> findGameAdsByUserId (String userId){
            User user = userRepository.findById(userId).orElseThrow();

            List<GameAds> gameAds = gameAdsRepository.findAll();
            List<GameAds> foundGames = new ArrayList<>();
            for (GameAds gameAd : gameAds) {
                if (Objects.equals(gameAd.getUser().getId(), user.getId())) {
                    foundGames.add(gameAd);
                }
            }
            return foundGames;
        }



    // "Roll the Dice" game ad randomizer
    public GameAds getRandomGameAd() {
        Random randomGameAd = new Random();
        List<GameAds> allGameAds = gameAdsRepository.findAll();
        int maxInt = allGameAds.size();
        GameAds gameAds = allGameAds.get(randomGameAd.nextInt(maxInt));
        return gameAds;

    }
    public List<GameAds> findAvailableGameAdsSortedByPriceAsc() {

        List<GameAds> availableAds = new ArrayList<>();
        availableAds = gameAdsRepository.findByIsAvailable(true);

       // sortera på pris
        Collections.sort(availableAds, Comparator.comparing(GameAds::getPrice));

        return availableAds;


        //return gameAdsRepository.findByIsAvailable(true);
        //This works in returning only available ads
    }





}