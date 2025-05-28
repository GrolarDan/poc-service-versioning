package dmk.poc.publishinghouseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public record GenreDto(Integer id, @JsonProperty(access = READ_ONLY) String name) {
}
