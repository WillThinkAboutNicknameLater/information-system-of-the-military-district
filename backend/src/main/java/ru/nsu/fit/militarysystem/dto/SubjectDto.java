package ru.nsu.fit.militarysystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDto implements BaseDto {
    private Short id;

    private String name;
}
