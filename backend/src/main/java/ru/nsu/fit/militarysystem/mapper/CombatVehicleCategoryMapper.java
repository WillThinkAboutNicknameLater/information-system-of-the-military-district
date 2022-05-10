package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.militarysystem.dto.CombatVehicleCategoryDto;
import ru.nsu.fit.militarysystem.service.CombatVehicleGroupService;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicleCategory;

@Mapper(componentModel = "spring")
public abstract class CombatVehicleCategoryMapper implements BaseMapper<CombatVehicleCategory, CombatVehicleCategoryDto> {
    @Autowired
    protected CombatVehicleGroupService combatVehicleGroupService;

    @Override
    @Mapping(source = "combatVehicleGroup.name", target = "combatVehicleGroupName")
    public abstract CombatVehicleCategoryDto entityToDto(CombatVehicleCategory entity);

    @Override
    @Mapping(target = "combatVehicleGroup",
             expression = "java(combatVehicleGroupService.getCombatVehicleGroupByName(dto.getCombatVehicleGroupName()))")
    public abstract CombatVehicleCategory dtoToEntity(CombatVehicleCategoryDto dto);
}
