package SecondRoll.demo.payload.response;

import SecondRoll.demo.models.User;
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
public class GameAdResponseLombok {
    // these parameters will generate their own references through the @id, @DBRef and @CreatedDate annotations
    @Id
    @NotBlank
    private long id;

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
    @NonNull
    @Size(min = 1, max = 30)
    private String title;

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
    @Size(min = 1, max = 200)
    private String gameCreator;

    @Size(min = 1, max = 200)
    private String gamePlayTime;

    @Size(min = 1, max = 200)
    private String gameRecommendedAge;

    @Size(min = 1, max = 200)
    private String gamePlayers;

    @Size(min = 1, max = 200)
    public List<String> gameGenres = new ArrayList<>();

    private String photoURL;


    // mandatory parameters require a builder class with required fields as constructor parameters
    public static GameAdResponseLombokBuilder builder (final String title, final String description, final int price, final int shippingCost) {
        return new GameAdResponseLombokBuilder()
                .title(title)
                .description(description)
                .price(price)
                .shippingCost(shippingCost);
    }
}
