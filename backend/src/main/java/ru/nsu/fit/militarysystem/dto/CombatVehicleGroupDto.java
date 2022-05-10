package ru.nsu.fit.militarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CombatVehicleGroupDto implements BaseDto {
    private Short id;

    private String name;
}
