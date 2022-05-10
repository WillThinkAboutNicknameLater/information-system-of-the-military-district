package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.ArmamentCategoryDto;
import ru.nsu.fit.militarysystem.filter.ArmamentCategorySearchFilter;
import ru.nsu.fit.militarysystem.service.ArmamentCategoryService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ArmamentCategoryController {
    private final ArmamentCategoryService armamentCategoryService;

    private static final String GET_ARMAMENT_CATEGORIES = "/armament-categories";

    private static final String GET_ARMAMENT_CATEGORIES_BY_SEARCH_FILTER = "/armament-categories/search";

    private static final String GET_ARMAMENT_CATEGORY = "/armament-categories/{id}";

    private static final String POST_ARMAMENT_CATEGORY = "/armament-categories";

    private static final String PUT_ARMAMENT_CATEGORY = "/armament-categories/{id}";

    private static final String DELETE_ARMAMENT_CATEGORY = "/armament-categories/{id}";

    public ArmamentCategoryController(ArmamentCategoryService armamentCategoryService) {
        this.armamentCategoryService = armamentCategoryService;
    }

    @GetMapping(GET_ARMAMENT_CATEGORIES)
    public ResponseEntity<List<ArmamentCategoryDto>> getAllArmamentCategories() {
        List<ArmamentCategoryDto> armamentCategoryDtos = armamentCategoryService.getAllArmamentCategoriesAsDtos();
        return new ResponseEntity<>(armamentCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_ARMAMENT_CATEGORIES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<ArmamentCategoryDto>> getAllArmamentCategoriesByFilter(@RequestObjectParam ArmamentCategorySearchFilter armamentCategorySearchFilter)
            throws EntityNotFoundException {
        Page<ArmamentCategoryDto> armamentCategoryDtos = armamentCategoryService.getAllArmamentCategoriesByFilterAsDtos(armamentCategorySearchFilter);
        return new ResponseEntity<>(armamentCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_ARMAMENT_CATEGORY)
    public ResponseEntity<ArmamentCategoryDto> getArmamentCategoryById(@PathVariable("id") short id)
            throws EntityNotFoundException {
        ArmamentCategoryDto armamentCategoryDto = armamentCategoryService.getArmamentCategoryByIdAsDto(id);
        return new ResponseEntity<>(armamentCategoryDto, HttpStatus.OK);
    }

    @PostMapping(POST_ARMAMENT_CATEGORY)
    public ResponseEntity<ArmamentCategoryDto> createArmamentCategory(@RequestBody ArmamentCategoryDto armamentCategoryDto)
            throws EntityAlreadyExistsException {
        return new ResponseEntity<>(armamentCategoryService.createArmamentCategory(armamentCategoryDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_ARMAMENT_CATEGORY)
    public ResponseEntity<ArmamentCategoryDto> updateArmamentCategoryById(@PathVariable("id") short id,
                                                                          @RequestBody ArmamentCategoryDto armamentCategoryDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(armamentCategoryService.updateArmamentCategoryById(id, armamentCategoryDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_ARMAMENT_CATEGORY)
    public ResponseEntity<ArmamentCategoryDto> deleteArmamentCategoryById(@PathVariable("id") short id)
            throws EntityNotFoundException {
        ArmamentCategoryDto armamentCategoryDto = armamentCategoryService.deleteArmamentCategoryById(id);
        return new ResponseEntity<>(armamentCategoryDto, HttpStatus.OK);
    }

}
