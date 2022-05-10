package ru.nsu.fit.militarysystem.filter;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CombatVehicleSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("category-id")
    private Set<Short> combatVehicleCategoryIds = new HashSet<>();

    @JsonAlias("group-id")
    private Set<Short> combatVehicleGroupIds = new HashSet<>();

    @JsonAlias("formation-id")
    private Set<Integer> militaryFormationIds = new HashSet<>();
}
