package ru.nsu.fit.militarysystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectDto implements BaseDto {
    private Short id;

    private String name;
}
