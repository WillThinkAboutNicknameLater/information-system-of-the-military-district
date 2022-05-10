package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.ArmamentCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.ArmamentCategory;

@Mapper(componentModel = "spring")
public interface ArmamentCategoryMapper extends BaseMapper<ArmamentCategory, ArmamentCategoryDto> {
}
