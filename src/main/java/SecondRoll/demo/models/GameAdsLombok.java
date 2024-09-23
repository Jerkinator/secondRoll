package SecondRoll.demo.models;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class GameAdsLombok {

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

    private boolean isAvailable;


    // mandatory parameters when creating a game ad
    @NonNull
    @Size(min = 1, max = 30)
    public String title;

    @NonNull
    @Size(min = 1, max = 999)
    private String description;

    @NonNull
    @Range(min = 1, max = 50000)
    private int price;

    @NotNull
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
    public List<String> gameGenres = new ArrayList<>();

    private String photoURL;


    // mandatory parameters require a builder method with required fields as constructor parameters
    public static GameAdsLombok.GameAdsLombokBuilder builder (final String title, final String description, final int price, final int shippingCost) {
        return new GameAdsLombok.GameAdsLombokBuilder()
                .title(title)
                .description(description)
                .price(price)
                .shippingCost(shippingCost);
    }
}