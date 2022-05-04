package ru.nsu.fit.militarysystem.filter;

import lombok.*;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;

@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class PageFilter {
    protected PageCriteria pageCriteria;

    PageFilter() {
        this.pageCriteria = new PageCriteria();
    }
}
