package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.StaffCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;

import java.util.List;

@Mapper
public interface StaffCategoryMapper {
    StaffCategoryMapper INSTANCE = Mappers.getMapper(StaffCategoryMapper.class);

    StaffCategoryDto staffCategoryToStaffCategoryDto(StaffCategory staffCategory);

    StaffCategory staffCategoryDtoToStaffCategory(StaffCategoryDto staffCategoryDto);

    List<StaffCategoryDto> staffCategoriesToStaffCategoryDtos(List<StaffCategory> staffCategories);

    List<StaffCategory> staffCategoryDtosToStaffCategories(List<StaffCategoryDto> staffCategoryDtos);

    default Page<StaffCategoryDto> staffCategoriesToStaffCategoryDtos(Page<StaffCategory> staffCategories) {
        return staffCategories.map(INSTANCE::staffCategoryToStaffCategoryDto);
    }

    default Page<StaffCategory> staffCategoryDtosToStaffCategories(Page<StaffCategoryDto> staffCategoryDtos) {
        return staffCategoryDtos.map(INSTANCE::staffCategoryDtoToStaffCategory);
    }
}
