package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.CombatVehicleCategoryDto;
import ru.nsu.fit.militarysystem.filter.CombatVehicleCategorySearchFilter;
import ru.nsu.fit.militarysystem.service.CombatVehicleCategoryService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CombatVehicleCategoryController {
    private final CombatVehicleCategoryService combatVehicleCategoryService;

    private static final String GET_COMBAT_VEHICLE_CATEGORIES = "/combat-vehicle-categories";

    private static final String GET_COMBAT_VEHICLE_CATEGORIES_BY_SEARCH_FILTER = "/combat-vehicle-categories/search";

    private static final String GET_COMBAT_VEHICLE_CATEGORY = "/combat-vehicle-categories/{id}";

    private static final String POST_COMBAT_VEHICLE_CATEGORY = "/combat-vehicle-categories";

    private static final String PUT_COMBAT_VEHICLE_CATEGORY = "/combat-vehicle-categories/{id}";

    private static final String DELETE_COMBAT_VEHICLE_CATEGORY = "/combat-vehicle-categories/{id}";

    public CombatVehicleCategoryController(CombatVehicleCategoryService combatVehicleCategoryService) {
        this.combatVehicleCategoryService = combatVehicleCategoryService;
    }

    @GetMapping(GET_COMBAT_VEHICLE_CATEGORIES)
    public ResponseEntity<List<CombatVehicleCategoryDto>> getAllCombatVehicleCategories() {
        List<CombatVehicleCategoryDto> combatVehicleCategoryDtos = combatVehicleCategoryService.getAllCombatVehicleCategoriesAsDtos();
        return new ResponseEntity<>(combatVehicleCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLE_CATEGORIES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<CombatVehicleCategoryDto>> getAllCombatVehicleCategoriesByFilter(@RequestObjectParam CombatVehicleCategorySearchFilter combatVehicleCategorySearchFilter)
            throws EntityNotFoundException {
        Page<CombatVehicleCategoryDto> combatVehicleCategoryDtos =
                combatVehicleCategoryService.getAllCombatVehicleCategoriesByFilterAsDtos(combatVehicleCategorySearchFilter);
        return new ResponseEntity<>(combatVehicleCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLE_CATEGORY)
    public ResponseEntity<CombatVehicleCategoryDto> getCombatVehicleCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        CombatVehicleCategoryDto combatVehicleCategoryDto = combatVehicleCategoryService.getCombatVehicleCategoryByIdAsDto(id);
        return new ResponseEntity<>(combatVehicleCategoryDto, HttpStatus.OK);
    }

    @PostMapping(POST_COMBAT_VEHICLE_CATEGORY)
    public ResponseEntity<CombatVehicleCategoryDto> createCombatVehicleCategory(@RequestBody CombatVehicleCategoryDto combatVehicleCategoryDto)
            throws EntityAlreadyExistsException {
        return new ResponseEntity<>(combatVehicleCategoryService.createCombatVehicleCategory(combatVehicleCategoryDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_COMBAT_VEHICLE_CATEGORY)
    public ResponseEntity<CombatVehicleCategoryDto> updateCombatVehicleCategoryById(@PathVariable("id") short id,
                                                                                    @RequestBody CombatVehicleCategoryDto combatVehicleCategoryDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(combatVehicleCategoryService.updateCombatVehicleCategoryById(id, combatVehicleCategoryDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_COMBAT_VEHICLE_CATEGORY)
    public ResponseEntity<CombatVehicleCategoryDto> deleteCombatVehicleCategoryById(@PathVariable("id") short id)
            throws EntityNotFoundException {
        CombatVehicleCategoryDto combatVehicleCategoryDto = combatVehicleCategoryService.deleteCombatVehicleCategoryById(id);
        return new ResponseEntity<>(combatVehicleCategoryDto, HttpStatus.OK);
    }

}
