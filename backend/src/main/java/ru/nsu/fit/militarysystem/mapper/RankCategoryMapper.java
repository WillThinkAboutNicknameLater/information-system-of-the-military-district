package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RankCategoryMapper {
    public abstract RankCategoryDto entityToDto(RankCategory rankCategory);

    public abstract RankCategory dtoToEntity(RankCategoryDto rankCategoryDto);

    public abstract List<RankCategoryDto> entitiesToDtos(List<RankCategory> rankCategories);

    public abstract List<RankCategory> dtosToEntities(List<RankCategoryDto> rankCategoryDtos);

    public Page<RankCategoryDto> entitiesToDtos(Page<RankCategory> rankCategories) {
        return rankCategories.map(this::entityToDto);
    }

    public Page<RankCategory> dtosToEntities(Page<RankCategoryDto> rankCategoryDtos) {
        return rankCategoryDtos.map(this::dtoToEntity);
    }
}
