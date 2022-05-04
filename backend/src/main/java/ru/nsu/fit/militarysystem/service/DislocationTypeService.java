package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.DislocationTypeDto;
import ru.nsu.fit.militarysystem.mapper.DislocationTypeMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.DislocationTypeSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.DislocationType;
import ru.nsu.fit.militarysystem.store.repository.DislocationTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DislocationTypeService {
    private final DislocationTypeRepository dislocationTypeRepository;

    private final DislocationTypeMapper dislocationTypeMapper;

    public DislocationTypeService(DislocationTypeRepository dislocationTypeRepository, DislocationTypeMapper dislocationTypeMapper) {
        this.dislocationTypeRepository = dislocationTypeRepository;
        this.dislocationTypeMapper = dislocationTypeMapper;
    }

    private DislocationType saveDislocationType(DislocationTypeDto dislocationTypeDto) {
        DislocationType newDislocationType = dislocationTypeMapper.dtoToEntity(dislocationTypeDto);
        return dislocationTypeRepository.save(newDislocationType);
    }

    public List<DislocationTypeDto> getAllDislocationTypesAsDtos() {
        List<DislocationType> dislocationTypes = new ArrayList<>(dislocationTypeRepository.findAll());
        return dislocationTypeMapper.entitiesToDtos(dislocationTypes);
    }

    public Page<DislocationTypeDto> getAllDislocationTypesByFilterAsDtos(DislocationTypeSearchFilter dislocationTypeSearchFilter) throws EntityNotFoundException {
        if (dislocationTypeSearchFilter == null) {
            dislocationTypeSearchFilter = new DislocationTypeSearchFilter();
        }

        PageCriteria pageCriteria = dislocationTypeSearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<DislocationType> dislocationTypes = dislocationTypeRepository.findAll(pageable);
        if (dislocationTypes.isEmpty()) {
            throw new EntityNotFoundException(DislocationType.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return dislocationTypeMapper.entitiesToDtos(dislocationTypes);
    }

    public DislocationTypeDto getDislocationTypeByIdAsDto(short id) throws EntityNotFoundException {
        Optional<DislocationType> dislocationType = dislocationTypeRepository.findById(id);
        if (dislocationType.isEmpty()) {
            throw new EntityNotFoundException(DislocationType.class, Map.of("id", String.valueOf(id)));
        }
        return dislocationTypeMapper.entityToDto(dislocationType.get());
    }

    public DislocationType getDislocationTypeByName(String name) throws EntityNotFoundException {
        Optional<DislocationType> dislocationType = dislocationTypeRepository.findByName(name);
        if (dislocationType.isEmpty()) {
            throw new EntityNotFoundException(DislocationType.class, Map.of("name", name));
        }
        return dislocationType.get();
    }

    public DislocationTypeDto createDislocationType(DislocationTypeDto dislocationTypeDto) {
        return dislocationTypeMapper.entityToDto(saveDislocationType(dislocationTypeDto));
    }

    public DislocationTypeDto updateDislocationTypeById(short id, DislocationTypeDto dislocationTypeDto) throws EntityNotFoundException {
        Optional<DislocationType> dislocationType = dislocationTypeRepository.findById(id);
        if (dislocationType.isEmpty()) {
            throw new EntityNotFoundException(DislocationType.class, Map.of("id", String.valueOf(id)));
        }
        dislocationTypeDto.setId(id);
        return dislocationTypeMapper.entityToDto(saveDislocationType(dislocationTypeDto));
    }

    public DislocationTypeDto deleteDislocationTypeById(short id) throws EntityNotFoundException {
        Optional<DislocationType> dislocationType = dislocationTypeRepository.findById(id);
        if (dislocationType.isEmpty()) {
            throw new EntityNotFoundException(DislocationType.class, Map.of("id", String.valueOf(id)));
        }
        dislocationTypeRepository.deleteById(id);
        return dislocationTypeMapper.entityToDto(dislocationType.get());
    }
}
