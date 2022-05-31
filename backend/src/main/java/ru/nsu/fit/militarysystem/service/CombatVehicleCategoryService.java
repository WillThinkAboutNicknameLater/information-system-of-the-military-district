package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.CombatVehicleCategoryDto;
import ru.nsu.fit.militarysystem.filter.CombatVehicleCategorySearchFilter;
import ru.nsu.fit.militarysystem.mapper.CombatVehicleCategoryMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicleCategory;
import ru.nsu.fit.militarysystem.store.repository.CombatVehicleCategoryRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class CombatVehicleCategoryService {
    private final CombatVehicleCategoryRepository combatVehicleCategoryRepository;

    private final CombatVehicleCategoryMapper combatVehicleCategoryMapper;

    public CombatVehicleCategoryService(CombatVehicleCategoryRepository combatVehicleCategoryRepository,
                                        CombatVehicleCategoryMapper combatVehicleCategoryMapper) {
        this.combatVehicleCategoryRepository = combatVehicleCategoryRepository;
        this.combatVehicleCategoryMapper = combatVehicleCategoryMapper;
    }

    private CombatVehicleCategory saveCombatVehicleCategory(CombatVehicleCategoryDto combatVehicleCategoryDto) {
        CombatVehicleCategory newCombatVehicleCategory = combatVehicleCategoryMapper.dtoToEntity(combatVehicleCategoryDto);
        return combatVehicleCategoryRepository.save(newCombatVehicleCategory);
    }

    public List<CombatVehicleCategoryDto> getAllCombatVehicleCategoriesAsDtos() {
        List<CombatVehicleCategory> combatVehicleCategories = new ArrayList<>(combatVehicleCategoryRepository.findAll());
        return combatVehicleCategoryMapper.entitiesToDtos(combatVehicleCategories);
    }

    public Page<CombatVehicleCategoryDto> getAllCombatVehicleCategoriesByFilterAsDtos(CombatVehicleCategorySearchFilter combatVehicleCategorySearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(combatVehicleCategorySearchFilter.getPageNumber(),
                                           combatVehicleCategorySearchFilter.getPageSize(),
                                           combatVehicleCategorySearchFilter.getSortDirection(),
                                           combatVehicleCategorySearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = combatVehicleCategorySearchFilter.getSearchName();
        String combatVehicleGroupIds = IterableToPostgresqlArrayConverter.convert(combatVehicleCategorySearchFilter.getCombatVehicleGroupIds());
        Page<CombatVehicleCategory> combatVehicleCategories =
                combatVehicleCategoryRepository.findAllByFilter(searchName, combatVehicleGroupIds, pageable);

        return combatVehicleCategoryMapper.entitiesToDtos(combatVehicleCategories);
    }

    public CombatVehicleCategoryDto getCombatVehicleCategoryByIdAsDto(short id) throws EntityNotFoundException {
        Optional<CombatVehicleCategory> combatVehicleCategory = combatVehicleCategoryRepository.findById(id);
        if (combatVehicleCategory.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleCategory.class, Map.of("id", String.valueOf(id)));
        }
        return combatVehicleCategoryMapper.entityToDto(combatVehicleCategory.get());
    }

    public CombatVehicleCategory getCombatVehicleCategoryByName(String name) throws EntityNotFoundException {
        Optional<CombatVehicleCategory> combatVehicleCategory = combatVehicleCategoryRepository.findByName(name);
        if (combatVehicleCategory.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleCategory.class, Map.of("name", name));
        }
        return combatVehicleCategory.get();
    }

    public CombatVehicleCategoryDto createCombatVehicleCategory(CombatVehicleCategoryDto combatVehicleCategoryDto)
            throws EntityAlreadyExistsException {
        Short id = combatVehicleCategoryDto.getId();
        if (Objects.isNull(id)) {
            return combatVehicleCategoryMapper.entityToDto(saveCombatVehicleCategory(combatVehicleCategoryDto));
        }
        Optional<CombatVehicleCategory> combatVehicleCategory = combatVehicleCategoryRepository.findById(id);
        if (combatVehicleCategory.isPresent()) {
            throw new EntityAlreadyExistsException(CombatVehicleCategory.class, Map.of("id", String.valueOf(id)));
        }
        return combatVehicleCategoryMapper.entityToDto(saveCombatVehicleCategory(combatVehicleCategoryDto));
    }

    public CombatVehicleCategoryDto updateCombatVehicleCategoryById(short id, CombatVehicleCategoryDto combatVehicleCategoryDto)
            throws EntityNotFoundException {
        Optional<CombatVehicleCategory> combatVehicleCategory = combatVehicleCategoryRepository.findById(id);
        if (combatVehicleCategory.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleCategory.class, Map.of("id", String.valueOf(id)));
        }
        combatVehicleCategoryDto.setId(id);
        return combatVehicleCategoryMapper.entityToDto(saveCombatVehicleCategory(combatVehicleCategoryDto));
    }

    public CombatVehicleCategoryDto deleteCombatVehicleCategoryById(short id) throws EntityNotFoundException {
        Optional<CombatVehicleCategory> combatVehicleCategory = combatVehicleCategoryRepository.findById(id);
        if (combatVehicleCategory.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleCategory.class, Map.of("id", String.valueOf(id)));
        }
        combatVehicleCategoryRepository.deleteById(id);
        return combatVehicleCategoryMapper.entityToDto(combatVehicleCategory.get());
    }
}
