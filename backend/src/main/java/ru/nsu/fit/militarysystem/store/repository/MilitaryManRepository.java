package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;

import java.util.List;
import java.util.Optional;

public interface MilitaryManRepository extends JpaRepository<MilitaryMan, Integer> {
    Optional<MilitaryMan> findByIdentificationNumber(String identificationNumber);

    @Query(value = "SELECT * FROM find_all_military_men_by_filter(CAST(:searchName AS text), CAST(:rankIds AS smallint[]), CAST(:rankCategoryIds AS smallint[]), CAST(:staffCategoryIds AS smallint[]), CAST(:militarySpecialtyIds AS smallint[]), CAST(:militaryFormationIds AS integer[]))",
           nativeQuery = true)
    Page<MilitaryMan> findAllByFilter(String searchName,
                                      String rankIds,
                                      String rankCategoryIds,
                                      String staffCategoryIds,
                                      String militarySpecialtyIds,
                                      String militaryFormationIds,
                                      Pageable pageable);

    @Query(value = "SELECT * FROM find_all_subordinates_by_military_man_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<MilitaryMan> findAllSubordinatesById(int id);

    @Query(value = "SELECT * FROM find_all_military_men_by_military_formation_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<MilitaryMan> findAllByMilitaryFormationId(int id);
}
