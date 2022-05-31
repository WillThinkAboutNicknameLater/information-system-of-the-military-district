package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryDistrictDto;
import ru.nsu.fit.militarysystem.filter.MilitaryDistrictSearchFilter;
import ru.nsu.fit.militarysystem.mapper.MilitaryDistrictMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.MilitaryDistrict;
import ru.nsu.fit.militarysystem.store.repository.MilitaryDistrictRepository;

import java.util.*;

@Service
public class MilitaryDistrictService {
    private final MilitaryDistrictRepository militaryDistrictRepository;

    private final MilitaryDistrictMapper militaryDistrictMapper;

    public MilitaryDistrictService(MilitaryDistrictRepository militaryDistrictRepository, MilitaryDistrictMapper militaryDistrictMapper) {
        this.militaryDistrictRepository = militaryDistrictRepository;
        this.militaryDistrictMapper = militaryDistrictMapper;
    }

    private MilitaryDistrict saveMilitaryDistrict(MilitaryDistrictDto militaryDistrictDto) {
        MilitaryDistrict newMilitaryDistrict = militaryDistrictMapper.dtoToEntity(militaryDistrictDto);
        return militaryDistrictRepository.save(newMilitaryDistrict);
    }

    public List<MilitaryDistrictDto> getAllMilitaryDistrictsAsDtos() {
        List<MilitaryDistrict> militaryDistricts = new ArrayList<>(militaryDistrictRepository.findAll());
        return militaryDistrictMapper.entitiesToDtos(militaryDistricts);
    }

    public Page<MilitaryDistrictDto> getAllMilitaryDistrictsByFilterAsDtos(MilitaryDistrictSearchFilter militaryDistrictSearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(militaryDistrictSearchFilter.getPageNumber(),
                                           militaryDistrictSearchFilter.getPageSize(),
                                           militaryDistrictSearchFilter.getSortDirection(),
                                           militaryDistrictSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = militaryDistrictSearchFilter.getSearchName();
        Page<MilitaryDistrict> militaryDistricts = militaryDistrictRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        return militaryDistrictMapper.entitiesToDtos(militaryDistricts);
    }

    public MilitaryDistrictDto getMilitaryDistrictByIdAsDto(short id) throws EntityNotFoundException {
        Optional<MilitaryDistrict> militaryDistrict = militaryDistrictRepository.findById(id);
        if (militaryDistrict.isEmpty()) {
            throw new EntityNotFoundException(MilitaryDistrict.class, Map.of("id", String.valueOf(id)));
        }
        return militaryDistrictMapper.entityToDto(militaryDistrict.get());
    }

    public MilitaryDistrictDto createMilitaryDistrict(MilitaryDistrictDto militaryDistrictDto) throws EntityAlreadyExistsException {
        Short id = militaryDistrictDto.getId();
        if (Objects.isNull(id)) {
            return militaryDistrictMapper.entityToDto(saveMilitaryDistrict(militaryDistrictDto));
        }
        Optional<MilitaryDistrict> militaryDistrict = militaryDistrictRepository.findById(id);
        if (militaryDistrict.isPresent()) {
            throw new EntityAlreadyExistsException(MilitaryDistrict.class, Map.of("id", String.valueOf(id)));
        }
        return militaryDistrictMapper.entityToDto(saveMilitaryDistrict(militaryDistrictDto));
    }

    public MilitaryDistrictDto updateMilitaryDistrictById(short id, MilitaryDistrictDto militaryDistrictDto) throws EntityNotFoundException {
        Optional<MilitaryDistrict> militaryDistrict = militaryDistrictRepository.findById(id);
        if (militaryDistrict.isEmpty()) {
            throw new EntityNotFoundException(MilitaryDistrict.class, Map.of("id", String.valueOf(id)));
        }
        militaryDistrictDto.setId(id);
        return militaryDistrictMapper.entityToDto(saveMilitaryDistrict(militaryDistrictDto));
    }

    public MilitaryDistrictDto deleteMilitaryDistrictById(short id) throws EntityNotFoundException {
        Optional<MilitaryDistrict> militaryDistrict = militaryDistrictRepository.findById(id);
        if (militaryDistrict.isEmpty()) {
            throw new EntityNotFoundException(MilitaryDistrict.class, Map.of("id", String.valueOf(id)));
        }
        militaryDistrictRepository.deleteById(id);
        return militaryDistrictMapper.entityToDto(militaryDistrict.get());
    }
}
