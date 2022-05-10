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
public class RankSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("staff-category-id")
    private Set<Short> staffCategoryIds = new HashSet<>();

    @JsonAlias("rank-category-id")
    private Set<Short> rankCategoryIds = new HashSet<>();
}
