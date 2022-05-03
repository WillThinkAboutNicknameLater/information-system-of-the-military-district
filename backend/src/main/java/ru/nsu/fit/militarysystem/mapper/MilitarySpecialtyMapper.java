package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MilitarySpecialtyMapper {
    public abstract MilitarySpecialtyDto entityToDto(MilitarySpecialty militarySpecialty);

    public abstract MilitarySpecialty dtoToEntity(MilitarySpecialtyDto militarySpecialtyDto);

    public abstract List<MilitarySpecialtyDto> entitiesToDtos(List<MilitarySpecialty> militarySpecialties);

    public abstract List<MilitarySpecialty> dtosToEntities(List<MilitarySpecialtyDto> militarySpecialtyDtos);

    public Page<MilitarySpecialtyDto> entitiesToDtos(Page<MilitarySpecialty> militarySpecialties) {
        return militarySpecialties.map(this::entityToDto);
    }

    public Page<MilitarySpecialty> dtosToEntities(Page<MilitarySpecialtyDto> militarySpecialtyDtos) {
        return militarySpecialtyDtos.map(this::dtoToEntity);
    }
}
