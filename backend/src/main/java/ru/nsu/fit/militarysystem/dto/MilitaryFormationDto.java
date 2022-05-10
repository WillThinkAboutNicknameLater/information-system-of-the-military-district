package ru.nsu.fit.militarysystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MilitaryFormationDto implements BaseDto {
    private Integer id;

    private String name;

    private String militaryFormationTypeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfFormation;

    private SimplifiedMilitaryManDto commander;

    private SimplifiedDislocationDto dislocation;
}
