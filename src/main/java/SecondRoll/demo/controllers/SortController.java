package SecondRoll.demo.controllers;

import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.services.GameAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/gameads/")
public class SortController {
    @Autowired
    GameAdsService gameAdsService;
    @Autowired
    GameAdsRepository gameAdsRepository;

    // Sorting method for all games and price and date ascending/descending.
    @GetMapping("/{sortBy}")
    public List<GameAdResponse> sortGameAds(@PathVariable String sortBy) {
        if(sortBy.equals("all")) {
            List<GameAdResponse> allGames = gameAdsService.getAllGameAds();
            return allGames;
        } else if(sortBy.equals("priceasc")) {
            return gameAdsService.findGames("priceasc");
        } else if(sortBy.equals("pricedesc")) {
            return gameAdsService.findGames("pricedesc");
        } else if(sortBy.equals("dateasc")) {
            return gameAdsService.findGames("dateasc");
        } else if(sortBy.equals("datedesc")) {
            return gameAdsService.findGames("datedesc");
        } else {
            return null;
        }
    }
}