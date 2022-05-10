package ru.nsu.fit.militarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimplifiedMilitaryManDto implements BaseDto {
    private String identificationNumber;

    private String secondName;

    private String firstName;

    private String patronymic;
}
