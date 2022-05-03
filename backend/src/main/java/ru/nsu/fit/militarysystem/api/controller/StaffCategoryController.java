package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.StaffCategoryDto;
import ru.nsu.fit.militarysystem.service.StaffCategoryService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.StaffCategorySearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class StaffCategoryController {
    private final StaffCategoryService staffCategoryService;

    private static final String GET_STAFF_CATEGORIES = "/staff-categories";

    private static final String GET_STAFF_CATEGORIES_WITH_SEARCH_FILTER = "/staff-categories/search";

    private static final String GET_STAFF_CATEGORY = "/staff-categories/{id}";

    private static final String POST_STAFF_CATEGORY = "/staff-categories";

    private static final String PUT_STAFF_CATEGORY = "/staff-categories/{id}";

    private static final String DELETE_STAFF_CATEGORY = "/staff-categories/{id}";

    public StaffCategoryController(StaffCategoryService staffCategoryService) {
        this.staffCategoryService = staffCategoryService;
    }

    @GetMapping(GET_STAFF_CATEGORIES)
    public ResponseEntity<List<StaffCategoryDto>> getAllStaffCategories() {
        List<StaffCategoryDto> staffCategoryDtos = staffCategoryService.getAllStaffCategoriesAsDtos();
        return new ResponseEntity<>(staffCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_STAFF_CATEGORIES_WITH_SEARCH_FILTER)
    public ResponseEntity<Page<StaffCategoryDto>> getAllStaffCategoriesByFilter(@RequestBody(required = false) StaffCategorySearchFilter staffCategorySearchFilter) throws EntityNotFoundException {
        Page<StaffCategoryDto> staffCategoryDtos = staffCategoryService.getAllStaffCategoriesByFilterAsDtos(staffCategorySearchFilter);
        return new ResponseEntity<>(staffCategoryDtos, HttpStatus.OK);
    }

    @GetMapping(GET_STAFF_CATEGORY)
    public ResponseEntity<StaffCategoryDto> getStaffCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        StaffCategoryDto staffCategoryDto = staffCategoryService.getStaffCategoryByIdAsDto(id);
        return new ResponseEntity<>(staffCategoryDto, HttpStatus.OK);
    }

    @PostMapping(POST_STAFF_CATEGORY)
    public ResponseEntity<StaffCategoryDto> createStaffCategory(@RequestBody StaffCategoryDto staffCategoryDto) {
        return new ResponseEntity<>(staffCategoryService.createStaffCategory(staffCategoryDto), HttpStatus.OK);
    }

    @PutMapping(PUT_STAFF_CATEGORY)
    public ResponseEntity<StaffCategoryDto> updateStaffCategoryById(@PathVariable("id") short id, @RequestBody StaffCategoryDto staffCategoryDto) throws EntityNotFoundException {
        return new ResponseEntity<>(staffCategoryService.updateStaffCategoryById(id, staffCategoryDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_STAFF_CATEGORY)
    public ResponseEntity<StaffCategoryDto> deleteStaffCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        StaffCategoryDto staffCategoryDto = staffCategoryService.deleteStaffCategoryById(id);
        return new ResponseEntity<>(staffCategoryDto, HttpStatus.OK);
    }

}
