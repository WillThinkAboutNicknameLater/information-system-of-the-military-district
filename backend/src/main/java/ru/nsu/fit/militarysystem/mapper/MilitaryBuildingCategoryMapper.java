package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.MilitaryBuildingCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.MilitaryBuildingCategory;

@Mapper(componentModel = "spring")
public interface MilitaryBuildingCategoryMapper extends BaseMapper<MilitaryBuildingCategory, MilitaryBuildingCategoryDto> {
}
