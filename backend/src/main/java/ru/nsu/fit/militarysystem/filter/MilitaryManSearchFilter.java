package ru.nsu.fit.militarysystem.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nsu.fit.militarysystem.filter.criteria.MilitaryManCriteria;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class MilitaryManSearchFilter {
    private PageCriteria pageCriteria;

    private MilitaryManCriteria militaryManCriteria;

    public MilitaryManSearchFilter() {
        this.pageCriteria = new PageCriteria();
        this.militaryManCriteria = new MilitaryManCriteria();
    }
}
