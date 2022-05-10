package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitaryBuildingCategoryDto;
import ru.nsu.fit.militarysystem.filter.MilitaryBuildingCategorySearchFilter;
import ru.nsu.fit.militarysystem.service.MilitaryBuildingCategoryService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryBuildingCategoryController {
    private final MilitaryBuildingCategoryService militaryBuildingCategoryService;

    private static final String GET_MILITARY_BUILDING_CATEGORIES = "/military-building-categories";

    private static final String GET_MILITARY_BUILDING_CATEGORIES_BY_SEARCH_FILTER = "/military-building-categories/search";

    private static final String GET_MILITARY_BUILDING_CATEGORY = "/military-building-categories/{id}";

    private static final String POST_MILITARY_BUILDING_CATEGORY = "/military-building-categories";

    private static final String PUT_MILITARY_BUILDING_CATEGORY = "/military-building-categories/{id}";

    private static final String DELETE_MILITARY_BUILDING_CATEGORY = "/military-building-categories/{id}";

    public MilitaryBuildingCategoryController(MilitaryBuildingCategoryService militaryBuildingCategoryService) {
        this.militaryBuildingCategoryService = militaryBuildingCategoryService;
    }

    @GetMapping(GET_MILITARY_BUILDING_CATEGORIES)
    public ResponseEntity<List<MilitaryBuildingCategoryDto>> getAllMilitaryBuildingCategories() {
        List<MilitaryBuildingCategoryDto> militaryBuildingCategoryDtos = militaryBuildingCategoryService.getAllMilitaryBuildingCategoriesAsDtos();
        return new ResponseEntity<>(militaryBuildingCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_BUILDING_CATEGORIES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitaryBuildingCategoryDto>> getAllMilitaryBuildingCategoriesByFilter(@RequestObjectParam MilitaryBuildingCategorySearchFilter militaryBuildingCategorySearchFilter)
            throws EntityNotFoundException {
        Page<MilitaryBuildingCategoryDto> militaryBuildingCategoryDtos =
                militaryBuildingCategoryService.getAllMilitaryBuildingCategoriesByFilterAsDtos(militaryBuildingCategorySearchFilter);
        return new ResponseEntity<>(militaryBuildingCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_BUILDING_CATEGORY)
    public ResponseEntity<MilitaryBuildingCategoryDto> getMilitaryBuildingCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryBuildingCategoryDto militaryBuildingCategoryDto = militaryBuildingCategoryService.getMilitaryBuildingCategoryByIdAsDto(id);
        return new ResponseEntity<>(militaryBuildingCategoryDto, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_BUILDING_CATEGORY)
    public ResponseEntity<MilitaryBuildingCategoryDto> createMilitaryBuildingCategory(@RequestBody MilitaryBuildingCategoryDto militaryBuildingCategoryDto)
            throws
            EntityAlreadyExistsException {
        return new ResponseEntity<>(militaryBuildingCategoryService.createMilitaryBuildingCategory(militaryBuildingCategoryDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_MILITARY_BUILDING_CATEGORY)
    public ResponseEntity<MilitaryBuildingCategoryDto> updateMilitaryBuildingCategoryById(@PathVariable("id") short id,
                                                                                          @RequestBody MilitaryBuildingCategoryDto militaryBuildingCategoryDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(militaryBuildingCategoryService.updateMilitaryBuildingCategoryById(id, militaryBuildingCategoryDto),
                                    HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_BUILDING_CATEGORY)
    public ResponseEntity<MilitaryBuildingCategoryDto> deleteMilitaryBuildingCategoryById(@PathVariable("id") short id)
            throws EntityNotFoundException {
        MilitaryBuildingCategoryDto militaryBuildingCategoryDto = militaryBuildingCategoryService.deleteMilitaryBuildingCategoryById(id);
        return new ResponseEntity<>(militaryBuildingCategoryDto, HttpStatus.OK);
    }

}
