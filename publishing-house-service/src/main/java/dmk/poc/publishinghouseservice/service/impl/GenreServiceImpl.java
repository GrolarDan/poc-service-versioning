package dmk.poc.publishinghouseservice.service.impl;

import dmk.poc.publishinghouseservice.dto.GenreDto;
import dmk.poc.publishinghouseservice.dto.GenreType;
import dmk.poc.publishinghouseservice.service.GenreMapper;
import dmk.poc.publishinghouseservice.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAllGenres() {
        return Arrays.stream(GenreType.values()).map(genreMapper::mapToDto).toList();
    }

    @Override
    public List<GenreDto> getFictionGenres() {
        return Stream.of(
                GenreType.FANFICTION,
                GenreType.FANTASY,
                GenreType.FICTION_NARRATIVE,
                GenreType.FICTION_IN_VERSE,
                GenreType.METAFICTION,
                GenreType.MYTHOLOGY,
                GenreType.REALISTIC_FICTION,
                GenreType.SCIENCE_FICTION
        ).map(genreMapper::mapToDto).toList();
    }
}
