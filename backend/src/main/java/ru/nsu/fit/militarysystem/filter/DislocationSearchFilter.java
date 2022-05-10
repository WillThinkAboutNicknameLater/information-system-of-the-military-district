package ru.nsu.fit.militarysystem.filter;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DislocationSearchFilter extends PageFilter {
    @JsonAlias("name")
    private String searchName = "";

    @JsonAlias("type-id")
    private Set<Short> dislocationTypeIds = new HashSet<>();

    @JsonAlias("subject-id")
    private Set<Short> subjectIds = new HashSet<>();
}
