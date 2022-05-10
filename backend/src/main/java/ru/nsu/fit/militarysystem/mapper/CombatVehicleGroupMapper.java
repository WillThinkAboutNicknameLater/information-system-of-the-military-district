package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.CombatVehicleGroupDto;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicleGroup;

@Mapper(componentModel = "spring")
public interface CombatVehicleGroupMapper extends BaseMapper<CombatVehicleGroup, CombatVehicleGroupDto> {
}
