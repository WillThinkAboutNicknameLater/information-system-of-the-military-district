package ru.nsu.fit.militarysystem.filter;

import lombok.*;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubjectSearchFilter {
    private PageCriteria pageCriteria;

    public SubjectSearchFilter() {
        this.pageCriteria = new PageCriteria();
    }
}
