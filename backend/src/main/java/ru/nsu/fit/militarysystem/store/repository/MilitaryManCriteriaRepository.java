package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.militarysystem.filter.MilitaryManSearchFilter;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.criteria.MilitaryManCriteria;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MilitaryManCriteriaRepository {
    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public MilitaryManCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<MilitaryMan> findAllWithFilters(MilitaryManSearchFilter militaryManSearchFilter) {
        PageCriteria pageCriteria = militaryManSearchFilter.getPageCriteria();
        MilitaryManCriteria militaryManCriteria = militaryManSearchFilter.getMilitaryManCriteria();
        CriteriaQuery<MilitaryMan> criteriaQuery = criteriaBuilder.createQuery(MilitaryMan.class);
        Root<MilitaryMan> militaryManRoot = criteriaQuery.from(MilitaryMan.class);
        Predicate predicate = getPredicate(militaryManCriteria, militaryManRoot);
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(getOrders(pageCriteria, militaryManRoot));

        TypedQuery<MilitaryMan> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageCriteria.getPageNumber() * pageCriteria.getPageSize());
        typedQuery.setMaxResults(pageCriteria.getPageSize());

        Pageable pageable = getPageable(pageCriteria);

        long militaryMenCount = getMilitaryMenCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, militaryMenCount);
    }

    private Predicate getPredicate(MilitaryManCriteria militaryManCriteria, Root<MilitaryMan> militaryManRoot) {
        String name = militaryManCriteria.getName();

        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(name)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(militaryManRoot.get("secondName")), "%" + name.toLowerCase() + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(militaryManRoot.get("firstName")), "%" + name.toLowerCase() + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(militaryManRoot.get("patronymic")), "%" + name.toLowerCase() + "%"));
        }

        if (predicates.isEmpty()) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.and(criteriaBuilder.or(predicates.toArray(new Predicate[]{})));
    }

    private List<Order> getOrders(PageCriteria pageCriteria, Root<MilitaryMan> militaryManRoot) {
        List<Order> orders = new ArrayList<>();
        if (pageCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
            for (String order : pageCriteria.getSortBy()) {
                orders.add(criteriaBuilder.asc(militaryManRoot.get(order)));
            }
            return orders;
        }
        for (String order : pageCriteria.getSortBy()) {
            orders.add(criteriaBuilder.desc(militaryManRoot.get(order)));
        }
        return orders;
    }

    private Pageable getPageable(PageCriteria pageCriteria) {
        Sort sort = Sort.by(pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        return PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), sort);
    }

    private long getMilitaryMenCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<MilitaryMan> countRoot = countQuery.from(MilitaryMan.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

