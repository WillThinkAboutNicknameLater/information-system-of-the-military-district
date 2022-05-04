package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryDistrictDto;
import ru.nsu.fit.militarysystem.mapper.MilitaryDistrictMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.MilitaryDistrictSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.MilitaryDistrict;
import ru.nsu.fit.militarysystem.store.repository.MilitaryDistrictRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Page<MilitaryDistrictDto> getAllMilitaryDistrictsByFilterAsDtos(MilitaryDistrictSearchFilter militaryDistrictSearchFilter) throws EntityNotFoundException {
        if (militaryDistrictSearchFilter == null) {
            militaryDistrictSearchFilter = new MilitaryDistrictSearchFilter();
        }

        PageCriteria pageCriteria = militaryDistrictSearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<MilitaryDistrict> militaryDistricts = militaryDistrictRepository.findAll(pageable);
        if (militaryDistricts.isEmpty()) {
            throw new EntityNotFoundException(MilitaryDistrict.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return militaryDistrictMapper.entitiesToDtos(militaryDistricts);
    }

    public MilitaryDistrictDto getMilitaryDistrictByIdAsDto(short id) throws EntityNotFoundException {
        Optional<MilitaryDistrict> militaryDistrict = militaryDistrictRepository.findById(id);
        if (militaryDistrict.isEmpty()) {
            throw new EntityNotFoundException(MilitaryDistrict.class, Map.of("id", String.valueOf(id)));
        }
        return militaryDistrictMapper.entityToDto(militaryDistrict.get());
    }

    public MilitaryDistrict getMilitaryDistrictByName(String name) throws EntityNotFoundException {
        Optional<MilitaryDistrict> militaryDistrict = militaryDistrictRepository.findByName(name);
        if (militaryDistrict.isEmpty()) {
            throw new EntityNotFoundException(MilitaryDistrict.class, Map.of("name", name));
        }
        return militaryDistrict.get();
    }

    public MilitaryDistrictDto createMilitaryDistrict(MilitaryDistrictDto militaryDistrictDto) {
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
