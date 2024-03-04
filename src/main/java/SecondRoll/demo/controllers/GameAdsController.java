package SecondRoll.demo.controllers;

import SecondRoll.demo.exception.EntityNotFoundException;
import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
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
    public ResponseEntity<GameAds> createGameAd(@RequestBody CreateGameDTO createGameDTO) {
        GameAds gameAd = gameAdsService.createGameAd(createGameDTO);
        return new ResponseEntity<>(gameAd, HttpStatus.CREATED);
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
            return ResponseEntity.ok(updatedGameAd);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // GET a gameAd by ID.
    @GetMapping(value = "/{id}")
    public ResponseEntity<GameAds> getGameAdById(@PathVariable String id) {
        Optional<GameAds> gameAds = gameAdsService.getGameAdById(id);

        return gameAds.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete by ID.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteGameAd(@PathVariable String id) {
        return gameAdsService.deleteGameAd(id);
    }

    // FILTER by enum.
    @GetMapping(value = "/search")
    public List<GameAds> findGameAdsByGameDetails(@RequestParam List<EGameCategory> gameDetails) {
        return gameAdsService.findGameAdsByGameDetails(gameDetails);
    }

    // UPDATED GET all game ads belonging to a user.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GameAdResponse>> getUserOrders(@PathVariable String userId) {
        List<GameAdResponse> gameAds = gameAdsService.getUserOrders(userId);
        return ResponseEntity.ok(gameAds);
    }

    // "Roll the Dice" game ad randomizer
    @GetMapping(value = "/rolldice")
    public GameAds getRandomGameAd() {
        return gameAdsService.getRandomGameAd();
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