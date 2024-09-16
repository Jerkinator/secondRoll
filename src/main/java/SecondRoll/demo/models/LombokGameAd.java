package SecondRoll.demo.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString

public class LombokGameAd {
    @NonNull
    private final String name;
    @NonNull
    private final String password;
    @NonNull
    private final int id;

    private final String description;

    public static LombokGameAdBuilder builder (final String name, final String password, final int id) {
        return new LombokGameAdBuilder()
                .name(name)
                .password(password)
                .id(id);
    }

    LombokGameAd lombokGameAd = LombokGameAd.builder(name, password, id)
            .name("Mia")
            .password("tjoho")
            .id(1)
            .description("bla bla bla")
            .build();
}
