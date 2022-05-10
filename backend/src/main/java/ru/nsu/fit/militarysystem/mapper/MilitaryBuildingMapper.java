package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.nsu.fit.militarysystem.dto.MilitaryBuildingDto;
import ru.nsu.fit.militarysystem.service.MilitaryBuildingCategoryService;
import ru.nsu.fit.militarysystem.service.MilitaryFormationService;
import ru.nsu.fit.militarysystem.store.entity.MilitaryBuilding;

@Mapper(componentModel = "spring")
public abstract class MilitaryBuildingMapper implements BaseMapper<MilitaryBuilding, MilitaryBuildingDto> {
    @Autowired
    protected MilitaryBuildingCategoryService militaryBuildingCategoryService;

    @Lazy
    @Autowired
    protected MilitaryFormationService militaryFormationService;

    @Override
    @Mapping(source = "militaryBuildingCategory.name", target = "militaryBuildingCategoryName")
    @Mapping(source = "militaryFormation.name", target = "militaryFormationName")
    public abstract MilitaryBuildingDto entityToDto(MilitaryBuilding entity);

    @Override
    @Mapping(target = "militaryBuildingCategory",
             expression = "java(militaryBuildingCategoryService.getMilitaryBuildingCategoryByName(dto.getMilitaryBuildingCategoryName()))")
    @Mapping(target = "militaryFormation",
             expression = "java(militaryFormationService.getMilitaryFormationByName(dto.getMilitaryFormationName()))")
    public abstract MilitaryBuilding dtoToEntity(MilitaryBuildingDto dto);
}
