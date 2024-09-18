package SecondRoll.demo.controllers;

import SecondRoll.demo.payload.GameAdDTOConverter;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.services.GameAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/gameads/")
public class SortController extends GameAdDTOConverter {
    @Autowired
    GameAdsService gameAdsService;
    @Autowired
    GameAdsRepository gameAdsRepository;

    // Sorting method for all games and price and date ascending/descending.
    /*
    @GetMapping("/{sortBy}")
    public List<GameAdResponse> sortGameAds(@PathVariable String sortBy) {
        List<GameAds> availableAds = gameAdsRepository.findByIsAvailable(true);
        if(sortBy.equals("all")) {
            List<GameAdResponse> allGames = gameAdsService.getAllGameAds();
            return allGames;
        } else if(sortBy.equals("priceasc")) {
            Collections.sort(availableAds, Comparator.comparing(GameAds::getPrice));
        } else if(sortBy.equals("pricedesc")) {
            Collections.sort(availableAds, Comparator.comparing(GameAds::getPrice).reversed());
        } else if(sortBy.equals("dateasc")) {
            Collections.sort(availableAds, Comparator.comparing(GameAds::getCreated_at));
        } else if(sortBy.equals("datedesc")) {
            Collections.sort(availableAds, Comparator.comparing(GameAds::getCreated_at).reversed());
        } return availableAds.stream().map(this::convertToDTO).collect(Collectors.toList());
    } */
}