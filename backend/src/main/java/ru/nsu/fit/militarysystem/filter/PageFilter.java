package ru.nsu.fit.militarysystem.filter;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageFilter {
    @JsonAlias("page-number")
    protected Integer pageNumber = 0;

    @JsonAlias("page-size")
    protected Integer pageSize = 10;

    @JsonAlias("sort-direction")
    protected Sort.Direction sortDirection = Sort.Direction.ASC;

    @JsonAlias("sort-by")
    protected Set<String> sortBy = Set.of("id");
}
