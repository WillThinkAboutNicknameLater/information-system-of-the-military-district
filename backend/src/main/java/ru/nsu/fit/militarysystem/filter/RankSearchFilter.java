package ru.nsu.fit.militarysystem.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.criteria.RankCriteria;

@Getter
@Setter
@ToString
public class RankSearchFilter extends PageFilter {
    private RankCriteria rankCriteria;

    public RankSearchFilter() {
        super();
        this.rankCriteria = new RankCriteria();
    }

    public RankSearchFilter(PageCriteria pageCriteria, RankCriteria rankCriteria) {
        super(pageCriteria);
        this.rankCriteria = rankCriteria;
    }
}
