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
public class MilitarySpecialtySearchFilter {
    private PageCriteria pageCriteria;

    public MilitarySpecialtySearchFilter() {
        this.pageCriteria = new PageCriteria();
    }
}
