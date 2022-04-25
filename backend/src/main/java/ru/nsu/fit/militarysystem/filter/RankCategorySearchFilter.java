package ru.nsu.fit.militarysystem.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RankCategorySearchFilter {
    private PageCriteria pageCriteria;

    public RankCategorySearchFilter() {
        this.pageCriteria = new PageCriteria();
    }
}
