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
public class MilitaryFormationSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("type-id")
    private Set<Short> militaryFormationTypeIds = new HashSet<>();
}
