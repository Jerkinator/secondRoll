package SecondRoll.demo.controller;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.services.GameAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/gameAds")
public class GameAdsController {
    @Autowired
    GameAdService gameAdService;

    // POST
    @PostMapping()
    public GameAds createGameAd(@RequestBody GameAds gameAds) {
        return gameAdService.createGameAd(gameAds);
    }

    // GET
    @GetMapping()
    public List<GameAds> getAllGameAds() {
        return gameAdService.getAllGameAds();
    }

    // PUT
    @PutMapping()
    public GameAds updateGameAd(@RequestBody GameAds gameAds) {
        return gameAdService.updateGameAd(gameAds);
    }

    // GET by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<GameAds> getGameAdById(@PathVariable String id) {
        Optional<GameAds> gameAds = gameAdService.getGameAdById(id);

        return gameAds.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteGameAd(@PathVariable String id) {
        return gameAdService.deleteGameAd(id);
    }

}

