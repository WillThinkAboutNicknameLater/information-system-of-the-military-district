package ru.nsu.fit.militarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DislocationDto implements BaseDto {
    private Integer id;

    private String name;

    private String dislocationTypeName;

    private String subjectName;
}
