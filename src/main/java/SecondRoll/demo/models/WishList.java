package SecondRoll.demo.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class WishList {

    @Id
    private String id;

    @DBRef
    private List<GameAds> gameAds;

    @NotBlank
    @DBRef
    private User user;

    // Getters and setters.
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GameAds> getGameAds() {
        return gameAds;
    }

    public void setGameAds(List<GameAds> gameAds) {
        this.gameAds = gameAds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}