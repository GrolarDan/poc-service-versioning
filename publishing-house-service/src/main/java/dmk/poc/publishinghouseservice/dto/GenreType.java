package dmk.poc.publishinghouseservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum GenreType {
    CLASSIC(1, "Classic"),
    COMIC(2, "Comic/Graphic Novel"),
    CRIME(3, "Crime/Detective"),
    FABLE(4, "Fable"),
    FAIRY_TALE(5, "Fairy tale"),
    FANFICTION(6, "Fanfiction"),
    FANTASY(7, "Fantasy"),
    FICTION_NARRATIVE(8, "Fiction narrative"),
    FICTION_IN_VERSE(9, "Fiction in verse"),
    FOLKLORE(10, "Folklore"),
    HISTORICAL_FICTION(11, "Historical fiction"),
    HORROR(12, "Horror"),
    HUMOR(13, "Humor"),
    LEGEND(14, "Legend"),
    METAFICTION(15, "Metafiction"),
    MYSTERY(16, "Mystery"),
    MYTHOLOGY(17, "Mythology"),
    MYTHOPOEIA(18, "Mythopoeia"),
    REALISTIC_FICTION(19, "Realistic fiction"),
    SCIENCE_FICTION(20, "Science fiction"),
    SHORT_STORY(21, "Short story"),
    SUSPENSE(22, "Suspense/Thriller"),
    TALL_TALE(23, "Tall tale"),
    WESTERN(24, "Western"),
    BIOGRAPHY(25, "Biography/Autobiography"),
    ESSAY(26, "Essay"),
    NARRATIVE_NONFICTION(27, "Narrative nonfiction"),
    SPEECH(28, "Speech"),
    TEXTBOOK(29, "Textbook"),
    REFERENCE_BOOK(30, "Reference book");

    private final Integer id;
    private final String value;

    public static Optional<GenreType> fromId(Integer id) {
        for (GenreType genre : GenreType.values()) {
            if (genre.getId().equals(id)) {
                return Optional.of(genre);
            }
        }
        return Optional.empty();
    }

    public static Optional<GenreType> fromValue(String value) {
        for (GenreType genre : GenreType.values()) {
            if (genre.getValue().equalsIgnoreCase(value)) {
                return Optional.of(genre);
            }
        }
        return Optional.empty();
    }
}
