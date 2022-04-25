package ru.nsu.fit.militarysystem.filter;

import lombok.*;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.criteria.RankCriteria;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RankSearchFilter {
    private PageCriteria pageCriteria;

    private RankCriteria rankCriteria;

    public RankSearchFilter() {
        this.pageCriteria = new PageCriteria();
        this.rankCriteria = new RankCriteria();
    }
}
