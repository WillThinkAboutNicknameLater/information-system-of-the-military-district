package ru.nsu.fit.militarysystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RankDto implements BaseDto {
    private Short id;

    private String name;

    private String staffCategoryName;

    private String rankCategoryName;
}
