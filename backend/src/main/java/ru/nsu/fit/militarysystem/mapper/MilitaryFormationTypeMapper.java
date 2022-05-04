package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationTypeDto;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormationType;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MilitaryFormationTypeMapper {
    public abstract MilitaryFormationTypeDto entityToDto(MilitaryFormationType militaryFormationType);

    public abstract MilitaryFormationType dtoToEntity(MilitaryFormationTypeDto militaryFormationTypeDto);

    public abstract List<MilitaryFormationTypeDto> entitiesToDtos(List<MilitaryFormationType> militaryFormationTypes);

    public abstract List<MilitaryFormationType> dtosToEntities(List<MilitaryFormationTypeDto> militaryFormationTypeDtos);

    public Page<MilitaryFormationTypeDto> entitiesToDtos(Page<MilitaryFormationType> militaryFormationTypes) {
        return militaryFormationTypes.map(this::entityToDto);
    }

    public Page<MilitaryFormationType> dtosToEntities(Page<MilitaryFormationTypeDto> militaryFormationTypeDtos) {
        return militaryFormationTypeDtos.map(this::dtoToEntity);
    }
}
