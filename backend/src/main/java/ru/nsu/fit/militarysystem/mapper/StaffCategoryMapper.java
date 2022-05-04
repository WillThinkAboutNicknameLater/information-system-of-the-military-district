package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.StaffCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;

@Mapper(componentModel = "spring")
public interface StaffCategoryMapper extends BaseMapper<StaffCategory, StaffCategoryDto> {
}
