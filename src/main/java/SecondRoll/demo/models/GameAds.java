package SecondRoll.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameAds {
    @Id
    private String id;
    @NotBlank
    @DBRef
    private User user;
    @NotBlank
    @Size(min = 1, max = 30)
    private String title;
    @NotBlank
    @Size(min = 1, max = 999)
    private String description;
    @NotNull
    @Range(min = 1, max = 50000)
    private int price;
    @NotBlank
    @Size(min = 1, max = 200)
    private String gameCreator;
    @NotBlank
    @Size(min = 1, max = 200)
    private String gamePlayTime;
    @NotBlank
    @Size(min = 1, max = 200)
    private String gameRecommendedAge;
    @NotBlank
    @Size(min = 1, max = 200)
    private String gamePlayers;
    @NotBlank
    @Size(min = 1, max = 200)
    public List<String> gameGenres = new ArrayList<>();
    @NotBlank
    private String photoURL;
    @NotNull
    @Range(min = 1, max = 500)
    private int shippingCost;
    @CreatedDate
    private LocalDate created_at;
    @CreatedDate
    private LocalDate updated_at;


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getGameCreator() {
        return gameCreator;
    }

    public String getGamePlayTime() {
        return gamePlayTime;
    }

    public String getGameRecommendedAge() {
        return gameRecommendedAge;
    }

    public String getGamePlayers() {
        return gamePlayers;
    }

    public List<String> getGameGenres() {
        return gameGenres;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    @Override
    public String toString() {
        return "GameAds{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", gameCreator='" + gameCreator + '\'' +
                ", gamePlayTime='" + gamePlayTime + '\'' +
                ", gameRecommendedAge='" + gameRecommendedAge + '\'' +
                ", gamePlayers='" + gamePlayers + '\'' +
                ", gameGenres=" + gameGenres +
                ", photoURL='" + photoURL + '\'' +
                ", shippingCost=" + shippingCost +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    private GameAds(GameAdBuilder gameAdBuilder) {
        this.id = gameAdBuilder.id;
        this.user = gameAdBuilder.user;
        this.title = gameAdBuilder.title;
        this.description = gameAdBuilder.description;
        this.price = gameAdBuilder.price;
        this.gameCreator = gameAdBuilder.gameCreator;
        this.gamePlayTime = gameAdBuilder.gamePlayTime;
        this.gameRecommendedAge = gameAdBuilder.gameRecommendedAge;
        this.gamePlayers = gameAdBuilder.gamePlayers;
        this.gameGenres = gameAdBuilder.gameGenres;
        this.photoURL = gameAdBuilder.photoURL;
        this.shippingCost = gameAdBuilder.shippingCost;
        this.created_at = gameAdBuilder.created_at;
        this.updated_at = gameAdBuilder.updated_at;

    }


    public static class GameAdBuilder {
        @Id
        private String id;
        @NotBlank
        @DBRef
        private User user;
        @NotBlank
        @Size(min = 1, max = 30)
        private String title;
        @NotBlank
        @Size(min = 1, max = 999)
        private String description;
        @NotNull
        @Range(min = 1, max = 50000)
        private int price;
        @NotBlank
        @Size(min = 1, max = 200)
        private String gameCreator;
        @NotBlank
        @Size(min = 1, max = 200)
        private String gamePlayTime;
        @NotBlank
        @Size(min = 1, max = 200)
        private String gameRecommendedAge;
        @NotBlank
        @Size(min = 1, max = 200)
        private String gamePlayers;
        @NotBlank
        @Size(min = 1, max = 200)
        public List<String> gameGenres = new ArrayList<>();
        @NotBlank
        private String photoURL;
        @NotNull
        @Range(min = 1, max = 500)
        private int shippingCost;
        @CreatedDate
        private LocalDate created_at;
        @CreatedDate
        private LocalDate updated_at;

        public GameAdBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public GameAdBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public GameAdBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public GameAdBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public GameAdBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public GameAdBuilder withGameCreator(String gameCreator) {
            this.gameCreator = gameCreator;
            return this;
        }

        public GameAdBuilder withGamePlayTime(String gamePlayTime) {
            this.gamePlayTime = gamePlayTime;
            return this;
        }

        public GameAdBuilder withGameRecommendedAge(String gameRecommendedAge) {
            this.gameRecommendedAge = gameRecommendedAge;
            return this;
        }

        public GameAdBuilder withGamePlayers(String gamePlayers) {
            this.gamePlayers = gamePlayers;
            return this;
        }

        public GameAdBuilder withGameGenres(List<String> gameGenres) {
            this.gameGenres = gameGenres;
            return this;
        }

        public GameAdBuilder withPhotoURL(String photoURL) {
            this.photoURL = photoURL;
            return this;
        }

        public GameAdBuilder withShippingCost(int shippingCost) {
            this.shippingCost = shippingCost;
            return this;
        }

        public GameAdBuilder withCreatedAt(LocalDate created_at) {
            this.created_at = created_at;
            return this;
        }

        public GameAdBuilder withUpdatedAt(LocalDate updated_at) {
            this.updated_at = updated_at;
            return this;
        }

        public GameAds build() {
            return new GameAds(this);
        }
    }

}


