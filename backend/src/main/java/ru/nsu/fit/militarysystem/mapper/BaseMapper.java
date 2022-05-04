package ru.nsu.fit.militarysystem.mapper;

import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.BaseDto;
import ru.nsu.fit.militarysystem.store.entity.BaseEntity;

import java.util.List;

public interface BaseMapper<T1 extends BaseEntity, T2 extends BaseDto> {
    T2 entityToDto(T1 entity);

    T1 dtoToEntity(T2 dto);

    List<T2> entitiesToDtos(List<T1> entities);

    List<T1> dtosToEntities(List<T2> dtos);

    default Page<T2> entitiesToDtos(Page<T1> entities) {
        return entities.map(this::entityToDto);
    }

    default Page<T1> dtosToEntities(Page<T2> dtos) {
        return dtos.map(this::dtoToEntity);
    }
}
