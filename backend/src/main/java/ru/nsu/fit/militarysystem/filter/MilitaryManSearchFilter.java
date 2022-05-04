package ru.nsu.fit.militarysystem.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nsu.fit.militarysystem.filter.criteria.MilitaryManCriteria;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;

@Getter
@Setter
@ToString
public class MilitaryManSearchFilter extends PageFilter {
    private MilitaryManCriteria militaryManCriteria;

    public MilitaryManSearchFilter() {
        super();
        this.militaryManCriteria = new MilitaryManCriteria();
    }

    public MilitaryManSearchFilter(PageCriteria pageCriteria, MilitaryManCriteria militaryManCriteria) {
        super(pageCriteria);
        this.militaryManCriteria = militaryManCriteria;
    }
}
