package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationDto;
import ru.nsu.fit.militarysystem.dto.MilitaryManDto;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.filter.MilitaryManSearchFilter;
import ru.nsu.fit.militarysystem.mapper.MilitaryFormationMapper;
import ru.nsu.fit.militarysystem.mapper.MilitaryManMapper;
import ru.nsu.fit.militarysystem.mapper.MilitarySpecialtyMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormation;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;
import ru.nsu.fit.militarysystem.store.repository.MilitaryManRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class MilitaryManService {
    private final MilitaryManRepository militaryManRepository;

    private final MilitaryManMapper militaryManMapper;

    private final MilitarySpecialtyMapper militarySpecialtyMapper;

    private final MilitaryFormationMapper militaryFormationMapper;

    public MilitaryManService(MilitaryManRepository militaryManRepository,
                              MilitaryManMapper militaryManMapper,
                              MilitarySpecialtyMapper militarySpecialtyMapper,
                              MilitaryFormationMapper militaryFormationMapper) {
        this.militaryManRepository = militaryManRepository;
        this.militaryManMapper = militaryManMapper;
        this.militarySpecialtyMapper = militarySpecialtyMapper;
        this.militaryFormationMapper = militaryFormationMapper;
    }

    private MilitaryMan saveMilitaryMan(MilitaryManDto militaryManDto) {
        MilitaryMan newMilitaryMan = militaryManMapper.dtoToEntity(militaryManDto);
        return militaryManRepository.save(newMilitaryMan);
    }

    public List<MilitaryManDto> getAllMilitaryMenAsDtos() {
        List<MilitaryMan> militaryMen = new ArrayList<>(militaryManRepository.findAll());
        return militaryManMapper.entitiesToDtos(militaryMen);
    }

    public Page<MilitaryManDto> getAllMilitaryMenByFilterAsDtos(MilitaryManSearchFilter militaryManSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(militaryManSearchFilter.getPageNumber(),
                                           militaryManSearchFilter.getPageSize(),
                                           militaryManSearchFilter.getSortDirection(),
                                           militaryManSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = militaryManSearchFilter.getSearchName();
        String rankIds = IterableToPostgresqlArrayConverter.convert(militaryManSearchFilter.getRankIds());
        String rankCategoryIds = IterableToPostgresqlArrayConverter.convert(militaryManSearchFilter.getRankCategoryIds());
        String staffCategoryIds = IterableToPostgresqlArrayConverter.convert(militaryManSearchFilter.getStaffCategoryIds());
        String militarySpecialtyIds = IterableToPostgresqlArrayConverter.convert(militaryManSearchFilter.getMilitarySpecialtyIds());
        String militaryFormationIds = IterableToPostgresqlArrayConverter.convert(militaryManSearchFilter.getMilitaryFormationIds());
        Page<MilitaryMan> militaryMen =
                militaryManRepository.findAllByFilter(searchName, rankIds, rankCategoryIds, staffCategoryIds, militarySpecialtyIds,
                                                      militaryFormationIds, pageable);

        return militaryManMapper.entitiesToDtos(militaryMen);
    }

    public MilitaryManDto getMilitaryManByIdAsDto(int id) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }
        return militaryManMapper.entityToDto(militaryMan.get());
    }

    public MilitaryMan getMilitaryManByIdentificationNumber(String identificationNumber)
            throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findByIdentificationNumber(identificationNumber);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("identificationNumber", String.valueOf(identificationNumber)));
        }
        return militaryMan.get();
    }

    public List<MilitaryManDto> getMilitaryManByMilitaryFormationIdAsDtos(int militaryFormationId) throws EntityNotFoundException {
        List<MilitaryMan> militaryMen = militaryManRepository.findAllByMilitaryFormationId(militaryFormationId);
        if (militaryMen.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan[].class, Map.of("militaryFormationId", String.valueOf(militaryFormationId)));
        }
        return militaryManMapper.entitiesToDtos(militaryMen);
    }

    public List<MilitaryManDto> getSubordinatesByIdAsDtos(int id) throws EntityNotFoundException {
        List<MilitaryMan> subordinates = militaryManRepository.findAllSubordinatesById(id);
        if (subordinates.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan[].class, Map.of("militaryManId", String.valueOf(id)));
        }
        return militaryManMapper.entitiesToDtos(subordinates);
    }

    public List<MilitarySpecialtyDto> getMilitarySpecialtiesByIdAsDtos(int id) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }

        List<MilitarySpecialty> militarySpecialties = new ArrayList<>(militaryMan.get().getMilitarySpecialties());
        militarySpecialties.sort(Comparator.comparing(MilitarySpecialty::getId));
        if (militarySpecialties.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty[].class, Map.of("militaryManId", String.valueOf(id)));
        }
        return militarySpecialtyMapper.entitiesToDtos(militarySpecialties);
    }

    public List<MilitaryFormationDto> getMilitaryFormationsByIdAsDtos(int id) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }

        List<MilitaryFormation> militaryFormations = new ArrayList<>(militaryMan.get().getMilitaryFormations());
        militaryFormations.sort(Comparator.comparing(MilitaryFormation::getId));
        if (militaryFormations.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormation[].class, Map.of("militaryManId", String.valueOf(id)));
        }
        return militaryFormationMapper.entitiesToDtos(militaryFormations);
    }

    public MilitaryManDto createMilitaryMan(MilitaryManDto militaryManDto) throws EntityAlreadyExistsException {
        Integer id = militaryManDto.getId();
        if (Objects.isNull(id)) {
            return militaryManMapper.entityToDto(saveMilitaryMan(militaryManDto));
        }
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isPresent()) {
            throw new EntityAlreadyExistsException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }
        return militaryManMapper.entityToDto(saveMilitaryMan(militaryManDto));
    }

    public MilitaryManDto updateMilitaryManById(int id, MilitaryManDto militaryManDto) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }
        militaryManDto.setId(id);
        return militaryManMapper.entityToDto(saveMilitaryMan(militaryManDto));
    }

    public MilitaryManDto deleteMilitaryManById(int id) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }
        militaryManRepository.deleteById(id);
        return militaryManMapper.entityToDto(militaryMan.get());
    }
}
