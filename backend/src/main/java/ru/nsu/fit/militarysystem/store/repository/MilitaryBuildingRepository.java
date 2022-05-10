package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.MilitaryBuilding;

import java.util.List;

public interface MilitaryBuildingRepository extends JpaRepository<MilitaryBuilding, Integer> {
    @Query(value = "SELECT * FROM find_all_military_buildings_by_filter(CAST(:searchName AS text), CAST(:militaryBuildingCategoryIds AS smallint[]), CAST(:militaryFormationIds AS integer[]))",
           nativeQuery = true)
    Page<MilitaryBuilding> findAllByFilter(String searchName, String militaryBuildingCategoryIds, String militaryFormationIds, Pageable pageable);

    @Query(value = "SELECT * FROM find_all_military_buildings_by_military_formation_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<MilitaryBuilding> findAllByMilitaryFormationId(int id);
}
