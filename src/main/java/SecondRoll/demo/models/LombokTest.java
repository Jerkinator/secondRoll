package SecondRoll.demo.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString

public class LombokTest {
    @NonNull
    private final String name;
    @NonNull
    private final String password;
    @NonNull
    private final int id;

    private final String description;

// mandatory parameters require a builder class with required fields as constructor parameters
    public static LombokTestBuilder builder (final String name, final String password, final int id) {
        return new LombokTestBuilder()
                .name(name)
                .password(password)
                .id(id);
    }

    // output in main would look like this
/*    LombokTest = LombokGameAd.builder("Mia", "tjoho", 1)
            .description("bla bla bla")
            .build();
        System.out.println(lombokGameAd);*/

}
