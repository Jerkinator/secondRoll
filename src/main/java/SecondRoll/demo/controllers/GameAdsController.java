package SecondRoll.demo.controllers;


import SecondRoll.demo.exception.EntityNotFoundException;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.services.GameAdsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "5173", maxAge = 3600)
@RestController
@RequestMapping(value="/api/gameads")
public class GameAdsController {

    @Autowired
    GameAdsService gameAdsService;
    @Autowired
    GameAdsRepository gameAdsRepository;

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
        try {
            GameAds updatedGameAd = gameAdsService.updateGameAd(gameId, gameDetails);
            User user = updatedGameAd.getUser();
            return ResponseEntity.ok().body(new GameAdResponse (updatedGameAd.getId(),user.getId(),user.getUsername(), updatedGameAd.getTitle(),
                    updatedGameAd.getDescription(), updatedGameAd.getPrice(), updatedGameAd.getShippingCost(),
                    updatedGameAd.getGameCreator(), updatedGameAd.getGamePlayTime(),
                    updatedGameAd.getGameRecommendedAge(), updatedGameAd.getGamePlayers(),
                    updatedGameAd.getGameGenres(),
                    updatedGameAd.getCreated_at(),
                    updatedGameAd.getUpdated_at()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Move to a GET class?
    // GET gameAd by id
   @GetMapping(value = "/{id}")
    public GameAds getGameAdById(@PathVariable String id) {
            GameAds gameAd = gameAdsService.getGameAdById(id);
            convertToDTO(gameAd);

            return gameAd;
    }


   /*  @GetMapping("/{id}") // TEST Get by ID, can be scrapped.
    public GameAds getGameAdById(@PathVariable String id) {
        GameAds gameAd = gameAdsService.getGameAdById(id);
        return gameAd;
    } */

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

    // Move to a GET-class?
    // GET ALL game ads belonging to a user
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<GameAds>> getUserGameAds(@PathVariable String userId) {
        List<GameAds> gameAds = gameAdsService.getUserGames(userId);
        return ResponseEntity.ok(gameAds);
    }

    public GameAdResponse convertToDTO(GameAds gameAd) {
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
}