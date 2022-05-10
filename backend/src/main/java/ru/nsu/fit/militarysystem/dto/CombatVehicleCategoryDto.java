package ru.nsu.fit.militarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CombatVehicleCategoryDto implements BaseDto {
    private Short id;

    private String name;

    private String combatVehicleGroupName;
}
