package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.StaffCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StaffCategoryMapper {
    public abstract StaffCategoryDto entityToDto(StaffCategory staffCategory);

    public abstract StaffCategory dtoToEntity(StaffCategoryDto staffCategoryDto);

    public abstract List<StaffCategoryDto> entitiesToDtos(List<StaffCategory> staffCategories);

    public abstract List<StaffCategory> dtosToEntities(List<StaffCategoryDto> staffCategoryDtos);

    public Page<StaffCategoryDto> entitiesToDtos(Page<StaffCategory> staffCategories) {
        return staffCategories.map(this::entityToDto);
    }

    public Page<StaffCategory> dtosToEntities(Page<StaffCategoryDto> staffCategoryDtos) {
        return staffCategoryDtos.map(this::dtoToEntity);
    }
}
