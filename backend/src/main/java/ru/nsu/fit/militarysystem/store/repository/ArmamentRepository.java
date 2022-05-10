package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.Armament;

import java.util.List;

public interface ArmamentRepository extends JpaRepository<Armament, Integer> {
    @Query(value = "SELECT * FROM find_all_armaments_by_filter(CAST(:searchName AS text), CAST(:armamentCategoryIds AS smallint[]), CAST(:militaryFormationIds AS integer[]))",
           nativeQuery = true)
    Page<Armament> findAllByFilter(String searchName, String armamentCategoryIds, String militaryFormationIds, Pageable pageable);

    @Query(value = "SELECT * FROM find_all_armaments_by_military_formation_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<Armament> findAllByMilitaryFormationId(int id);
}
