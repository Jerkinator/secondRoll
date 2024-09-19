package SecondRoll.demo.payload.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter
@Setter
@Builder
public class GameAdResponseLombok {

    // the response class is the representation of the data that we want to show in the response

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
    }