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
@RequestMapping(value="/api/gameAds")
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
  /*  @GetMapping(value = "/{id}")
    public ResponseEntity<?> getGameAdById(@PathVariable String id) {
        try {
            Optional<GameAds> gameAd = gameAdsService.getGameAdById(id);
            User user = gameAd.get().getUser();

            return ResponseEntity.ok().body(new GameAdResponse(gameAd.get().getId(),user.getId(),user.getUsername(), gameAd.get().getTitle(),
                    gameAd.get().getDescription(), gameAd.get().getPrice(), gameAd.get().getShippingCost(),
                    gameAd.get().getGameCreator(), gameAd.get().getGamePlayTime(), gameAd.get().getGameRecommendedAge(),
                    gameAd.get().getGamePlayers(), gameAd.get().getGameGenres(),
                    gameAd.get().getCreated_at(), gameAd.get().getUpdated_at()));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    } */

    @GetMapping("/{id}")
    public GameAds getGameAdById(@PathVariable String id) {
        GameAds gameAd = gameAdsService.getGameAdById(id);
        return gameAd;
    }

    @GetMapping("/all")
    List<GameAdResponse> findAllGameAds() {
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
    public ResponseEntity<List<GameAdResponse>> getUserGameAds(@PathVariable String userId) {
        List<GameAdResponse> gameAds = gameAdsService.getUserOrders(userId);
        return ResponseEntity.ok(gameAds);
    }

    // Finds gameAds where the passed title is checked and if present returns a list of all matching ads
    /* @GetMapping("/findbytitle/{title}")
    public ResponseEntity<?> getGameAdsByTitle(@PathVariable  String title) {
        try {
            List<GameAds> adsByTitle = gameAdsRepository.findByTitleIgnoreCase(title);

            if (adsByTitle.isEmpty()) {
                return ResponseEntity.ok().body("No ads found for the title: " + title);
            } else {
                List<GameAdSearchResponse> adsByTitleResponse = new ArrayList<>();
                for (GameAds gameAd : adsByTitle) {
                    User user =gameAd.getUser();
                    adsByTitleResponse.add(new GameAdSearchResponse(gameAd.getId(),user.getUsername(),gameAd.getTitle(), gameAd.getDescription()
                            , gameAd.getPrice(),gameAd.getShippingCost(),gameAd.getGameCreator(), gameAd.getGamePlayTime()
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres()));
                }
                return ResponseEntity.ok().body(adsByTitleResponse);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }
    } */
}