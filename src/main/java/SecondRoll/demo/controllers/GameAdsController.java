package SecondRoll.demo.controllers;


import SecondRoll.demo.exception.EntityNotFoundException;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.payload.response.GameAdSearchResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.services.GameAdsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    // GET ALL game ads belonging to a user
    @GetMapping("/all")
    public ResponseEntity<List<GameAdResponse>> getAllGameAds() {
        List<GameAdResponse> orders = gameAdsService.getAllGameAds();
        return ResponseEntity.ok(orders);
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

    // GET gameAd by id
    @GetMapping(value = "/{id}")
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
    public ResponseEntity<List<GameAdResponse>> getUserGameAds(@PathVariable String userId) {
        List<GameAdResponse> gameAds = gameAdsService.getUserOrders(userId);
        return ResponseEntity.ok(gameAds);
    }

    // "Roll the Dice" gameAd randomizer
    @GetMapping(value = "/rolldice")
    public ResponseEntity<GameAdResponse> getRandomGameAd() {
        GameAds gameAd = gameAdsService.getRandomGameAd();
        User user = gameAd.getUser();
        return ResponseEntity.ok().body(new GameAdResponse( gameAd.getId(),user.getId(),user.getUsername(),gameAd.getTitle(),
                gameAd.getDescription(), gameAd.getPrice(), gameAd.getShippingCost(), gameAd.getGameCreator(),
                gameAd.getGamePlayTime(), gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(),
                gameAd.getGameGenres(), gameAd.getCreated_at(), gameAd.getUpdated_at()));
    }


    // Sort available gameAds in ascending order by price
    // Request response has not yet been implemented
    @GetMapping("/sortbyprice/asc")
    public List<GameAds> findAvailableGameAdsSortedByPriceAsc() {
        return gameAdsService.findAvailableGameAdsSortedByPriceAsc();
    }

    // Sort available gameAds in descending order by price
    // Request response has not yet been implemented
    @GetMapping("/sortbyprice/desc")
    public List<GameAds> findAvailableGameAdsSortedByPriceDesc() {
        return gameAdsService.findAvailableGameAdsSortedByPriceDesc();
    }

    // Sort available gameAds in ascending order by date created
    // Request response has not yet been implemented
    @GetMapping("/sortbydate/asc")
    public List<GameAds> availableGameAdsSortedByDateAsc() {
        return gameAdsService.availableGameAdsSortedByDateAsc();
    }

    // Sort available gameAds in descending order by date created
    // Request response has not yet been implemented
    @GetMapping("/sortbydate/desc")
    public List<GameAds> availableGameAdsSortedByDateDesc() {
        return gameAdsService.availableGameAdsSortedByDateDesc();
    }


    // Finds gameAds where the passed title is checked and if present returns a list of all matching ads
    @GetMapping("/findbytitle/{title}")
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
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres(), gameAd.getPhotoURL()));
                }
                return ResponseEntity.ok().body(adsByTitleResponse);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }

    }


    // Finds gameAds where the passed genre is checked and if present returns a list of all matching ads
    @GetMapping("/findbygenre/{genre}")
    public ResponseEntity<?> getGameAdsByGenre(@PathVariable  String genre) {
        try {
            List<GameAds> adsByGenre = gameAdsRepository.findByGameGenres(genre);
            if (adsByGenre.isEmpty()) {
                return ResponseEntity.ok().body("No ads found for the genre: " + genre);
            } else {
                List<GameAdSearchResponse> adsByGenreResponse = new ArrayList<>();
                for (GameAds gameAd : adsByGenre) {
                    User user =gameAd.getUser();
                    adsByGenreResponse.add(new GameAdSearchResponse(gameAd.getId(),user.getUsername(),gameAd.getTitle(), gameAd.getDescription()
                            , gameAd.getPrice(),gameAd.getShippingCost(), gameAd.getGameCreator(), gameAd.getGamePlayTime()
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres(),gameAd.getPhotoURL()));
                }
                return ResponseEntity.ok().body(adsByGenreResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }

    }

    // Finds gameAds where the passed game creator is checked and if present returns a list of all matching ads
    @GetMapping("/findbycreator/{creator}")
    public ResponseEntity<?> getGameAdsByGameCreator(@PathVariable  String creator) {
        try {
            List<GameAds> adsByCreator = gameAdsRepository.findByGameCreator(creator);
            if (adsByCreator.isEmpty()) {
                return ResponseEntity.ok().body("No ads found with the game creator: " + creator);
            } else {
                List<GameAdSearchResponse> adsByCreatorResponse = new ArrayList<>();
                for (GameAds gameAd : adsByCreator) {
                    User user =gameAd.getUser();
                    adsByCreatorResponse.add(new GameAdSearchResponse(gameAd.getId(),user.getUsername(),gameAd.getTitle(), gameAd.getDescription()
                            , gameAd.getPrice(),gameAd.getShippingCost(), gameAd.getGameCreator(), gameAd.getGamePlayTime()
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres(), gameAd.getPhotoURL()));
                }
                return ResponseEntity.ok().body(adsByCreatorResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }
    }

    // Finds gameAds where the passed game play time is checked and if present returns a list of all matching ads
    @GetMapping("/findbygametime/{gameTime}")
    public ResponseEntity<?> getGameAdsByGamePlayTime(@PathVariable  String gameTime) {
        try {
            List<GameAds> adsByGameTime = gameAdsRepository.findByGamePlayTime(gameTime);
            if (adsByGameTime.isEmpty()) {
                return ResponseEntity.ok().body("No ads found with the gameTime: " + gameTime);
            } else {
                List<GameAdSearchResponse> adsByGameTimeResponse = new ArrayList<>();
                for (GameAds gameAd : adsByGameTime) {
                    User user =gameAd.getUser();
                    adsByGameTimeResponse.add(new GameAdSearchResponse(gameAd.getId(),user.getUsername(),gameAd.getTitle(), gameAd.getDescription()
                            , gameAd.getPrice(),gameAd.getShippingCost(), gameAd.getGameCreator(), gameAd.getGamePlayTime()
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres(),gameAd.getPhotoURL()));
                }
                return ResponseEntity.ok().body(adsByGameTimeResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }
    }

    // Finds gameAds where the passed recommended age is checked and if present returns a list of all matching ads
    @GetMapping("/findbyage/{recommendedAge}")
    public ResponseEntity<?> getGameAdsByGameRecommendedAge(@PathVariable  String recommendedAge) {
        try {
            List<GameAds> adsByGameRecommendedAge = gameAdsRepository.findByGameRecommendedAge(recommendedAge);
            if (adsByGameRecommendedAge.isEmpty()) {
                return ResponseEntity.ok().body("No ads found with the recommendedAge: " + recommendedAge);
            } else {
                List<GameAdSearchResponse> adsByAgeResponse = new ArrayList<>();
                for (GameAds gameAd : adsByGameRecommendedAge) {
                    User user =gameAd.getUser();
                    adsByAgeResponse.add(new GameAdSearchResponse(gameAd.getId(),user.getUsername(),gameAd.getTitle(), gameAd.getDescription()
                            , gameAd.getPrice(),gameAd.getShippingCost(), gameAd.getGameCreator(), gameAd.getGamePlayTime()
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres(), gameAd.getPhotoURL()));
                }
                return ResponseEntity.ok().body(adsByAgeResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }
    }

    // Finds gameAds where the passed number of players is checked and if present returns a list of all matching ads
    @GetMapping("/findbyplayers/{players}")
    public ResponseEntity<?> getGameAdsByGamePlayers(@PathVariable  String players) {
        try {
            List<GameAds> adsByPlayers = gameAdsRepository.findByGamePlayers(players);
            if (adsByPlayers.isEmpty()) {
                return ResponseEntity.ok().body("No ads found with the number of players: " + players);
            } else {
                List<GameAdSearchResponse> adsByPlayersResponse = new ArrayList<>();
                for (GameAds gameAd : adsByPlayers) {
                    User user =gameAd.getUser();
                    adsByPlayersResponse.add(new GameAdSearchResponse(gameAd.getId(),user.getUsername(),gameAd.getTitle(), gameAd.getDescription()
                            , gameAd.getPrice(),gameAd.getShippingCost(), gameAd.getGameCreator(), gameAd.getGamePlayTime()
                            , gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(), gameAd.getGameGenres(), gameAd.getPhotoURL()));
                }
                return ResponseEntity.ok().body(adsByPlayersResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }
    }
}