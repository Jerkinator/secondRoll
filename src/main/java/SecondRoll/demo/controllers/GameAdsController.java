package SecondRoll.demo.controllers;


import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.services.GameAdsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "5173", maxAge = 3600)
@RestController
@RequestMapping(value="/api/gameads")
public class GameAdsController {
    private final GameAdsService gameAdsService;
    public GameAdsController(GameAdsService gameAdsService) {
        this.gameAdsService = gameAdsService;
    }

    // POST gameAd
    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GameAdResponse> createGameAd(@Valid @RequestBody CreateGameDTO createGameDTO) {
        GameAds gameAd = gameAdsService.createGameAd(createGameDTO);
        User user = gameAd.getUser();
        return ResponseEntity.ok().body(new GameAdResponse(gameAd.getId(),user.getId(),user.getUsername(), gameAd.getTitle(),
                gameAd.getDescription(), gameAd.getPrice(), gameAd.getShippingCost(), gameAd.getGameCreator(),
                gameAd.getGamePlayTime(), gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(),gameAd.getGameGenres()
                /*gameAd.getPhotoURL()*/, gameAd.getCreated_at(), gameAd.getUpdated_at()));
    }

    // PUT update gameAd
    @PutMapping("/{gameId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> updateGameAd(@PathVariable String gameId, @RequestBody GameAds gameDetails) {
            GameAdResponse updatedGameAd = gameAdsService.updateGameAd(gameId, gameDetails);
            return ResponseEntity.ok(updatedGameAd);
    }

    // GET a gameAd by id.
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getGameAdById(@PathVariable String id) {
        GameAdResponse gameAd = gameAdsService.getGameAdById(id);
        return ResponseEntity.ok(gameAd);
    }

    // GET all gameAds
    @GetMapping("/all")
    List<GameAds> findAllGameAds() {
        return gameAdsService.getAllGameAds();
    }

    // DELETE gameAd by id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String deleteGameAd(@PathVariable String id) {
        return gameAdsService.deleteGameAd(id);
    }

    // GET ALL game ads belonging to a user
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<GameAds>> getUserGameAds(@PathVariable String userId) {
        List<GameAds> gameAds = gameAdsService.getUserGames(userId);
        return ResponseEntity.ok(gameAds);
    }
}