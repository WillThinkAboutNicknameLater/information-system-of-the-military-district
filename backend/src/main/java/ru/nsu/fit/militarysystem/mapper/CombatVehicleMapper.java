package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.nsu.fit.militarysystem.dto.CombatVehicleDto;
import ru.nsu.fit.militarysystem.service.CombatVehicleCategoryService;
import ru.nsu.fit.militarysystem.service.MilitaryFormationService;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicle;

@Mapper(componentModel = "spring")
public abstract class CombatVehicleMapper implements BaseMapper<CombatVehicle, CombatVehicleDto> {
    @Autowired
    protected CombatVehicleCategoryService combatVehicleCategoryService;

    @Lazy
    @Autowired
    protected MilitaryFormationService militaryFormationService;

    @Override
    @Mapping(source = "combatVehicleCategory.name", target = "combatVehicleCategoryName")
    @Mapping(source = "militaryFormation.name", target = "militaryFormationName")
    public abstract CombatVehicleDto entityToDto(CombatVehicle entity);

    @Override
    @Mapping(target = "combatVehicleCategory",
             expression = "java(combatVehicleCategoryService.getCombatVehicleCategoryByName(dto.getCombatVehicleCategoryName()))")
    @Mapping(target = "militaryFormation",
             expression = "java(militaryFormationService.getMilitaryFormationByName(dto.getMilitaryFormationName()))")
    public abstract CombatVehicle dtoToEntity(CombatVehicleDto dto);
}
