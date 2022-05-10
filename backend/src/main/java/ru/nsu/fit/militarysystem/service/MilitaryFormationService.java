package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.*;
import ru.nsu.fit.militarysystem.filter.MilitaryFormationSearchFilter;
import ru.nsu.fit.militarysystem.mapper.MilitaryFormationMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.*;
import ru.nsu.fit.militarysystem.store.repository.MilitaryFormationRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class MilitaryFormationService {
    private final MilitaryFormationRepository militaryFormationRepository;

    private final MilitaryFormationMapper militaryFormationMapper;

    private final MilitaryManService militaryManService;

    private final DislocationService dislocationService;

    private final CombatVehicleService combatVehicleService;

    private final ArmamentService armamentService;

    private final MilitaryBuildingService militaryBuildingService;

    public MilitaryFormationService(MilitaryFormationRepository militaryFormationRepository,
                                    MilitaryFormationMapper militaryFormationMapper,
                                    MilitaryManService militaryManService,
                                    DislocationService dislocationService,
                                    CombatVehicleService combatVehicleService,
                                    ArmamentService armamentService,
                                    MilitaryBuildingService militaryBuildingService) {
        this.militaryFormationRepository = militaryFormationRepository;
        this.militaryFormationMapper = militaryFormationMapper;
        this.militaryManService = militaryManService;
        this.dislocationService = dislocationService;
        this.combatVehicleService = combatVehicleService;
        this.armamentService = armamentService;
        this.militaryBuildingService = militaryBuildingService;
    }

    private MilitaryFormation saveMilitaryFormation(MilitaryFormationDto militaryFormationDto) {
        MilitaryFormation newMilitaryFormation = militaryFormationMapper.dtoToEntity(militaryFormationDto);
        return militaryFormationRepository.save(newMilitaryFormation);
    }

    public List<MilitaryFormationDto> getAllMilitaryFormationsAsDtos() {
        List<MilitaryFormation> militaryFormations = new ArrayList<>(militaryFormationRepository.findAll());
        return militaryFormationMapper.entitiesToDtos(militaryFormations);
    }

    public Page<MilitaryFormationDto> getAllMilitaryFormationsByFilterAsDtos(MilitaryFormationSearchFilter militaryFormationSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(militaryFormationSearchFilter.getPageNumber(),
                                           militaryFormationSearchFilter.getPageSize(),
                                           militaryFormationSearchFilter.getSortDirection(),
                                           militaryFormationSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = militaryFormationSearchFilter.getSearchName();
        String militaryFormationTypeIds = IterableToPostgresqlArrayConverter.convert(militaryFormationSearchFilter.getMilitaryFormationTypeIds());
        Page<MilitaryFormation> militaryFormations = militaryFormationRepository.findAllByFilter(searchName, militaryFormationTypeIds, pageable);
        if (militaryFormations.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation[].class,
                                              Map.of("militaryFormationSearchFilter", militaryFormationSearchFilter.toString()));
        }
        return militaryFormationMapper.entitiesToDtos(militaryFormations);
    }

    public MilitaryFormationDto getMilitaryFormationByIdAsDto(int id) throws EntityNotFoundException {
        Optional<MilitaryFormation> militaryFormation = militaryFormationRepository.findById(id);
        if (militaryFormation.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation.class, Map.of("id", String.valueOf(id)));
        }
        return militaryFormationMapper.entityToDto(militaryFormation.get());
    }

    public List<MilitaryFormationDto> getSubordinatesByIdAsDtos(int id) throws EntityNotFoundException {
        List<MilitaryFormation> subordinates = militaryFormationRepository.findAllSubordinatesById(id);
        if (subordinates.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation[].class, Map.of("militaryFormationId", String.valueOf(id)));
        }
        return militaryFormationMapper.entitiesToDtos(subordinates);
    }

    public List<DislocationDto> getDislocationsByIdAsDtos(int id) throws EntityNotFoundException {
        List<DislocationDto> dislocationDtos = dislocationService.getDislocationsByMilitaryFormationIdAsDtos(id);
        if (dislocationDtos.isEmpty()) {
            throw new EntityNotFoundException(Dislocation[].class, Map.of("militaryFormationId", String.valueOf(id)));
        }
        return dislocationDtos;
    }

    public List<MilitaryManDto> getMilitaryMenByIdAsDtos(int id) throws EntityNotFoundException {
        List<MilitaryManDto> militaryManDtos = militaryManService.getMilitaryManByMilitaryFormationIdAsDtos(id);
        if (militaryManDtos.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan[].class, Map.of("militaryFormationId", String.valueOf(id)));
        }
        return militaryManDtos;
    }

    public List<CombatVehicleDto> getCombatVehiclesByIdAsDtos(int id) throws EntityNotFoundException {
        List<CombatVehicleDto> combatVehicleDtos = combatVehicleService.getCombatVehiclesByMilitaryFormationIdAsDtos(id);
        if (combatVehicleDtos.isEmpty()) {
            throw new EntityNotFoundException(CombatVehicle[].class, Map.of("militaryFormationId", String.valueOf(id)));
        }
        return combatVehicleDtos;
    }

    public List<ArmamentDto> getArmamentsByIdAsDtos(int id) throws EntityNotFoundException {
        List<ArmamentDto> armamentDtos = armamentService.getArmamentsByMilitaryFormationIdAsDtos(id);
        if (armamentDtos.isEmpty()) {
            throw new EntityNotFoundException(Armament[].class, Map.of("militaryFormationId", String.valueOf(id)));
        }
        return armamentDtos;
    }

    public List<MilitaryBuildingDto> getMilitaryBuildingsByIdAsDtos(int id) throws EntityNotFoundException {
        List<MilitaryBuildingDto> militaryBuildingDtos = militaryBuildingService.getMilitaryBuildingsByMilitaryFormationIdAsDtos(id);
        if (militaryBuildingDtos.isEmpty()) {
            throw new EntityNotFoundException(MilitaryBuilding[].class, Map.of("militaryFormationId", String.valueOf(id)));
        }
        return militaryBuildingDtos;
    }

    public MilitaryFormation getMilitaryFormationByName(String name) throws EntityNotFoundException {
        Optional<MilitaryFormation> militaryFormation = militaryFormationRepository.findByName(name);
        if (militaryFormation.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation.class, Map.of("name", name));
        }
        return militaryFormation.get();
    }

    public MilitaryFormationDto createMilitaryFormation(MilitaryFormationDto militaryFormationDto) {
        Integer id = militaryFormationDto.getId();
        if (Objects.isNull(id)) {
            return militaryFormationMapper.entityToDto(saveMilitaryFormation(militaryFormationDto));
        }
        Optional<MilitaryFormation> militaryFormation = militaryFormationRepository.findById(id);
        if (militaryFormation.isPresent()) {
            throw new EntityAlreadyExistsException(MilitaryFormation.class, Map.of("id", String.valueOf(id)));
        }
        return militaryFormationMapper.entityToDto(saveMilitaryFormation(militaryFormationDto));
    }

    public MilitaryFormationDto updateMilitaryFormationById(int id, MilitaryFormationDto militaryFormationDto) throws EntityNotFoundException {
        Optional<MilitaryFormation> militaryFormation = militaryFormationRepository.findById(id);
        if (militaryFormation.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation.class, Map.of("id", String.valueOf(id)));
        }
        militaryFormationDto.setId(id);
        return militaryFormationMapper.entityToDto(saveMilitaryFormation(militaryFormationDto));
    }

    public MilitaryFormationDto deleteMilitaryFormationById(int id) throws EntityNotFoundException {
        Optional<MilitaryFormation> militaryFormation = militaryFormationRepository.findById(id);
        if (militaryFormation.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation.class, Map.of("id", String.valueOf(id)));
        }
        militaryFormationRepository.deleteById(id);
        return militaryFormationMapper.entityToDto(militaryFormation.get());
    }
}
