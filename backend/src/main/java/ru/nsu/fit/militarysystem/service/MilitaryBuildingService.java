package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryBuildingDto;
import ru.nsu.fit.militarysystem.filter.MilitaryBuildingSearchFilter;
import ru.nsu.fit.militarysystem.mapper.MilitaryBuildingMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.MilitaryBuilding;
import ru.nsu.fit.militarysystem.store.repository.MilitaryBuildingRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class MilitaryBuildingService {
    private final MilitaryBuildingRepository militaryBuildingRepository;

    private final MilitaryBuildingMapper militaryBuildingMapper;

    public MilitaryBuildingService(MilitaryBuildingRepository militaryBuildingRepository, MilitaryBuildingMapper militaryBuildingMapper) {
        this.militaryBuildingRepository = militaryBuildingRepository;
        this.militaryBuildingMapper = militaryBuildingMapper;
    }

    private MilitaryBuilding saveMilitaryBuilding(MilitaryBuildingDto militaryBuildingDto) {
        MilitaryBuilding newMilitaryBuilding = militaryBuildingMapper.dtoToEntity(militaryBuildingDto);
        return militaryBuildingRepository.save(newMilitaryBuilding);
    }

    public List<MilitaryBuildingDto> getAllMilitaryBuildingsAsDtos() {
        List<MilitaryBuilding> militaryBuildings = new ArrayList<>(militaryBuildingRepository.findAll());
        return militaryBuildingMapper.entitiesToDtos(militaryBuildings);
    }

    public Page<MilitaryBuildingDto> getAllMilitaryBuildingsByFilterAsDtos(MilitaryBuildingSearchFilter militaryBuildingSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(militaryBuildingSearchFilter.getPageNumber(),
                                           militaryBuildingSearchFilter.getPageSize(),
                                           militaryBuildingSearchFilter.getSortDirection(),
                                           militaryBuildingSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = militaryBuildingSearchFilter.getSearchName();
        String militaryBuildingCategoryIds =
                IterableToPostgresqlArrayConverter.convert(militaryBuildingSearchFilter.getMilitaryBuildingCategoryIds());
        String militaryFormationIds = IterableToPostgresqlArrayConverter.convert(militaryBuildingSearchFilter.getMilitaryFormationIds());
        Page<MilitaryBuilding> militaryBuildings =
                militaryBuildingRepository.findAllByFilter(searchName, militaryBuildingCategoryIds, militaryFormationIds, pageable);

        return militaryBuildingMapper.entitiesToDtos(militaryBuildings);
    }

    public MilitaryBuildingDto getMilitaryBuildingByIdAsDto(int id) throws EntityNotFoundException {
        Optional<MilitaryBuilding> militaryBuilding = militaryBuildingRepository.findById(id);
        if (militaryBuilding.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuilding.class, Map.of("id", String.valueOf(id)));
        }
        return militaryBuildingMapper.entityToDto(militaryBuilding.get());
    }

    public List<MilitaryBuildingDto> getMilitaryBuildingsByMilitaryFormationIdAsDtos(int militaryFormationId) throws EntityNotFoundException {
        List<MilitaryBuilding> militaryBuildings = militaryBuildingRepository.findAllByMilitaryFormationId(militaryFormationId);
        if (militaryBuildings.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuilding[].class, Map.of("militaryFormationId", String.valueOf(militaryFormationId)));
        }
        return militaryBuildingMapper.entitiesToDtos(militaryBuildings);
    }

    public MilitaryBuildingDto createMilitaryBuilding(MilitaryBuildingDto militaryBuildingDto) throws EntityAlreadyExistsException {
        Integer id = militaryBuildingDto.getId();
        if (Objects.isNull(id)) {
            return militaryBuildingMapper.entityToDto(saveMilitaryBuilding(militaryBuildingDto));
        }
        Optional<MilitaryBuilding> militaryBuilding = militaryBuildingRepository.findById(id);
        if (militaryBuilding.isPresent()) {
            throw new EntityAlreadyExistsException(MilitaryBuilding.class, Map.of("id", String.valueOf(id)));
        }
        return militaryBuildingMapper.entityToDto(saveMilitaryBuilding(militaryBuildingDto));
    }

    public MilitaryBuildingDto updateMilitaryBuildingById(int id, MilitaryBuildingDto militaryBuildingDto) throws EntityNotFoundException {
        Optional<MilitaryBuilding> militaryBuilding = militaryBuildingRepository.findById(id);
        if (militaryBuilding.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuilding.class, Map.of("id", String.valueOf(id)));
        }
        militaryBuildingDto.setId(id);
        return militaryBuildingMapper.entityToDto(saveMilitaryBuilding(militaryBuildingDto));
    }

    public MilitaryBuildingDto deleteMilitaryBuildingById(int id) throws EntityNotFoundException {
        Optional<MilitaryBuilding> militaryBuilding = militaryBuildingRepository.findById(id);
        if (militaryBuilding.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuilding.class, Map.of("id", String.valueOf(id)));
        }
        militaryBuildingRepository.deleteById(id);
        return militaryBuildingMapper.entityToDto(militaryBuilding.get());
    }
}
