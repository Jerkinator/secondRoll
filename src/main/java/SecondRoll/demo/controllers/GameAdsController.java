package SecondRoll.demo.controllers;

import SecondRoll.demo.models.EGameCategory;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.services.GameAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/gameAds")
public class GameAdsController {
    @Autowired

    GameAdsService gameAdsService;


    // POST
    @PostMapping()
    public GameAds createGameAd(@RequestBody GameAds gameAds) {
        return gameAdsService.createGameAd(gameAds);
    }

    // GET
    @GetMapping()
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


    @GetMapping("/search")
    public List<GameAds> findGameAdsByGameDetails(@RequestParam List<EGameCategory> gamedetails) {
        return gameAdsService.findGameAdsByGameDetails(gamedetails);
    }

}


