package dmk.poc.publishinghouseservice.service;

import dmk.poc.publishinghouseservice.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();
    List<GenreDto> getFictionGenres();
}
