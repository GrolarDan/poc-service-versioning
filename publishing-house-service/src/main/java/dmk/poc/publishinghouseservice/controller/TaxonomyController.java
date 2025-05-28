package dmk.poc.publishinghouseservice.controller;

import dmk.poc.publishinghouseservice.dto.GenreDto;
import dmk.poc.publishinghouseservice.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static dmk.poc.publishinghouseservice.controller.TaxonomyController.*;

@RestController
@RequestMapping(value = {VERSION_V1_PATH + TAXONOMY_PATH, VERSION_V2_PATH + TAXONOMY_PATH}, produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "Taxonomy", description = "Taxonomy API")
public class TaxonomyController {
    static final String VERSION_V1_PATH = "v1";
    static final String VERSION_V2_PATH = "v2";
    static final String TAXONOMY_PATH = "/taxonomy";

    private final GenreService genreService;

    @GetMapping(value = "/genres")
    @Operation(summary = "Get all genres")
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping(value = "/genres/fiction")
    @Operation(summary = "Get only fiction genres")
    public List<GenreDto> getFictionGenres() {
        return genreService.getFictionGenres();
    }
}
