package SecondRoll.demo.controllers;

import SecondRoll.demo.payload.response.GameAdSearchResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/gameads/search")
public class SearchController {

    @Autowired
    GameAdsRepository gameAdsRepository;

    @GetMapping("/{search}")
    public ResponseEntity<?> searchGameAdsByTitle(@PathVariable String search) {
        try {
            List<GameAdSearchResponse> searchResults = gameAdsRepository.searchGameAdsByTitleContainingIgnoreCase(search);

            if (searchResults.isEmpty()) {
                return ResponseEntity.ok().body("No ads found for the title: " + search);
            } else {
                return ResponseEntity.ok().body(searchResults);
                }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("An error occurred: " + e.getMessage()));
        }
    }
}
