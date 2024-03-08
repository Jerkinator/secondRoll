package SecondRoll.demo.controllers;

import SecondRoll.demo.exception.EntityNotFoundException;
import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.services.GameAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/gameAds")
public class GameAdsController {

    @Autowired
    GameAdsService gameAdsService;

    // POST.
    @PostMapping()
    public ResponseEntity<GameAdResponse> createGameAd(@RequestBody CreateGameDTO createGameDTO) {
        GameAds gameAd = gameAdsService.createGameAd(createGameDTO);
        User user = gameAd.getUser();
        return ResponseEntity.ok().body(new GameAdResponse(user.getUsername(), gameAd.getTitle(),
                gameAd.getDescription(), gameAd.getPrice(), gameAd.getShippingCost(),
                gameAd.getGameDetails(), gameAd.getCreated_at(), gameAd.getUpdated_at()));
    }

    // GET ALL gameAds.
    @GetMapping("/all")
    public ResponseEntity<List<GameAdResponse>> getAllGameAds() {
        List<GameAdResponse> orders = gameAdsService.getAllGameAds();
        return ResponseEntity.ok(orders);
    }

    // UPDATED PUT.
    @PutMapping("/{gameId}")
    public ResponseEntity<?> updateGameAd(@PathVariable String gameId, @RequestBody GameAds gameDetails) {
        try {
            GameAds updatedGameAd = gameAdsService.updateGameAd(gameId, gameDetails);
            User user = updatedGameAd.getUser();
            return ResponseEntity.ok().body(new GameAdResponse(user.getUsername(), updatedGameAd.getTitle(),
                    updatedGameAd.getDescription(), updatedGameAd.getPrice(), updatedGameAd.getShippingCost(),
                    updatedGameAd.getGameDetails(), updatedGameAd.getCreated_at(),
                    updatedGameAd.getUpdated_at()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // GET a gameAd by ID.
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getGameAdById(@PathVariable String id) {
        try {
            Optional<GameAds> gameAd = gameAdsService.getGameAdById(id);
            User user = gameAd.get().getUser();

            return ResponseEntity.ok().body(new GameAdResponse(user.getUsername(), gameAd.get().getTitle(),
                    gameAd.get().getDescription(), gameAd.get().getPrice(), gameAd.get().getShippingCost(),
                    gameAd.get().getGameDetails(), gameAd.get().getCreated_at(), gameAd.get().getUpdated_at()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete by ID.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteGameAd(@PathVariable String id) {
        return gameAdsService.deleteGameAd(id);
    }

    // Search by gameDetails
    @GetMapping(value = "/search")
    public List<GameAds> findGameAdsByGameDetails(@RequestParam List<EGameCategory> gameDetails) {
        return gameAdsService.findGameAdsByGameDetails(gameDetails);
    }

    // GET all game ads belonging to a user.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GameAdResponse>> getUserOrders(@PathVariable String userId) {
        List<GameAdResponse> gameAds = gameAdsService.getUserOrders(userId);
        return ResponseEntity.ok(gameAds);
    }

    // Search by latest added gameAd.

    // Search by Price, unfinished method.
    /* @GetMapping(value = "/price")
    public ResponseEntity<List<GameAds>> findGameAdsByPrice(@PathVariable List price) {

        List<GameAds> gamePrice = gameAdsService.findGameAdsByPrice(price);
        return ResponseEntity.ok(gamePrice);
<<<<<<< HEAD
    }

    //HELENA:
    // det här blir väl typ search/userId/userId va?
    // jag hade gjort om det här så att userId är en pathVariable
    // /search/{userId} och @PathVariable String userId
    // metoden ska nog funka om ni ändrar så ni ska inte behöva ändra i service
    @GetMapping(value = "/search/userId")
    public List<GameAds> findGameAdsByUserId(@RequestParam String userId) {
        return gameAdsService.findGameAdsByUserId(userId);
    }
=======
    } */


    // "Roll the Dice" game ad randomizer
    @GetMapping(value = "/rolldice")
    public ResponseEntity<GameAdResponse> getRandomGameAd() {
        GameAds gameAd = gameAdsService.getRandomGameAd();
        User user = gameAd.getUser();
        return ResponseEntity.ok().body(new GameAdResponse(user.getUsername(), gameAd.getTitle(),
                gameAd.getDescription(), gameAd.getPrice(), gameAd.getShippingCost(), gameAd.getGameDetails(),
                gameAd.getCreated_at(), gameAd.getUpdated_at()));
    }
}

 /* // OLD GET all game ads belonging to a user, stored for now, just in case.
    @GetMapping(value = "/search/userId")
    public List<GameAds> findGameAdsByUserId(@RequestParam String userId) {
        return gameAdsService.findGameAdsByUserId(userId);
    } */

 /*
    // OLD PUT Update a game ad, stored for now, just in case.
    @PutMapping()
    public GameAds updateGameAd(@RequestBody GameAds gameAds) {
        return gameAdsService.updateGameAd(gameAds);
    } */