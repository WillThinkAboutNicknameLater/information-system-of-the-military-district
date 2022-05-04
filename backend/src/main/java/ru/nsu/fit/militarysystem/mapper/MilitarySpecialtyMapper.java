package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;

@Mapper(componentModel = "spring")
public interface MilitarySpecialtyMapper extends BaseMapper<MilitarySpecialty, MilitarySpecialtyDto> {
}
