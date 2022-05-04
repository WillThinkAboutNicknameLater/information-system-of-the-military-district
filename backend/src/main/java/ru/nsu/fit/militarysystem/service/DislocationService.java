package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.DislocationDto;
import ru.nsu.fit.militarysystem.mapper.DislocationMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.DislocationSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.Dislocation;
import ru.nsu.fit.militarysystem.store.repository.DislocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DislocationService {
    private final DislocationRepository dislocationRepository;

    private final DislocationMapper dislocationMapper;

    public DislocationService(DislocationRepository dislocationRepository, DislocationMapper dislocationMapper) {
        this.dislocationRepository = dislocationRepository;
        this.dislocationMapper = dislocationMapper;
    }

    private Dislocation saveDislocation(DislocationDto dislocationDto) {
        Dislocation newDislocation = dislocationMapper.dtoToEntity(dislocationDto);
        return dislocationRepository.save(newDislocation);
    }

    public List<DislocationDto> getAllDislocationsAsDtos() {
        List<Dislocation> dislocations = new ArrayList<>(dislocationRepository.findAll());
        return dislocationMapper.entitiesToDtos(dislocations);
    }

    public Page<DislocationDto> getAllDislocationsByFilterAsDtos(DislocationSearchFilter dislocationSearchFilter) throws EntityNotFoundException {
        if (dislocationSearchFilter == null) {
            dislocationSearchFilter = new DislocationSearchFilter();
        }

        PageCriteria pageCriteria = dislocationSearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<Dislocation> dislocations = dislocationRepository.findAll(pageable);
        if (dislocations.isEmpty()) {
            throw new EntityNotFoundException(Dislocation.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return dislocationMapper.entitiesToDtos(dislocations);
    }

    public DislocationDto getDislocationByIdAsDto(int id) throws EntityNotFoundException {
        Optional<Dislocation> dislocation = dislocationRepository.findById(id);
        if (dislocation.isEmpty()) {
            throw new EntityNotFoundException(Dislocation.class, Map.of("id", String.valueOf(id)));
        }
        return dislocationMapper.entityToDto(dislocation.get());
    }

    public DislocationDto createDislocation(DislocationDto dislocationDto) {
        return dislocationMapper.entityToDto(saveDislocation(dislocationDto));
    }

    public DislocationDto updateDislocationById(int id, DislocationDto dislocationDto) throws EntityNotFoundException {
        Optional<Dislocation> dislocation = dislocationRepository.findById(id);
        if (dislocation.isEmpty()) {
            throw new EntityNotFoundException(Dislocation.class, Map.of("id", String.valueOf(id)));
        }
        dislocationDto.setId(id);
        return dislocationMapper.entityToDto(saveDislocation(dislocationDto));
    }

    public DislocationDto deleteDislocationById(int id) throws EntityNotFoundException {
        Optional<Dislocation> dislocation = dislocationRepository.findById(id);
        if (dislocation.isEmpty()) {
            throw new EntityNotFoundException(Dislocation.class, Map.of("id", String.valueOf(id)));
        }
        dislocationRepository.deleteById(id);
        return dislocationMapper.entityToDto(dislocation.get());
    }
}
