package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.Dislocation;

import java.util.List;
import java.util.Optional;

public interface DislocationRepository extends JpaRepository<Dislocation, Integer> {
    Optional<Dislocation> findByOkato(String okato);

    @Query(value = "SELECT * FROM find_all_dislocations_by_filter(CAST(:searchName AS text), CAST(:dislocationTypeIds AS smallint[]), CAST(:subjectIds AS smallint[]))",
           nativeQuery = true)
    Page<Dislocation> findAllByFilter(String searchName, String dislocationTypeIds, String subjectIds, Pageable pageable);

    @Query(value = "SELECT * FROM find_all_dislocations_by_military_formation_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<Dislocation> findAllByMilitaryFormationId(int id);
}
