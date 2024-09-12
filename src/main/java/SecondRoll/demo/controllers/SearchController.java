package SecondRoll.demo.controllers;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.GameAdDTOConverter;
import SecondRoll.demo.payload.response.GameAdSearchResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/gameads/search")
public class SearchController extends GameAdDTOConverter {

    @Autowired
    GameAdsRepository gameAdsRepository;

    @GetMapping("/{search}")
    public ResponseEntity<?> getGameAdsByTitle(@PathVariable String search) {
        try {
            List<GameAds> adsByTitle = gameAdsRepository.searchGameAdsByTitleContainingIgnoreCase(search);

            if (adsByTitle.isEmpty()) {
                return ResponseEntity.ok().body("No ads found for the title: " + search);
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
    }
}
