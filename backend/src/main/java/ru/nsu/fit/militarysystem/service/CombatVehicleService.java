package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.CombatVehicleDto;
import ru.nsu.fit.militarysystem.filter.CombatVehicleSearchFilter;
import ru.nsu.fit.militarysystem.mapper.CombatVehicleMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicle;
import ru.nsu.fit.militarysystem.store.repository.CombatVehicleRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class CombatVehicleService {
    private final CombatVehicleRepository combatVehicleRepository;

    private final CombatVehicleMapper combatVehicleMapper;

    public CombatVehicleService(CombatVehicleRepository combatVehicleRepository, CombatVehicleMapper combatVehicleMapper) {
        this.combatVehicleRepository = combatVehicleRepository;
        this.combatVehicleMapper = combatVehicleMapper;
    }

    private CombatVehicle saveCombatVehicle(CombatVehicleDto combatVehicleDto) {
        CombatVehicle newCombatVehicle = combatVehicleMapper.dtoToEntity(combatVehicleDto);
        return combatVehicleRepository.save(newCombatVehicle);
    }

    public List<CombatVehicleDto> getAllCombatVehiclesAsDtos() {
        List<CombatVehicle> combatVehicles = new ArrayList<>(combatVehicleRepository.findAll());
        return combatVehicleMapper.entitiesToDtos(combatVehicles);
    }

    public Page<CombatVehicleDto> getAllCombatVehiclesByFilterAsDtos(CombatVehicleSearchFilter combatVehicleSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(combatVehicleSearchFilter.getPageNumber(), combatVehicleSearchFilter.getPageSize(),
                                           combatVehicleSearchFilter.getSortDirection(),
                                           combatVehicleSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = combatVehicleSearchFilter.getSearchName();
        String combatVehicleCategoryIds = IterableToPostgresqlArrayConverter.convert(combatVehicleSearchFilter.getCombatVehicleCategoryIds());
        String combatVehicleGroupIds = IterableToPostgresqlArrayConverter.convert(combatVehicleSearchFilter.getCombatVehicleGroupIds());
        String militaryFormationIds = IterableToPostgresqlArrayConverter.convert(combatVehicleSearchFilter.getMilitaryFormationIds());
        Page<CombatVehicle> combatVehicles =
                combatVehicleRepository.findAllByFilter(searchName, combatVehicleCategoryIds, combatVehicleGroupIds, militaryFormationIds, pageable);

        return combatVehicleMapper.entitiesToDtos(combatVehicles);
    }

    public CombatVehicleDto getCombatVehicleByIdAsDto(int id) throws EntityNotFoundException {
        Optional<CombatVehicle> combatVehicle = combatVehicleRepository.findById(id);
        if (combatVehicle.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicle.class, Map.of("id", String.valueOf(id)));
        }
        return combatVehicleMapper.entityToDto(combatVehicle.get());
    }

    public List<CombatVehicleDto> getCombatVehiclesByMilitaryFormationIdAsDtos(int militaryFormationId) throws EntityNotFoundException {
        List<CombatVehicle> combatVehicles = combatVehicleRepository.findAllByMilitaryFormationId(militaryFormationId);
        if (combatVehicles.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicle[].class, Map.of("militaryFormationId", String.valueOf(militaryFormationId)));
        }
        return combatVehicleMapper.entitiesToDtos(combatVehicles);
    }

    public CombatVehicleDto createCombatVehicle(CombatVehicleDto combatVehicleDto) throws EntityAlreadyExistsException {
        Integer id = combatVehicleDto.getId();
        if (Objects.isNull(id)) {
            return combatVehicleMapper.entityToDto(saveCombatVehicle(combatVehicleDto));
        }
        Optional<CombatVehicle> combatVehicle = combatVehicleRepository.findById(id);
        if (combatVehicle.isPresent()) {
            throw new EntityAlreadyExistsException(CombatVehicle.class, Map.of("id", String.valueOf(id)));
        }
        return combatVehicleMapper.entityToDto(saveCombatVehicle(combatVehicleDto));
    }

    public CombatVehicleDto updateCombatVehicleById(int id, CombatVehicleDto combatVehicleDto) throws EntityNotFoundException {
        Optional<CombatVehicle> combatVehicle = combatVehicleRepository.findById(id);
        if (combatVehicle.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicle.class, Map.of("id", String.valueOf(id)));
        }
        combatVehicleDto.setId(id);
        return combatVehicleMapper.entityToDto(saveCombatVehicle(combatVehicleDto));
    }

    public CombatVehicleDto deleteCombatVehicleById(int id) throws EntityNotFoundException {
        Optional<CombatVehicle> combatVehicle = combatVehicleRepository.findById(id);
        if (combatVehicle.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicle.class, Map.of("id", String.valueOf(id)));
        }
        combatVehicleRepository.deleteById(id);
        return combatVehicleMapper.entityToDto(combatVehicle.get());
    }
}
