package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.filter.RankCategorySearchFilter;
import ru.nsu.fit.militarysystem.service.RankCategoryService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class RankCategoryController {
    private final RankCategoryService rankCategoryService;

    private static final String GET_RANK_CATEGORIES = "/rank-categories";

    private static final String GET_RANK_CATEGORIES_BY_SEARCH_FILTER = "/rank-categories/search";

    private static final String GET_RANK_CATEGORY = "/rank-categories/{id}";

    private static final String POST_RANK_CATEGORY = "/rank-categories";

    private static final String PUT_RANK_CATEGORY = "/rank-categories/{id}";

    private static final String DELETE_RANK_CATEGORY = "/rank-categories/{id}";

    public RankCategoryController(RankCategoryService rankCategoryService) {
        this.rankCategoryService = rankCategoryService;
    }

    @GetMapping(GET_RANK_CATEGORIES)
    public ResponseEntity<List<RankCategoryDto>> getAllRankCategories() {
        List<RankCategoryDto> rankCategoryDtos = rankCategoryService.getAllRankCategoriesAsDtos();
        return new ResponseEntity<>(rankCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_RANK_CATEGORIES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<RankCategoryDto>> getAllRankCategoriesByFilter(@RequestObjectParam RankCategorySearchFilter rankCategorySearchFilter)
            throws EntityNotFoundException {
        Page<RankCategoryDto> rankCategoryDtos = rankCategoryService.getAllRankCategoriesByFilterAsDtos(rankCategorySearchFilter);
        return new ResponseEntity<>(rankCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_RANK_CATEGORY)
    public ResponseEntity<RankCategoryDto> getRankCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        RankCategoryDto rankCategoryDto = rankCategoryService.getRankCategoryByIdAsDto(id);
        return new ResponseEntity<>(rankCategoryDto, HttpStatus.OK);
    }

    @PostMapping(POST_RANK_CATEGORY)
    public ResponseEntity<RankCategoryDto> createRankCategory(@RequestBody RankCategoryDto rankCategoryDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(rankCategoryService.createRankCategory(rankCategoryDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_RANK_CATEGORY)
    public ResponseEntity<RankCategoryDto> updateRankCategoryById(@PathVariable("id") short id, @RequestBody RankCategoryDto rankCategoryDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(rankCategoryService.updateRankCategoryById(id, rankCategoryDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_RANK_CATEGORY)
    public ResponseEntity<RankCategoryDto> deleteRankCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        RankCategoryDto rankCategoryDto = rankCategoryService.deleteRankCategoryById(id);
        return new ResponseEntity<>(rankCategoryDto, HttpStatus.OK);
    }

}
