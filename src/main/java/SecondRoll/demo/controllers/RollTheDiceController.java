package SecondRoll.demo.controllers;


import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.payload.GameAdDTOConverter;
import SecondRoll.demo.payload.response.GameAdResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.services.GameAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;


// @CrossOrigin(origins = "5173", maxAge = 3600)
@RestController
@RequestMapping(value="/api/gameads")
public class RollTheDiceController extends GameAdDTOConverter {

    @Autowired
    GameAdsService gameAdsService;
    @Autowired
    GameAdsRepository gameAdsRepository;

    @GetMapping(value = "/rolldice")
    public GameAdResponse getRandomGameAd() {
        Random randomGameAd = new Random();
        List<GameAds> allGameAds = gameAdsRepository.findAll();
        int maxInt = allGameAds.size();
        GameAds gameAd = allGameAds.get(randomGameAd.nextInt(maxInt));

        return convertToDTO(gameAd);
    }
}