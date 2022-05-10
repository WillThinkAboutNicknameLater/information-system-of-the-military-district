package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryBuildingCategoryDto;
import ru.nsu.fit.militarysystem.filter.MilitaryBuildingCategorySearchFilter;
import ru.nsu.fit.militarysystem.mapper.MilitaryBuildingCategoryMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.MilitaryBuildingCategory;
import ru.nsu.fit.militarysystem.store.repository.MilitaryBuildingCategoryRepository;

import java.util.*;

@Service
public class MilitaryBuildingCategoryService {
    private final MilitaryBuildingCategoryRepository militaryBuildingCategoryRepository;

    private final MilitaryBuildingCategoryMapper militaryBuildingCategoryMapper;

    public MilitaryBuildingCategoryService(MilitaryBuildingCategoryRepository militaryBuildingCategoryRepository,
                                           MilitaryBuildingCategoryMapper militaryBuildingCategoryMapper) {
        this.militaryBuildingCategoryRepository = militaryBuildingCategoryRepository;
        this.militaryBuildingCategoryMapper = militaryBuildingCategoryMapper;
    }

    private MilitaryBuildingCategory saveMilitaryBuildingCategory(MilitaryBuildingCategoryDto militaryBuildingCategoryDto) {
        MilitaryBuildingCategory newMilitaryBuildingCategory = militaryBuildingCategoryMapper.dtoToEntity(militaryBuildingCategoryDto);
        return militaryBuildingCategoryRepository.save(newMilitaryBuildingCategory);
    }

    public List<MilitaryBuildingCategoryDto> getAllMilitaryBuildingCategoriesAsDtos() {
        List<MilitaryBuildingCategory> militaryBuildingCategories = new ArrayList<>(militaryBuildingCategoryRepository.findAll());
        return militaryBuildingCategoryMapper.entitiesToDtos(militaryBuildingCategories);
    }

    public Page<MilitaryBuildingCategoryDto> getAllMilitaryBuildingCategoriesByFilterAsDtos(MilitaryBuildingCategorySearchFilter militaryBuildingCategorySearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(militaryBuildingCategorySearchFilter.getPageNumber(),
                                           militaryBuildingCategorySearchFilter.getPageSize(),
                                           militaryBuildingCategorySearchFilter.getSortDirection(),
                                           militaryBuildingCategorySearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = militaryBuildingCategorySearchFilter.getSearchName();
        Page<MilitaryBuildingCategory> militaryBuildingCategories =
                militaryBuildingCategoryRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        if (militaryBuildingCategories.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuildingCategory[].class,
                                              Map.of("militaryBuildingCategorySearchFilter", militaryBuildingCategorySearchFilter.toString()));
        }

        return militaryBuildingCategoryMapper.entitiesToDtos(militaryBuildingCategories);
    }

    public MilitaryBuildingCategoryDto getMilitaryBuildingCategoryByIdAsDto(short id) throws EntityNotFoundException {
        Optional<MilitaryBuildingCategory> militaryBuildingCategory = militaryBuildingCategoryRepository.findById(id);
        if (militaryBuildingCategory.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuildingCategory.class, Map.of("id", String.valueOf(id)));
        }
        return militaryBuildingCategoryMapper.entityToDto(militaryBuildingCategory.get());
    }

    public MilitaryBuildingCategory getMilitaryBuildingCategoryByName(String name) throws EntityNotFoundException {
        Optional<MilitaryBuildingCategory> militaryBuildingCategory = militaryBuildingCategoryRepository.findByName(name);
        if (militaryBuildingCategory.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuildingCategory.class, Map.of("name", name));
        }
        return militaryBuildingCategory.get();
    }

    public MilitaryBuildingCategoryDto createMilitaryBuildingCategory(MilitaryBuildingCategoryDto militaryBuildingCategoryDto)
            throws EntityAlreadyExistsException {
        Short id = militaryBuildingCategoryDto.getId();
        if (Objects.isNull(id)) {
            return militaryBuildingCategoryMapper.entityToDto(saveMilitaryBuildingCategory(militaryBuildingCategoryDto));
        }
        Optional<MilitaryBuildingCategory> militaryBuildingCategory = militaryBuildingCategoryRepository.findById(id);
        if (militaryBuildingCategory.isPresent()) {
            throw new EntityAlreadyExistsException(MilitaryBuildingCategory.class, Map.of("id", String.valueOf(id)));
        }
        return militaryBuildingCategoryMapper.entityToDto(saveMilitaryBuildingCategory(militaryBuildingCategoryDto));
    }

    public MilitaryBuildingCategoryDto updateMilitaryBuildingCategoryById(short id, MilitaryBuildingCategoryDto militaryBuildingCategoryDto)
            throws EntityNotFoundException {
        Optional<MilitaryBuildingCategory> militaryBuildingCategory = militaryBuildingCategoryRepository.findById(id);
        if (militaryBuildingCategory.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuildingCategory.class, Map.of("id", String.valueOf(id)));
        }
        militaryBuildingCategoryDto.setId(id);
        return militaryBuildingCategoryMapper.entityToDto(saveMilitaryBuildingCategory(militaryBuildingCategoryDto));
    }

    public MilitaryBuildingCategoryDto deleteMilitaryBuildingCategoryById(short id) throws EntityNotFoundException {
        Optional<MilitaryBuildingCategory> militaryBuildingCategory = militaryBuildingCategoryRepository.findById(id);
        if (militaryBuildingCategory.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuildingCategory.class, Map.of("id", String.valueOf(id)));
        }
        militaryBuildingCategoryRepository.deleteById(id);
        return militaryBuildingCategoryMapper.entityToDto(militaryBuildingCategory.get());
    }
}
