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
public class MilitaryBuildingSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("category-id")
    private Set<Short> militaryBuildingCategoryIds = new HashSet<>();

    @JsonAlias("formation-id")
    private Set<Integer> militaryFormationIds = new HashSet<>();
}
