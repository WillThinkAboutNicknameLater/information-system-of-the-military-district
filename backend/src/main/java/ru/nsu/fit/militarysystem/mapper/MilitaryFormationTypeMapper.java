package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationTypeDto;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormationType;

@Mapper(componentModel = "spring")
public interface MilitaryFormationTypeMapper extends BaseMapper<MilitaryFormationType, MilitaryFormationTypeDto> {
}
