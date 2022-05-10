package ru.nsu.fit.militarysystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankDto implements BaseDto {
    private Short id;

    private String name;

    private String rankCategoryName;

    private String staffCategoryName;
}
