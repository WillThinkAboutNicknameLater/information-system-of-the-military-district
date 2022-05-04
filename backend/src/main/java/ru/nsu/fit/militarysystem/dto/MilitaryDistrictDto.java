package ru.nsu.fit.militarysystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MilitaryDistrictDto implements BaseDto {
    private Short id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfFormation;

    private Short headquartersDislocationId;

    private String headquartersDislocationName;

    private SimplifiedMilitaryManDto commander;
}
