package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.ArmamentCategoryDto;
import ru.nsu.fit.militarysystem.filter.ArmamentCategorySearchFilter;
import ru.nsu.fit.militarysystem.mapper.ArmamentCategoryMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.ArmamentCategory;
import ru.nsu.fit.militarysystem.store.repository.ArmamentCategoryRepository;

import java.util.*;

@Service
public class ArmamentCategoryService {
    private final ArmamentCategoryRepository armamentCategoryRepository;

    private final ArmamentCategoryMapper armamentCategoryMapper;

    public ArmamentCategoryService(ArmamentCategoryRepository armamentCategoryRepository, ArmamentCategoryMapper armamentCategoryMapper) {
        this.armamentCategoryRepository = armamentCategoryRepository;
        this.armamentCategoryMapper = armamentCategoryMapper;
    }

    private ArmamentCategory saveArmamentCategory(ArmamentCategoryDto armamentCategoryDto) {
        ArmamentCategory newArmamentCategory = armamentCategoryMapper.dtoToEntity(armamentCategoryDto);
        return armamentCategoryRepository.save(newArmamentCategory);
    }

    public List<ArmamentCategoryDto> getAllArmamentCategoriesAsDtos() {
        List<ArmamentCategory> armamentCategories = new ArrayList<>(armamentCategoryRepository.findAll());
        return armamentCategoryMapper.entitiesToDtos(armamentCategories);
    }

    public Page<ArmamentCategoryDto> getAllArmamentCategoriesByFilterAsDtos(ArmamentCategorySearchFilter armamentCategorySearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(armamentCategorySearchFilter.getPageNumber(), armamentCategorySearchFilter.getPageSize(),
                                           armamentCategorySearchFilter.getSortDirection(),
                                           armamentCategorySearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = armamentCategorySearchFilter.getSearchName();
        Page<ArmamentCategory> armamentCategories = armamentCategoryRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        if (armamentCategories.isEmpty()) {
            throw new EntityNotFoundException(ArmamentCategory[].class,
                                              Map.of("armamentCategorySearchFilter", armamentCategorySearchFilter.toString()));
        }

        return armamentCategoryMapper.entitiesToDtos(armamentCategories);
    }

    public ArmamentCategoryDto getArmamentCategoryByIdAsDto(short id) throws EntityNotFoundException {
        Optional<ArmamentCategory> armamentCategory = armamentCategoryRepository.findById(id);
        if (armamentCategory.isEmpty()) {
            throw new EntityNotFoundException(ArmamentCategory.class, Map.of("id", String.valueOf(id)));
        }
        return armamentCategoryMapper.entityToDto(armamentCategory.get());
    }

    public ArmamentCategory getArmamentCategoryByName(String name) throws EntityNotFoundException {
        Optional<ArmamentCategory> armamentCategory = armamentCategoryRepository.findByName(name);
        if (armamentCategory.isEmpty()) {
            throw new EntityNotFoundException(ArmamentCategory.class, Map.of("name", name));
        }
        return armamentCategory.get();
    }

    public ArmamentCategoryDto createArmamentCategory(ArmamentCategoryDto armamentCategoryDto) throws EntityAlreadyExistsException {
        Short id = armamentCategoryDto.getId();
        if (Objects.isNull(id)) {
            return armamentCategoryMapper.entityToDto(saveArmamentCategory(armamentCategoryDto));
        }
        Optional<ArmamentCategory> armamentCategory = armamentCategoryRepository.findById(id);
        if (armamentCategory.isPresent()) {
            throw new EntityAlreadyExistsException(ArmamentCategory.class, Map.of("id", String.valueOf(id)));
        }
        return armamentCategoryMapper.entityToDto(saveArmamentCategory(armamentCategoryDto));
    }

    public ArmamentCategoryDto updateArmamentCategoryById(short id, ArmamentCategoryDto armamentCategoryDto) throws EntityNotFoundException {
        Optional<ArmamentCategory> armamentCategory = armamentCategoryRepository.findById(id);
        if (armamentCategory.isEmpty()) {
            throw new EntityNotFoundException(ArmamentCategory.class, Map.of("id", String.valueOf(id)));
        }
        armamentCategoryDto.setId(id);
        return armamentCategoryMapper.entityToDto(saveArmamentCategory(armamentCategoryDto));
    }

    public ArmamentCategoryDto deleteArmamentCategoryById(short id) throws EntityNotFoundException {
        Optional<ArmamentCategory> armamentCategory = armamentCategoryRepository.findById(id);
        if (armamentCategory.isEmpty()) {
            throw new EntityNotFoundException(ArmamentCategory.class, Map.of("id", String.valueOf(id)));
        }
        armamentCategoryRepository.deleteById(id);
        return armamentCategoryMapper.entityToDto(armamentCategory.get());
    }
}
