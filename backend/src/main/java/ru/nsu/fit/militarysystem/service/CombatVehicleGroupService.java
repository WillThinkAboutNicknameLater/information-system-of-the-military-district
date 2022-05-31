package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.CombatVehicleGroupDto;
import ru.nsu.fit.militarysystem.filter.CombatVehicleGroupSearchFilter;
import ru.nsu.fit.militarysystem.mapper.CombatVehicleGroupMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicleGroup;
import ru.nsu.fit.militarysystem.store.repository.CombatVehicleGroupRepository;

import java.util.*;

@Service
public class CombatVehicleGroupService {
    private final CombatVehicleGroupRepository combatVehicleGroupRepository;

    private final CombatVehicleGroupMapper combatVehicleGroupMapper;

    public CombatVehicleGroupService(CombatVehicleGroupRepository combatVehicleGroupRepository, CombatVehicleGroupMapper combatVehicleGroupMapper) {
        this.combatVehicleGroupRepository = combatVehicleGroupRepository;
        this.combatVehicleGroupMapper = combatVehicleGroupMapper;
    }

    private CombatVehicleGroup saveCombatVehicleGroup(CombatVehicleGroupDto combatVehicleGroupDto) {
        CombatVehicleGroup newCombatVehicleGroup = combatVehicleGroupMapper.dtoToEntity(combatVehicleGroupDto);
        return combatVehicleGroupRepository.save(newCombatVehicleGroup);
    }

    public List<CombatVehicleGroupDto> getAllCombatVehicleGroupsAsDtos() {
        List<CombatVehicleGroup> combatVehicleGroups = new ArrayList<>(combatVehicleGroupRepository.findAll());
        return combatVehicleGroupMapper.entitiesToDtos(combatVehicleGroups);
    }

    public Page<CombatVehicleGroupDto> getAllCombatVehicleGroupsByFilterAsDtos(CombatVehicleGroupSearchFilter combatVehicleGroupSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(combatVehicleGroupSearchFilter.getPageNumber(),
                                           combatVehicleGroupSearchFilter.getPageSize(),
                                           combatVehicleGroupSearchFilter.getSortDirection(),
                                           combatVehicleGroupSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = combatVehicleGroupSearchFilter.getSearchName();
        Page<CombatVehicleGroup> combatVehicleGroups = combatVehicleGroupRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        return combatVehicleGroupMapper.entitiesToDtos(combatVehicleGroups);
    }

    public CombatVehicleGroupDto getCombatVehicleGroupByIdAsDto(short id) throws EntityNotFoundException {
        Optional<CombatVehicleGroup> combatVehicleGroup = combatVehicleGroupRepository.findById(id);
        if (combatVehicleGroup.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleGroup.class, Map.of("id", String.valueOf(id)));
        }
        return combatVehicleGroupMapper.entityToDto(combatVehicleGroup.get());
    }

    public CombatVehicleGroup getCombatVehicleGroupByName(String name) throws EntityNotFoundException {
        Optional<CombatVehicleGroup> combatVehicleGroup = combatVehicleGroupRepository.findByName(name);
        if (combatVehicleGroup.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleGroup.class, Map.of("name", name));
        }
        return combatVehicleGroup.get();
    }

    public CombatVehicleGroupDto createCombatVehicleGroup(CombatVehicleGroupDto combatVehicleGroupDto) throws EntityAlreadyExistsException {
        Short id = combatVehicleGroupDto.getId();
        if (Objects.isNull(id)) {
            return combatVehicleGroupMapper.entityToDto(saveCombatVehicleGroup(combatVehicleGroupDto));
        }
        Optional<CombatVehicleGroup> combatVehicleGroup = combatVehicleGroupRepository.findById(id);
        if (combatVehicleGroup.isPresent()) {
            throw new EntityAlreadyExistsException(CombatVehicleGroup.class, Map.of("id", String.valueOf(id)));
        }
        return combatVehicleGroupMapper.entityToDto(saveCombatVehicleGroup(combatVehicleGroupDto));
    }

    public CombatVehicleGroupDto updateCombatVehicleGroupById(short id, CombatVehicleGroupDto combatVehicleGroupDto) throws EntityNotFoundException {
        Optional<CombatVehicleGroup> combatVehicleGroup = combatVehicleGroupRepository.findById(id);
        if (combatVehicleGroup.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleGroup.class, Map.of("id", String.valueOf(id)));
        }
        combatVehicleGroupDto.setId(id);
        return combatVehicleGroupMapper.entityToDto(saveCombatVehicleGroup(combatVehicleGroupDto));
    }

    public CombatVehicleGroupDto deleteCombatVehicleGroupById(short id) throws EntityNotFoundException {
        Optional<CombatVehicleGroup> combatVehicleGroup = combatVehicleGroupRepository.findById(id);
        if (combatVehicleGroup.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicleGroup.class, Map.of("id", String.valueOf(id)));
        }
        combatVehicleGroupRepository.deleteById(id);
        return combatVehicleGroupMapper.entityToDto(combatVehicleGroup.get());
    }
}
