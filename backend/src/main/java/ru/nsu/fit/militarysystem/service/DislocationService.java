package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.DislocationDto;
import ru.nsu.fit.militarysystem.filter.DislocationSearchFilter;
import ru.nsu.fit.militarysystem.mapper.DislocationMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.Dislocation;
import ru.nsu.fit.militarysystem.store.repository.DislocationRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

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

    public Page<DislocationDto> getAllDislocationsByFilterAsDtos(DislocationSearchFilter dislocationSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(dislocationSearchFilter.getPageNumber(),
                                           dislocationSearchFilter.getPageSize(),
                                           dislocationSearchFilter.getSortDirection(),
                                           dislocationSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = dislocationSearchFilter.getSearchName();
        String dislocationTypeIds = IterableToPostgresqlArrayConverter.convert(dislocationSearchFilter.getDislocationTypeIds());
        String subjectIds = IterableToPostgresqlArrayConverter.convert(dislocationSearchFilter.getSubjectIds());
        Page<Dislocation> dislocations = dislocationRepository.findAllByFilter(searchName, dislocationTypeIds, subjectIds, pageable);

        if (dislocations.isEmpty()) {
            throw new EntityNotFoundException(Dislocation[].class, Map.of("dislocationSearchFilter", dislocationSearchFilter.toString()));
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

    public Dislocation getDislocationByOkato(String okato) throws EntityNotFoundException {
        Optional<Dislocation> dislocation = dislocationRepository.findByOkato(okato);
        if (dislocation.isEmpty()) {
            throw new EntityNotFoundException(Dislocation.class, Map.of("okato", String.valueOf(okato)));
        }
        return dislocation.get();
    }

    public List<DislocationDto> getDislocationsByMilitaryFormationIdAsDtos(int militaryFormationId) throws EntityNotFoundException {
        List<Dislocation> dislocations = dislocationRepository.findAllByMilitaryFormationId(militaryFormationId);
        if (dislocations.isEmpty()) {
            throw new EntityNotFoundException(Dislocation[].class, Map.of("militaryFormationId", String.valueOf(militaryFormationId)));
        }
        return dislocationMapper.entitiesToDtos(dislocations);
    }

    public DislocationDto createDislocation(DislocationDto dislocationDto) throws EntityAlreadyExistsException {
        Integer id = dislocationDto.getId();
        if (Objects.isNull(id)) {
            return dislocationMapper.entityToDto(saveDislocation(dislocationDto));
        }
        Optional<Dislocation> dislocation = dislocationRepository.findById(id);
        if (dislocation.isPresent()) {
            throw new EntityAlreadyExistsException(Dislocation.class, Map.of("id", String.valueOf(id)));
        }
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