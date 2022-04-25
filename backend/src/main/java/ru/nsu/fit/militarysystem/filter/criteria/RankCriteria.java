package ru.nsu.fit.militarysystem.filter.criteria;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RankCriteria {
    private List<Short> staffCategories;

    private List<Short> rankCategories;
}
