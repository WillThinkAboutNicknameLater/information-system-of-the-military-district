package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.filter.MilitarySpecialtySearchFilter;
import ru.nsu.fit.militarysystem.mapper.MilitarySpecialtyMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;
import ru.nsu.fit.militarysystem.store.repository.MilitarySpecialtyRepository;

import java.util.*;

@Service
public class MilitarySpecialtyService {
    private final MilitarySpecialtyRepository militarySpecialtyRepository;

    private final MilitarySpecialtyMapper militarySpecialtyMapper;

    public MilitarySpecialtyService(MilitarySpecialtyRepository militarySpecialtyRepository, MilitarySpecialtyMapper militarySpecialtyMapper) {
        this.militarySpecialtyRepository = militarySpecialtyRepository;
        this.militarySpecialtyMapper = militarySpecialtyMapper;
    }

    private MilitarySpecialty saveMilitarySpecialty(MilitarySpecialtyDto militarySpecialtyDto) {
        MilitarySpecialty newMilitarySpecialty = militarySpecialtyMapper.dtoToEntity(militarySpecialtyDto);
        return militarySpecialtyRepository.save(newMilitarySpecialty);
    }

    public List<MilitarySpecialtyDto> getAllMilitarySpecialtiesAsDtos() {
        List<MilitarySpecialty> militarySpecialties = new ArrayList<>(militarySpecialtyRepository.findAll());
        return militarySpecialtyMapper.entitiesToDtos(militarySpecialties);
    }

    public Page<MilitarySpecialtyDto> getAllMilitarySpecialtiesByFilterAsDtos(MilitarySpecialtySearchFilter militarySpecialtySearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(militarySpecialtySearchFilter.getPageNumber(),
                                           militarySpecialtySearchFilter.getPageSize(),
                                           militarySpecialtySearchFilter.getSortDirection(),
                                           militarySpecialtySearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = militarySpecialtySearchFilter.getSearchName();
        Page<MilitarySpecialty> militarySpecialties = militarySpecialtyRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        if (militarySpecialties.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty[].class,
                                              Map.of("militarySpecialtySearchFilter", militarySpecialtySearchFilter.toString()));
        }

        return militarySpecialtyMapper.entitiesToDtos(militarySpecialties);
    }

    public MilitarySpecialtyDto getMilitarySpecialtyByIdAsDto(short id) throws EntityNotFoundException {
        Optional<MilitarySpecialty> militarySpecialty = militarySpecialtyRepository.findById(id);
        if (militarySpecialty.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty.class, Map.of("id", String.valueOf(id)));
        }
        return militarySpecialtyMapper.entityToDto(militarySpecialty.get());
    }

    public MilitarySpecialtyDto createMilitarySpecialty(MilitarySpecialtyDto militarySpecialtyDto) throws EntityAlreadyExistsException {
        Short id = militarySpecialtyDto.getId();
        if (Objects.isNull(id)) {
            return militarySpecialtyMapper.entityToDto(saveMilitarySpecialty(militarySpecialtyDto));
        }
        Optional<MilitarySpecialty> militarySpecialty = militarySpecialtyRepository.findById(id);
        if (militarySpecialty.isPresent()) {
            throw new EntityAlreadyExistsException(MilitarySpecialty.class, Map.of("id", String.valueOf(id)));
        }
        return militarySpecialtyMapper.entityToDto(saveMilitarySpecialty(militarySpecialtyDto));
    }

    public MilitarySpecialtyDto updateMilitarySpecialtyById(short id, MilitarySpecialtyDto militarySpecialtyDto) throws EntityNotFoundException {
        Optional<MilitarySpecialty> militarySpecialty = militarySpecialtyRepository.findById(id);
        if (militarySpecialty.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty.class, Map.of("id", String.valueOf(id)));
        }
        militarySpecialtyDto.setId(id);
        return militarySpecialtyMapper.entityToDto(saveMilitarySpecialty(militarySpecialtyDto));
    }

    public MilitarySpecialtyDto deleteMilitarySpecialtyById(short id) throws EntityNotFoundException {
        Optional<MilitarySpecialty> militarySpecialty = militarySpecialtyRepository.findById(id);
        if (militarySpecialty.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty.class, Map.of("id", String.valueOf(id)));
        }
        militarySpecialtyRepository.deleteById(id);
        return militarySpecialtyMapper.entityToDto(militarySpecialty.get());
    }
}
