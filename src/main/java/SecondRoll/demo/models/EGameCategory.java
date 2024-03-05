package SecondRoll.demo.models;

public enum EGameCategory {

    //Creator
    LINDGREN_AB, FANTUS_CO, SLIMS_AB, OTHER,
    // Playtime
    SHORT("5-15 min"), MEDIUM("15-30 min"), LONG("30+"),
    // Recommended age
    TODDLER("0-6"), YOUNG("6-12"), TEEN("13-17"), OLD("18+"),
    // Players
    FEW("1-2"), MORE("3-6"), ALOT("7+"),
    // Genres
    ADVENTURE, FAMILY, ROLE_PLAY, FANTASY, ADULT, CARD_GAME, SCIENCE_FICTION, CHILDRENS_GAME;

    EGameCategory() {
    }
    public String getMessage() {
        return message;
    }
    private String message;
    EGameCategory (String message) {
        this.message = message;
    }
}