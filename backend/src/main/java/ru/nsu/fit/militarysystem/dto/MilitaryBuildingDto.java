package ru.nsu.fit.militarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MilitaryBuildingDto implements BaseDto {
    private Integer id;

    private String name;

    private String militaryBuildingCategoryName;

    private String militaryFormationName;
}
