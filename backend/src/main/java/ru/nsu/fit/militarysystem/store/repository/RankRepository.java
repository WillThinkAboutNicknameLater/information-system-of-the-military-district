package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.Rank;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Short> {
    Optional<Rank> findByName(String name);

    @Query(value = "SELECT * FROM find_all_ranks_by_filter(CAST(:searchName AS text), CAST(:rankCategoryIds AS smallint[]), CAST(:staffCategoryIds AS smallint[]))",
           nativeQuery = true)
    Page<Rank> findAllByFilter(String searchName, String rankCategoryIds, String staffCategoryIds, Pageable pageable);
}
