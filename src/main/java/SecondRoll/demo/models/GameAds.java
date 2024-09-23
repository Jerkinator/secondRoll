package SecondRoll.demo.models;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String id;
    @NotBlank
    @DBRef
    private User user;
    @NotBlank
    @CreatedDate
    private LocalDate createdAt;
    @NotBlank
    @CreatedDate
    private LocalDate updatedAt;


    // mandatory parameters when creating a game ad
    @NotBlank
    @Size(min = 1, max = 30)
    private String title;
    @NotBlank
    @Size(min = 1, max = 999)
    private String description;
    @NotBlank
    @Range(min = 1, max = 50000)
    private int price;
    @NotBlank
    @Range(min = 1, max = 500)
    private int shippingCost;


    // non-mandatory parameters
    @Size(max = 200)
    private String gameCreator;
    @Size(max = 200)
    private String gamePlayTime;
    @Size(max = 200)
    private String gameRecommendedAge;
    @Size(max = 200)
    private String gamePlayers;
    @Size(max = 200)
    public List<String> gameGenres;
    private String photoURL;

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDate updatedAt) {this.updatedAt = updatedAt;}
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {this.price = price;}
    public int getShippingCost() {return shippingCost;}
    public void setShippingCost(int shippingCost) {this.shippingCost = shippingCost;}
    public String getGameCreator() {return gameCreator;}
    public void setGameCreator(String gameCreator) {this.gameCreator = gameCreator;}
    public String getGamePlayTime() {return gamePlayTime;}
    public void setGamePlayTime(String gamePlayTime) {this.gamePlayTime = gamePlayTime;}
    public String getGameRecommendedAge() {return gameRecommendedAge;}
    public void setGameRecommendedAge(String gameRecommendedAge) {this.gameRecommendedAge = gameRecommendedAge;}
    public String getGamePlayers() {return gamePlayers;}
    public void setGamePlayers(String gamePlayers) {this.gamePlayers = gamePlayers;}
    public List<String> getGameGenres() {return gameGenres;}
    public void setGameGenres(List<String> gameGenres) {this.gameGenres = gameGenres;}
    public String getPhotoURL() {return photoURL;}
    public void setPhotoURL(String photoURL) {this.photoURL = photoURL;}


    // "Traditional" builder pattern
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
                ", created_at=" + createdAt +
                ", updated_at=" + updatedAt +
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
        this.createdAt = gameAdBuilder.created_at;
        this.updatedAt = gameAdBuilder.updated_at;
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
        @NotBlank
        @Range(min = 1, max = 50000)
        private int price;
        @Size(max = 200)
        private String gameCreator;
        @Size(max = 200)
        private String gamePlayTime;
        @Size(max = 200)
        private String gameRecommendedAge;
        @Size(max = 200)
        private String gamePlayers;
        @Size(max = 200)
        public List<String> gameGenres = new ArrayList<>();
        private String photoURL;
        @NotBlank
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