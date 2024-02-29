package SecondRoll.demo.services;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    // Find GameAds by user ID.
    public Optional<GameAds> getGameByUserId(String userId) {
        return gameAdsRepository.findById(userId);
    }


    // "Roll the Dice" game ad randomizer
    public GameAds getRandomGameAd() {
        Random randomGameAd = new Random();
        List<GameAds> allGameAds = gameAdsRepository.findAll();
        int maxInt = allGameAds.size();
        List<Integer> gameAdsInt = Arrays.asList(maxInt);
        int  = allGameAds.get(randomGameAd.nextInt(maxInt));

            System.out.println("This is your random game ad " + randomGameAd);



/*
        Skapa en random.
        Hämta alla GameAds till en lista
        Sätt en maxInt utefter listan du hämtades max size
        gör get på listan du hämtade med random.nextInt(maxInt)(då hämtar du EN GameAd ur listan)
        returnera den GameAdden */

    }
}