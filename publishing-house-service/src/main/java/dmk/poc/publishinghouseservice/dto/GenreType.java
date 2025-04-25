package dmk.poc.publishinghouseservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenreType {
    CLASSIC("Classic"),
    COMIC("Comic/Graphic Novel"),
    CRIME("Crime/Detective"),
    FABLE("Fable"),
    FAIRY_TALE("Fairy tale"),
    FANFICTION("Fanfiction"),
    FANTASY("Fantasy"),
    FICTION_NARRATIVE("Fiction narrative"),
    FICTION_IN_VERSE("Fiction in verse"),
    FOLKLORE("Folklore"),
    HISTORICAL_FICTION("Historical fiction"),
    HORROR("Horror"),
    HUMOR("Humor"),
    LEGEND("Legend"),
    METAFICTION("Metafiction"),
    MYSTERY("Mystery"),
    MYTHOLOGY("Mythology"),
    MYTHOPOEIA("Mythopoeia"),
    REALISTIC_FICTION("Realistic fiction"),
    SCIENCE_FICTION("Science fiction"),
    SHORT_STORY("Short story"),
    SUSPENSE("Suspense/Thriller"),
    TALL_TALE("Tall tale"),
    WESTERN("Western"),
    BIOGRAPHY("Biography/Autobiography"),
    ESSAY("Essay"),
    NARRATIVE_NONFICTION("Narrative nonfiction"),
    SPEECH("Speech"),
    TEXTBOOK("Textbook"),
    REFERENCE_BOOK("Reference book");

    private final String value;
}
