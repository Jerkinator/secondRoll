package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.repository.GameAdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SecondRoll.demo.strategies.GameAdsFilterStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilterService {
    @Autowired
    private GameAdsRepository gameAdsRepository;

    public List<GameAds> filterGameAds(Map<String, String> filters) {
        List<GameAds> gameAd = gameAdsRepository.findAll();

        List<GameAdsFilterStrategy> strategies = new ArrayList<>();

        if (filters.containsKey("title")) {
            strategies.add(filterByTitle(filters.get("title")));
        }
        if (filters.containsKey("gameCreator")) {
            strategies.add(filterByGameCreator(filters.get("gameCreator")));
        }
        if (filters.containsKey("gameGenres")){
            strategies.add(filterByGameGenres(filters.get("gameGenres")));
        }
        return gameAd.stream()
                .filter(gameAds -> strategies.stream().allMatch(strategy -> strategy.filter(gameAds)))
                .collect(Collectors.toList());
    }

    private GameAdsFilterStrategy filterByTitle(String title) {
        return gameAd -> gameAd.getTitle().toLowerCase().contains(title.toLowerCase());
    }

    private GameAdsFilterStrategy filterByGameCreator(String gameCreator) {
        return gameAd -> gameAd.getGameCreator().toLowerCase().contains(gameCreator.toLowerCase());
    }

    private GameAdsFilterStrategy filterByGameGenres(String gameGenres) {
        return gameAd -> gameAd.getGameGenres().stream()
                .anyMatch(genre -> genre.equalsIgnoreCase(gameGenres));
    }

}
