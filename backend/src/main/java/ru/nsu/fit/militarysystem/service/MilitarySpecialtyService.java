package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.mapper.MilitarySpecialtyMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.MilitarySpecialtySearchFilter;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;
import ru.nsu.fit.militarysystem.store.repository.MilitarySpecialtyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Page<MilitarySpecialtyDto> getAllMilitarySpecialtiesByFilterAsDtos(MilitarySpecialtySearchFilter militarySpecialtySearchFilter) throws EntityNotFoundException {
        if (militarySpecialtySearchFilter == null) {
            militarySpecialtySearchFilter = new MilitarySpecialtySearchFilter();
        }

        PageCriteria pageCriteria = militarySpecialtySearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<MilitarySpecialty> militarySpecialties = militarySpecialtyRepository.findAll(pageable);
        if (militarySpecialties.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty.class, Map.of("pageCriteria", pageCriteria.toString()));
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

    public MilitarySpecialty getMilitarySpecialtyByName(String name) throws EntityNotFoundException {
        Optional<MilitarySpecialty> militarySpecialty = militarySpecialtyRepository.findByName(name);
        if (militarySpecialty.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty.class, Map.of("name", name));
        }
        return militarySpecialty.get();
    }

    public MilitarySpecialtyDto createMilitarySpecialty(MilitarySpecialtyDto militarySpecialtyDto) {
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
