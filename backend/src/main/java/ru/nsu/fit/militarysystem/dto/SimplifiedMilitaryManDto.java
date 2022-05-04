package ru.nsu.fit.militarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimplifiedMilitaryManDto implements BaseDto {
    private Integer id;

    private String secondName;

    private String firstName;

    private String patronymic;

    private String identificationNumber;
}
