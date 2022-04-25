package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.service.RankCategoryService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.RankCategorySearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class RankCategoryController {
    private final RankCategoryService rankCategoryService;

    private static final String GET_RANK_CATEGORIES = "/rank-categories";

    private static final String GET_RANK_CATEGORIES_WITH_SEARCH_FILTER = "/rank-categories/search";

    private static final String GET_RANK_CATEGORY = "/rank_categories/{id}";

    public RankCategoryController(RankCategoryService rankCategoryService) {
        this.rankCategoryService = rankCategoryService;
    }

    @GetMapping(GET_RANK_CATEGORIES)
    public ResponseEntity<List<RankCategoryDto>> getAllRankCategories() {
        List<RankCategoryDto> rankCategories = rankCategoryService.getAllRankCategories();
        return new ResponseEntity<>(rankCategories, HttpStatus.OK);
    }

    @GetMapping(GET_RANK_CATEGORIES_WITH_SEARCH_FILTER)
    public ResponseEntity<Page<RankCategoryDto>> getAllRankCategoriesWithFilters(@RequestBody(required = false) RankCategorySearchFilter rankCategorySearchFilter) throws EntityNotFoundException {
        Page<RankCategoryDto> rankCategories = rankCategoryService.getAllRankCategoriesWithFilters(rankCategorySearchFilter);
        return new ResponseEntity<>(rankCategories, HttpStatus.OK);
    }

    @GetMapping(GET_RANK_CATEGORY)
    public ResponseEntity<RankCategoryDto> getRankCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        RankCategoryDto rankCategory = rankCategoryService.getRankCategoryById(id);
        return new ResponseEntity<>(rankCategory, HttpStatus.OK);
    }
}
