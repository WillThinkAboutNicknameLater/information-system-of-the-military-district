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

    public StaffCategoryController(StaffCategoryService staffCategoryService) {
        this.staffCategoryService = staffCategoryService;
    }

    @GetMapping(GET_STAFF_CATEGORIES)
    public ResponseEntity<List<StaffCategoryDto>> getAllStaffCategories() {
        List<StaffCategoryDto> staffCategories = staffCategoryService.getAllStaffCategories();
        return new ResponseEntity<>(staffCategories, HttpStatus.OK);
    }

    @GetMapping(GET_STAFF_CATEGORIES_WITH_SEARCH_FILTER)
    public ResponseEntity<Page<StaffCategoryDto>> getAllStaffCategoriesWithFilters(@RequestBody(required = false) StaffCategorySearchFilter staffCategorySearchFilter) throws EntityNotFoundException {
        Page<StaffCategoryDto> staffCategories = staffCategoryService.getAllStaffCategoriesWithFilters(staffCategorySearchFilter);
        return new ResponseEntity<>(staffCategories, HttpStatus.OK);
    }

    @GetMapping(GET_STAFF_CATEGORY)
    public ResponseEntity<StaffCategoryDto> getStaffCategoryById(@PathVariable("id") short id) throws EntityNotFoundException {
        StaffCategoryDto staffCategory = staffCategoryService.getStaffCategoryById(id);
        return new ResponseEntity<>(staffCategory, HttpStatus.OK);
    }

}
