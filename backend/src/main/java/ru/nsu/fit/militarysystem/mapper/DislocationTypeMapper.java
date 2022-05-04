package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.DislocationTypeDto;
import ru.nsu.fit.militarysystem.store.entity.DislocationType;

@Mapper(componentModel = "spring")
public interface DislocationTypeMapper extends BaseMapper<DislocationType, DislocationTypeDto> {
}
