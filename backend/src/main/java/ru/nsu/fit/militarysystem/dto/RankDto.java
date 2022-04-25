package ru.nsu.fit.militarysystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RankDto {
    private Short id;

    private String name;

    private StaffCategoryDto staffCategory;

    private RankCategoryDto rankCategory;
}
