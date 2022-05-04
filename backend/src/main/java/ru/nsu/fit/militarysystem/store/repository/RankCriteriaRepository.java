package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.militarysystem.filter.RankSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.Rank;
import ru.nsu.fit.militarysystem.filter.criteria.RankCriteria;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RankCriteriaRepository {
    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public RankCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Rank> findAllByFilter(RankSearchFilter rankSearchFilter) {
        PageCriteria pageCriteria = rankSearchFilter.getPageCriteria();
        RankCriteria rankCriteria = rankSearchFilter.getRankCriteria();
        CriteriaQuery<Rank> criteriaQuery = criteriaBuilder.createQuery(Rank.class);
        Root<Rank> rankRoot = criteriaQuery.from(Rank.class);
        Predicate predicate = getPredicate(rankCriteria, rankRoot);
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(getOrders(pageCriteria, rankRoot));

        TypedQuery<Rank> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageCriteria.getPageNumber() * pageCriteria.getPageSize());
        typedQuery.setMaxResults(pageCriteria.getPageSize());

        Pageable pageable = getPageable(pageCriteria);

        long ranksCount = getRanksCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, ranksCount);
    }

    private Predicate getPredicate(RankCriteria rankCriteria, Root<Rank> rankRoot) {
        List<Short> staffCategories = rankCriteria.getStaffCategories();
        List<Short> rankCategories = rankCriteria.getRankCategories();

        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(staffCategories)) {
            predicates.add(rankRoot.get("staffCategory").in(staffCategories));
        }

        if (Objects.nonNull(rankCategories)) {
            predicates.add(rankRoot.get("rankCategory").in(rankCategories));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }

    private List<Order> getOrders(PageCriteria pageCriteria, Root<Rank> rankRoot) {
        List<Order> orders = new ArrayList<>();
        if (pageCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
            for (String order : pageCriteria.getSortBy()) {
                orders.add(criteriaBuilder.asc(rankRoot.get(order)));
            }
            return orders;
        }
        for (String order : pageCriteria.getSortBy()) {
            orders.add(criteriaBuilder.desc(rankRoot.get(order)));
        }
        return orders;
    }

    private Pageable getPageable(PageCriteria pageCriteria) {
        Sort sort = Sort.by(pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        return PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), sort);
    }

    private long getRanksCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Rank> countRoot = countQuery.from(Rank.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
