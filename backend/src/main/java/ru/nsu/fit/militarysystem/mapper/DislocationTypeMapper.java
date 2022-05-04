package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.DislocationTypeDto;
import ru.nsu.fit.militarysystem.store.entity.DislocationType;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DislocationTypeMapper {
    public abstract DislocationTypeDto entityToDto(DislocationType dislocationType);

    public abstract DislocationType dtoToEntity(DislocationTypeDto dislocationTypeDto);

    public abstract List<DislocationTypeDto> entitiesToDtos(List<DislocationType> dislocationTypes);

    public abstract List<DislocationType> dtosToEntities(List<DislocationTypeDto> dislocationTypeDtos);

    public Page<DislocationTypeDto> entitiesToDtos(Page<DislocationType> dislocationTypes) {
        return dislocationTypes.map(this::entityToDto);
    }

    public Page<DislocationType> dtosToEntities(Page<DislocationTypeDto> dislocationTypeDtos) {
        return dislocationTypeDtos.map(this::dtoToEntity);
    }
}
