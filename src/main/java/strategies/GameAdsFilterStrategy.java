package strategies;

import SecondRoll.demo.models.GameAds;

@FunctionalInterface
public interface GameAdsFilterStrategy {
    boolean filter(GameAds gameAd);
}
