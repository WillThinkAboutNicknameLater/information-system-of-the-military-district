package ru.nsu.fit.militarysystem.filter.criteria;

import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageCriteria {
    Integer pageNumber = 0;

    Integer pageSize = 10;

    Sort.Direction sortDirection = Sort.Direction.ASC;

    List<String> sortBy = List.of("id");
}
