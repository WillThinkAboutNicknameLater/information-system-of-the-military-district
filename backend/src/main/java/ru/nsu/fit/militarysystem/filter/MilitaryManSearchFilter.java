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
public class MilitaryManSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("rank-id")
    private Set<Short> rankIds = new HashSet<>();

    @JsonAlias("rank-category-id")
    private Set<Short> rankCategoryIds = new HashSet<>();

    @JsonAlias("staff-category-id")
    private Set<Short> staffCategoryIds = new HashSet<>();

    @JsonAlias("specialty-id")
    private Set<Short> militarySpecialtyIds = new HashSet<>();

    @JsonAlias("formation-id")
    private Set<Integer> militaryFormationIds = new HashSet<>();
}
