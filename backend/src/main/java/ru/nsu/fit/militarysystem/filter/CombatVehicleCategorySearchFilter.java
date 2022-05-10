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
public class CombatVehicleCategorySearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("group-id")
    private Set<Short> combatVehicleGroupIds = new HashSet<>();
}
