package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;

@Mapper(componentModel = "spring")
public interface RankCategoryMapper extends BaseMapper<RankCategory, RankCategoryDto> {
}
