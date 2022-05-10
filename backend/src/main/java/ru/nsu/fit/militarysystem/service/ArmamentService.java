package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.ArmamentDto;
import ru.nsu.fit.militarysystem.filter.ArmamentSearchFilter;
import ru.nsu.fit.militarysystem.mapper.ArmamentMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.Armament;
import ru.nsu.fit.militarysystem.store.repository.ArmamentRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class ArmamentService {
    private final ArmamentRepository armamentRepository;

    private final ArmamentMapper armamentMapper;

    public ArmamentService(ArmamentRepository armamentRepository, ArmamentMapper armamentMapper) {
        this.armamentRepository = armamentRepository;
        this.armamentMapper = armamentMapper;
    }

    private Armament saveArmament(ArmamentDto armamentDto) {
        Armament newArmament = armamentMapper.dtoToEntity(armamentDto);
        return armamentRepository.save(newArmament);
    }

    public List<ArmamentDto> getAllArmamentsAsDtos() {
        List<Armament> armaments = new ArrayList<>(armamentRepository.findAll());
        return armamentMapper.entitiesToDtos(armaments);
    }

    public Page<ArmamentDto> getAllArmamentsByFilterAsDtos(ArmamentSearchFilter armamentSearchFilter) throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(armamentSearchFilter.getPageNumber(),
                                           armamentSearchFilter.getPageSize(),
                                           armamentSearchFilter.getSortDirection(),
                                           armamentSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = armamentSearchFilter.getSearchName();
        String armamentCategoryIds = IterableToPostgresqlArrayConverter.convert(armamentSearchFilter.getArmamentCategoryIds());
        String militaryFormationIds = IterableToPostgresqlArrayConverter.convert(armamentSearchFilter.getMilitaryFormationIds());
        Page<Armament> armaments = armamentRepository.findAllByFilter(searchName, armamentCategoryIds, militaryFormationIds, pageable);

        if (armaments.isEmpty()) {
            throw new EntityNotFoundException(Armament[].class,
                                              Map.of("armamentSearchFilter", armamentSearchFilter.toString()));
        }

        return armamentMapper.entitiesToDtos(armaments);
    }

    public ArmamentDto getArmamentByIdAsDto(int id) throws EntityNotFoundException {
        Optional<Armament> armament = armamentRepository.findById(id);
        if (armament.isEmpty()) {
            throw new EntityNotFoundException(Armament.class, Map.of("id", String.valueOf(id)));
        }
        return armamentMapper.entityToDto(armament.get());
    }

    public List<ArmamentDto> getArmamentsByMilitaryFormationIdAsDtos(int militaryFormationId) throws EntityNotFoundException {
        List<Armament> armaments = armamentRepository.findAllByMilitaryFormationId(militaryFormationId);
        if (armaments.isEmpty()) {
            throw new EntityNotFoundException(Armament[].class, Map.of("militaryFormationId", String.valueOf(militaryFormationId)));
        }
        return armamentMapper.entitiesToDtos(armaments);
    }

    public ArmamentDto createArmament(ArmamentDto armamentDto) throws EntityAlreadyExistsException {
        Integer id = armamentDto.getId();
        if (Objects.isNull(id)) {
            return armamentMapper.entityToDto(saveArmament(armamentDto));
        }
        Optional<Armament> armament = armamentRepository.findById(id);
        if (armament.isPresent()) {
            throw new EntityAlreadyExistsException(Armament.class, Map.of("id", String.valueOf(id)));
        }
        return armamentMapper.entityToDto(saveArmament(armamentDto));
    }

    public ArmamentDto updateArmamentById(int id, ArmamentDto armamentDto) throws EntityNotFoundException {
        Optional<Armament> armament = armamentRepository.findById(id);
        if (armament.isEmpty()) {
            throw new EntityNotFoundException(Armament.class, Map.of("id", String.valueOf(id)));
        }
        armamentDto.setId(id);
        return armamentMapper.entityToDto(saveArmament(armamentDto));
    }

    public ArmamentDto deleteArmamentById(int id) throws EntityNotFoundException {
        Optional<Armament> armament = armamentRepository.findById(id);
        if (armament.isEmpty()) {
            throw new EntityNotFoundException(Armament.class, Map.of("id", String.valueOf(id)));
        }
        armamentRepository.deleteById(id);
        return armamentMapper.entityToDto(armament.get());
    }
}
