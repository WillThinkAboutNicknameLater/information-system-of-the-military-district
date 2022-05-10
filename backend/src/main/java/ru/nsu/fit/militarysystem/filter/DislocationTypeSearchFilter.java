package ru.nsu.fit.militarysystem.filter;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DislocationTypeSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";
}
