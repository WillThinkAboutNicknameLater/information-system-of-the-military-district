package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.MilitaryManDto;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.mapper.MilitaryManMapper;
import ru.nsu.fit.militarysystem.mapper.MilitarySpecialtyMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.MilitaryManSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;
import ru.nsu.fit.militarysystem.store.repository.MilitaryManCriteriaRepository;
import ru.nsu.fit.militarysystem.store.repository.MilitaryManRepository;

import java.util.*;

@Service
public class MilitaryManService {
    private final MilitaryManRepository militaryManRepository;

    private final MilitaryManCriteriaRepository militaryManCriteriaRepository;

    private final MilitaryManMapper militaryManMapper;

    private final MilitarySpecialtyMapper militarySpecialtyMapper;

    public MilitaryManService(MilitaryManRepository militaryManRepository, MilitaryManCriteriaRepository militaryManCriteriaRepository, MilitaryManMapper militaryManMapper, MilitarySpecialtyMapper militarySpecialtyMapper) {
        this.militaryManRepository = militaryManRepository;
        this.militaryManCriteriaRepository = militaryManCriteriaRepository;
        this.militaryManMapper = militaryManMapper;
        this.militarySpecialtyMapper = militarySpecialtyMapper;
    }

    private MilitaryMan saveMilitaryMan(MilitaryManDto militaryManDto) {
        MilitaryMan newMilitaryMan = militaryManMapper.dtoToEntity(militaryManDto);
        return militaryManRepository.save(newMilitaryMan);
    }

    public List<MilitaryManDto> getAllMilitaryMenAsDtos() {
        List<MilitaryMan> militaryMen = new ArrayList<>(militaryManRepository.findAll());
        return militaryManMapper.entitiesToDtos(militaryMen);
    }

    public Page<MilitaryManDto> getAllMilitaryMenByFilterAsDtos(MilitaryManSearchFilter militaryManSearchFilter) throws EntityNotFoundException {
        if (militaryManSearchFilter == null) {
            militaryManSearchFilter = new MilitaryManSearchFilter();
        }

        Page<MilitaryMan> militaryMen = militaryManCriteriaRepository.findAllWithFilters(militaryManSearchFilter);
        if (militaryMen.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("pageCriteria", militaryManSearchFilter.getPageCriteria().toString(), "militaryManCriteria", militaryManSearchFilter.getMilitaryManCriteria().toString()));
        }
        return militaryManMapper.entitiesToDtos(militaryMen);
    }

    public MilitaryManDto getMilitaryManByIdAsDto(int id) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }
        return militaryManMapper.entityToDto(militaryMan.get());
    }

    public List<MilitarySpecialtyDto> getMilitaryManSpecialtiesByIdAsDtos(int id) throws EntityNotFoundException {
        Optional<MilitaryMan> militaryMan = militaryManRepository.findById(id);
        if (militaryMan.isEmpty()) {
            throw new EntityNotFoundException(MilitaryMan.class, Map.of("id", String.valueOf(id)));
        }

        List<MilitarySpecialty> militarySpecialties = new ArrayList<>(militaryMan.get().getMilitarySpecialties());
        militarySpecialties.sort(Comparator.comparing(MilitarySpecialty::getId));
        if (militarySpecialties.isEmpty()) {
            throw new EntityNotFoundException(MilitarySpecialty.class, Map.of("military man id", String.valueOf(id)));
        }
        return militarySpecialtyMapper.entitiesToDtos(militarySpecialties);
    }

    public MilitaryManDto createMilitaryMan(MilitaryManDto militaryManDto) {
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

