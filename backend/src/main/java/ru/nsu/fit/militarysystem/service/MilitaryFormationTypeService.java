package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationTypeDto;
import ru.nsu.fit.militarysystem.mapper.MilitaryFormationTypeMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.MilitaryFormationTypeSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormationType;
import ru.nsu.fit.militarysystem.store.repository.MilitaryFormationTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MilitaryFormationTypeService {
    private final MilitaryFormationTypeRepository militaryFormationTypeRepository;

    private final MilitaryFormationTypeMapper militaryFormationTypeMapper;

    public MilitaryFormationTypeService(MilitaryFormationTypeRepository militaryFormationTypeRepository, MilitaryFormationTypeMapper militaryFormationTypeMapper) {
        this.militaryFormationTypeRepository = militaryFormationTypeRepository;
        this.militaryFormationTypeMapper = militaryFormationTypeMapper;
    }

    private MilitaryFormationType saveMilitaryFormationType(MilitaryFormationTypeDto militaryFormationTypeDto) {
        MilitaryFormationType newMilitaryFormationType = militaryFormationTypeMapper.dtoToEntity(militaryFormationTypeDto);
        return militaryFormationTypeRepository.save(newMilitaryFormationType);
    }

    public List<MilitaryFormationTypeDto> getAllMilitaryFormationTypesAsDtos() {
        List<MilitaryFormationType> militaryFormationTypes = new ArrayList<>(militaryFormationTypeRepository.findAll());
        return militaryFormationTypeMapper.entitiesToDtos(militaryFormationTypes);
    }

    public Page<MilitaryFormationTypeDto> getAllMilitaryFormationTypesByFilterAsDtos(MilitaryFormationTypeSearchFilter militaryFormationTypeSearchFilter) throws EntityNotFoundException {
        if (militaryFormationTypeSearchFilter == null) {
            militaryFormationTypeSearchFilter = new MilitaryFormationTypeSearchFilter();
        }

        PageCriteria pageCriteria = militaryFormationTypeSearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<MilitaryFormationType> militaryFormationTypes = militaryFormationTypeRepository.findAll(pageable);
        if (militaryFormationTypes.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormationType.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return militaryFormationTypeMapper.entitiesToDtos(militaryFormationTypes);
    }

    public MilitaryFormationTypeDto getMilitaryFormationTypeByIdAsDto(short id) throws EntityNotFoundException {
        Optional<MilitaryFormationType> militaryFormationType = militaryFormationTypeRepository.findById(id);
        if (militaryFormationType.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormationType.class, Map.of("id", String.valueOf(id)));
        }
        return militaryFormationTypeMapper.entityToDto(militaryFormationType.get());
    }

    public MilitaryFormationType getMilitaryFormationTypeByName(String name) throws EntityNotFoundException {
        Optional<MilitaryFormationType> militaryFormationType = militaryFormationTypeRepository.findByName(name);
        if (militaryFormationType.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormationType.class, Map.of("name", name));
        }
        return militaryFormationType.get();
    }

    public MilitaryFormationTypeDto createMilitaryFormationType(MilitaryFormationTypeDto militaryFormationTypeDto) {
        return militaryFormationTypeMapper.entityToDto(saveMilitaryFormationType(militaryFormationTypeDto));
    }

    public MilitaryFormationTypeDto updateMilitaryFormationTypeById(short id, MilitaryFormationTypeDto militaryFormationTypeDto) throws EntityNotFoundException {
        Optional<MilitaryFormationType> militaryFormationType = militaryFormationTypeRepository.findById(id);
        if (militaryFormationType.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormationType.class, Map.of("id", String.valueOf(id)));
        }
        militaryFormationTypeDto.setId(id);
        return militaryFormationTypeMapper.entityToDto(saveMilitaryFormationType(militaryFormationTypeDto));
    }

    public MilitaryFormationTypeDto deleteMilitaryFormationTypeById(short id) throws EntityNotFoundException {
        Optional<MilitaryFormationType> militaryFormationType = militaryFormationTypeRepository.findById(id);
        if (militaryFormationType.isEmpty()) {
            throw new EntityNotFoundException(MilitaryFormationType.class, Map.of("id", String.valueOf(id)));
        }
        militaryFormationTypeRepository.deleteById(id);
        return militaryFormationTypeMapper.entityToDto(militaryFormationType.get());
    }
}
