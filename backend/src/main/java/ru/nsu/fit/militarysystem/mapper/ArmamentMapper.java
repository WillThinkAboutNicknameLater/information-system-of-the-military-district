package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.nsu.fit.militarysystem.dto.ArmamentDto;
import ru.nsu.fit.militarysystem.service.ArmamentCategoryService;
import ru.nsu.fit.militarysystem.service.MilitaryFormationService;
import ru.nsu.fit.militarysystem.store.entity.Armament;

@Mapper(componentModel = "spring")
public abstract class ArmamentMapper implements BaseMapper<Armament, ArmamentDto> {
    @Autowired
    protected ArmamentCategoryService armamentCategoryService;

    @Lazy
    @Autowired
    protected MilitaryFormationService militaryFormationService;

    @Override
    @Mapping(source = "armamentCategory.name", target = "armamentCategoryName")
    @Mapping(source = "militaryFormation.name", target = "militaryFormationName")
    public abstract ArmamentDto entityToDto(Armament entity);

    @Override
    @Mapping(target = "armamentCategory",
             expression = "java(armamentCategoryService.getArmamentCategoryByName(dto.getArmamentCategoryName()))")
    @Mapping(target = "militaryFormation",
             expression = "java(militaryFormationService.getMilitaryFormationByName(dto.getMilitaryFormationName()))")
    public abstract Armament dtoToEntity(ArmamentDto dto);
}
