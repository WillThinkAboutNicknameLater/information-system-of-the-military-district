package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.StaffCategoryDto;
import ru.nsu.fit.militarysystem.mapper.StaffCategoryMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.StaffCategorySearchFilter;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;
import ru.nsu.fit.militarysystem.store.repository.StaffCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StaffCategoryService {
    private final StaffCategoryRepository staffCategoryRepository;

    private final StaffCategoryMapper staffCategoryMapper;

    public StaffCategoryService(StaffCategoryRepository staffCategoryRepository, StaffCategoryMapper staffCategoryMapper) {
        this.staffCategoryRepository = staffCategoryRepository;
        this.staffCategoryMapper = staffCategoryMapper;
    }

    private StaffCategory saveStaffCategory(StaffCategoryDto staffCategoryDto) {
        StaffCategory newStaffCategory = staffCategoryMapper.dtoToEntity(staffCategoryDto);
        return staffCategoryRepository.save(newStaffCategory);
    }

    public List<StaffCategoryDto> getAllStaffCategoriesAsDtos() {
        List<StaffCategory> staffCategories = new ArrayList<>(staffCategoryRepository.findAll());
        return staffCategoryMapper.entitiesToDtos(staffCategories);
    }

    public Page<StaffCategoryDto> getAllStaffCategoriesByFilterAsDtos(StaffCategorySearchFilter staffCategorySearchFilter) throws EntityNotFoundException {
        if (staffCategorySearchFilter == null) {
            staffCategorySearchFilter = new StaffCategorySearchFilter();
        }

        PageCriteria pageCriteria = staffCategorySearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<StaffCategory> staffCategories = staffCategoryRepository.findAll(pageable);
        if (staffCategories.isEmpty()) {
            throw new EntityNotFoundException(StaffCategory.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return staffCategoryMapper.entitiesToDtos(staffCategories);
    }

    public StaffCategoryDto getStaffCategoryByIdAsDto(short id) throws EntityNotFoundException {
        Optional<StaffCategory> staffCategory = staffCategoryRepository.findById(id);
        if (staffCategory.isEmpty()) {
            throw new EntityNotFoundException(StaffCategory.class, Map.of("id", String.valueOf(id)));
        }
        return staffCategoryMapper.entityToDto(staffCategory.get());
    }

    public StaffCategory getStaffCategoryByName(String name) throws EntityNotFoundException {
        Optional<StaffCategory> staffCategory = staffCategoryRepository.findByName(name);
        if (staffCategory.isEmpty()) {
            throw new EntityNotFoundException(StaffCategory.class, Map.of("name", name));
        }
        return staffCategory.get();
    }

    public StaffCategoryDto createStaffCategory(StaffCategoryDto staffCategoryDto) {
        return staffCategoryMapper.entityToDto(saveStaffCategory(staffCategoryDto));
    }

    public StaffCategoryDto updateStaffCategoryById(short id, StaffCategoryDto staffCategoryDto) throws EntityNotFoundException {
        Optional<StaffCategory> staffCategory = staffCategoryRepository.findById(id);
        if (staffCategory.isEmpty()) {
            throw new EntityNotFoundException(StaffCategory.class, Map.of("id", String.valueOf(id)));
        }
        staffCategoryDto.setId(id);
        return staffCategoryMapper.entityToDto(saveStaffCategory(staffCategoryDto));
    }

    public StaffCategoryDto deleteStaffCategoryById(short id) throws EntityNotFoundException {
        Optional<StaffCategory> staffCategory = staffCategoryRepository.findById(id);
        if (staffCategory.isEmpty()) {
            throw new EntityNotFoundException(StaffCategory.class, Map.of("id", String.valueOf(id)));
        }
        staffCategoryRepository.deleteById(id);
        return staffCategoryMapper.entityToDto(staffCategory.get());
    }
}
