package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormation;

import java.util.List;
import java.util.Optional;

public interface MilitaryFormationRepository extends JpaRepository<MilitaryFormation, Integer> {
    Optional<MilitaryFormation> findByName(String name);

    @Query(value = "SELECT * FROM find_all_military_formations_by_filter(CAST(:searchName AS text), CAST(:militaryFormationTypeIds AS smallint[]))",
           nativeQuery = true)
    Page<MilitaryFormation> findAllByFilter(String searchName, String militaryFormationTypeIds, Pageable pageable);

    @Query(value = "SELECT * FROM find_all_subordinates_by_military_formation_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<MilitaryFormation> findAllSubordinatesById(int id);
}