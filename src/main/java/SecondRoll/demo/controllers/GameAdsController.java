package SecondRoll.demo.controllers;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.payload.CreateGameDTO;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.services.GameAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(value="/api/gameAds")
public class GameAdsController {
    @Autowired

    GameAdsService gameAdsService;


    // POST
    @PostMapping()
    public ResponseEntity<GameAds> createGameAd(@RequestBody CreateGameDTO createGameDTO) {
        GameAds gameAd = gameAdsService.createGameAd(createGameDTO);
        return new ResponseEntity<>(gameAd, HttpStatus.CREATED);
    }

    // GET
    @GetMapping("/all")
    public List<GameAds> getAllGameAds() {
        return gameAdsService.getAllGameAds();
    }

    // PUT
    @PutMapping()
    public GameAds updateGameAd(@RequestBody GameAds gameAds) {
        return gameAdsService.updateGameAd(gameAds);
    }

    // GET by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<GameAds> getGameAdById(@PathVariable String id) {
        Optional<GameAds> gameAds = gameAdsService.getGameAdById(id);

        return gameAds.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteGameAd(@PathVariable String id) {
        return gameAdsService.deleteGameAd(id);
    }


    @GetMapping(value = "/search")
    public List<GameAds> findGameAdsByGameDetails(@RequestParam List<EGameCategory> gameDetails) {
        return gameAdsService.findGameAdsByGameDetails(gameDetails);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<GameAds> getGameAdsByUserId(@PathVariable String userId) {
        Optional<GameAds> gameAds = gameAdsService.getGameByUserId(userId);
        return gameAds.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Roll the Dice game ad randomizer
    @GetMapping(value = "/randomizer")
    public void List<GameAds> randomizeGameAds(GameAds gameAds) {
        List<Integer> GameAds = Arrays.asList();
        Random random = new Random();
        int randomGameAd = GameAds.get(random.nextInt(GameAds.size()));

       System.out.println("Random game ad " + randomGameAd);
    }

}