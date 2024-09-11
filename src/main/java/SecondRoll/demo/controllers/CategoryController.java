package SecondRoll.demo.controllers;

import SecondRoll.demo.payload.response.GameAdSearchResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/gameAds")
public class CategoryController {

    @Autowired
    GameAdsRepository gameAdsRepository;

    List<GameAdSearchResponse> foundAds;

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getGameAdsByCategory(@PathVariable String category) {

        if (category.equals("family") || category.equals("adventure") || category.equals("scifi") ||
                category.equals("strategy") || category.equals("cardgame") || category.equals("kids") ||
                category.equals("roleplay") || category.equals("other") || category.equals("fantasy")) {
                foundAds = gameAdsRepository.findByGameGenres(category);

        } else if (category.equals("1-2") || category.equals("2-4") || category.equals("4-6") ||
                category.equals("6-8") || category.equals("10+")) {
                foundAds = gameAdsRepository.findByGamePlayers(category);

        } else if (category.equals("alga") || category.equals("fryxgames") || category.equals("egmont") ||
                category.equals("lello") || category.equals("other")) {
                foundAds = gameAdsRepository.findByGameCreator(category);

        } else if (category.equals("10+") || category.equals("30+") || category.equals("60+")) {
                foundAds = gameAdsRepository.findByGamePlayTime(category);

        } else if (category.equals("2+") || category.equals("6+") || category.equals("10+") ||
                category.equals("14+") || category.equals("18+")) {
            foundAds = gameAdsRepository.findByGameRecommendedAge(category);
        }
        if (foundAds.isEmpty()) {
            return ResponseEntity.ok().body("No ads found within the selected category");
        } else {
            return ResponseEntity.ok().body(foundAds);
        }
    }
}
