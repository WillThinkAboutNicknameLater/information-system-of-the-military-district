/**
  * Функции *
 */

/* 1. Получить все подчиненные формирования */
CREATE OR REPLACE FUNCTION find_all_subordinates_by_military_formation_id(military_formation_id integer)
    RETURNS SETOF military_formations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        WITH RECURSIVE military_formation_hierarchy AS (
            SELECT military_formations.*
            FROM military_formations
            WHERE military_formations.id = military_formation_id

            UNION ALL

            SELECT f.*
            FROM military_formations AS f,
                 military_formation_hierarchy
            WHERE f.parent_id = military_formation_hierarchy.id
        )
        SELECT *
        FROM military_formation_hierarchy
        WHERE military_formation_hierarchy.id != military_formation_id
        ORDER BY military_formation_hierarchy.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить всех военнослужащих формирования */
CREATE OR REPLACE FUNCTION find_all_military_men_by_military_formation_id(military_formation_id integer)
    RETURNS SETOF military_men
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT mm.*
        FROM military_men AS mm
                 INNER JOIN military_men__military_formations AS mmmf ON mm.id = mmmf.military_man_id
        WHERE mmmf.military_formation_id = $1
        GROUP BY mm.id
        ORDER BY mm.id;
END;
$$ LANGUAGE PLPGSQL;

/* 2, 3, 11. Получить всех военнослужащих по фильтру */
CREATE OR REPLACE FUNCTION find_all_military_men_by_filter(
    search_name text,
    rank_ids smallint[],
    rank_category_ids smallint[],
    staff_category_ids smallint[],
    military_specialty_ids smallint[],
    military_formation_ids integer[]
)
    RETURNS SETOF military_men
AS
$$
DECLARE
    name_template text;
BEGIN
    name_template := '%' || LOWER(search_name) || '%';
    RETURN QUERY
        SELECT mm.*
        FROM military_men AS mm
                 INNER JOIN ranks AS r ON r.id = mm.rank_id
                 LEFT JOIN military_men__military_specialties AS mmms ON mm.id = mmms.military_man_id
                 LEFT JOIN military_men__military_formations AS mmmf ON mm.id = mmmf.military_man_id
        WHERE (LOWER(mm.second_name) LIKE name_template OR LOWER(mm.first_name) LIKE name_template OR LOWER(mm.patronymic) LIKE name_template)
          AND ((CARDINALITY(rank_ids) != 0 AND mm.rank_id = ANY (rank_ids)) OR CARDINALITY(rank_ids) = 0)
          AND ((CARDINALITY(rank_category_ids) != 0 AND r.rank_category_id = ANY (rank_category_ids)) OR CARDINALITY(rank_category_ids) = 0)
          AND ((CARDINALITY(staff_category_ids) != 0 AND r.staff_category_id = ANY (staff_category_ids)) OR CARDINALITY(staff_category_ids) = 0)
          AND ((CARDINALITY(military_specialty_ids) != 0 AND mmms.military_specialty_id = ANY (military_specialty_ids)) OR CARDINALITY(military_specialty_ids) = 0)
          AND ((CARDINALITY(military_formation_ids) != 0 AND mmmf.military_formation_id = ANY (military_formation_ids)) OR CARDINALITY(military_formation_ids) = 0)
        GROUP BY mm.id
        ORDER BY mm.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить все формирования, в которые входит военнослужащий */
CREATE OR REPLACE FUNCTION find_all_military_formations_by_military_man_id(military_man_id integer)
    RETURNS SETOF military_formations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT mf.*
        FROM military_men AS mm
                 LEFT JOIN military_men__military_formations AS mmmf ON mm.id = mmmf.military_man_id
                 INNER JOIN military_formations AS mf ON mf.id = mmmf.military_formation_id
        WHERE mm.id = $1
        ORDER BY mf.id;
END;
$$ LANGUAGE PLPGSQL;

/* 4. Получить цепочку подчиненности для военнослужащего */
CREATE OR REPLACE FUNCTION find_all_subordinates_by_military_man_id(military_man_id integer)
    RETURNS SETOF military_men
AS
$$
DECLARE
    military_man_staff_category_id smallint;
BEGIN
    SELECT staff_category_id
    FROM ranks,
         military_men
    WHERE military_men.id = military_man_id
      AND military_men.rank_id = ranks.id
    INTO military_man_staff_category_id;
    RETURN QUERY
        SELECT m.*
        FROM find_all_military_formations_by_military_man_id(military_man_id) AS mf
                 INNER JOIN military_men__military_formations AS mmmf ON mf.id = mmmf.military_formation_id
                 INNER JOIN military_men AS m ON m.id = mmmf.military_man_id
                 INNER JOIN ranks AS r ON r.id = m.rank_id
        WHERE r.staff_category_id > military_man_staff_category_id
        GROUP BY m.id
        ORDER BY m.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить все дислокации формирования */
CREATE OR REPLACE FUNCTION find_all_dislocations_by_military_formation_id(military_formation_id integer)
    RETURNS SETOF dislocations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT dislocations.*
        FROM dislocations
                 INNER JOIN military_formations AS mf ON dislocations.id = mf.dislocation_id
        WHERE mf.parent_id = military_formation_id
           OR mf.id = military_formation_id
        GROUP BY dislocations.id
        ORDER BY dislocations.id;
END;
$$ LANGUAGE PLPGSQL;

/* 5. Получить все дислокации по фильтру */
CREATE OR REPLACE FUNCTION find_all_dislocations_by_filter(
    search_name text,
    dislocation_type_ids smallint[],
    subject_ids smallint[]
)
    RETURNS SETOF dislocations
AS
$$
DECLARE
    name_template text := '%' || LOWER(search_name) || '%';
BEGIN
    RETURN QUERY
        SELECT d.*
        FROM dislocations AS d
        WHERE LOWER(d.name) LIKE name_template
          AND ((CARDINALITY(dislocation_type_ids) != 0 AND d.dislocation_type_id = ANY (dislocation_type_ids)) OR CARDINALITY(dislocation_type_ids) = 0)
          AND ((CARDINALITY(subject_ids) != 0 AND d.subject_id = ANY (subject_ids)) OR CARDINALITY(subject_ids) = 0)
        ORDER BY d.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить всю боевую технику формирования */
CREATE OR REPLACE FUNCTION find_all_combat_vehicles_by_military_formation_id(military_formation_id integer)
    RETURNS SETOF combat_vehicles
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT combat_vehicles.*
        FROM combat_vehicles
                 INNER JOIN military_formations AS mf ON combat_vehicles.military_formation_id = mf.id
        WHERE mf.id = $1
        GROUP BY combat_vehicles.id
        ORDER BY combat_vehicles.id;
END;
$$ LANGUAGE PLPGSQL;

/* 6. Получить всю боевую технику по фильтру */
CREATE OR REPLACE FUNCTION find_all_combat_vehicles_by_filter(
    search_name text,
    combat_vehicle_category_ids smallint[],
    combat_vehicle_group_ids smallint[],
    military_formation_ids integer[]
)
    RETURNS SETOF combat_vehicles
AS
$$
DECLARE
    name_template text := '%' || LOWER(search_name) || '%';
BEGIN
    RETURN QUERY
        SELECT cv.*
        FROM combat_vehicles AS cv
                 INNER JOIN combat_vehicle_categories AS cvc ON cvc.id = cv.combat_vehicle_category_id
                 INNER JOIN combat_vehicle_groups AS cvg ON cvg.id = cvc.combat_vehicle_group_id
        WHERE LOWER(cv.name) LIKE name_template
          AND ((CARDINALITY(combat_vehicle_category_ids) != 0 AND cv.combat_vehicle_category_id = ANY (combat_vehicle_category_ids)) OR CARDINALITY(combat_vehicle_category_ids) = 0)
          AND ((CARDINALITY(combat_vehicle_group_ids) != 0 AND cvc.combat_vehicle_group_id = ANY (combat_vehicle_group_ids)) OR CARDINALITY(combat_vehicle_group_ids) = 0)
          AND ((CARDINALITY(military_formation_ids) != 0 AND cv.military_formation_id = ANY (military_formation_ids)) OR CARDINALITY(military_formation_ids) = 0)
        ORDER BY cv.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить все сооружения формирования */
CREATE OR REPLACE FUNCTION find_all_military_buildings_by_military_formation_id(military_formation_id integer)
    RETURNS SETOF military_buildings
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT military_buildings.*
        FROM military_buildings
                 INNER JOIN military_formations AS mf ON military_buildings.military_formation_id = mf.id
        WHERE mf.id = $1
        GROUP BY military_buildings.id
        ORDER BY military_buildings.id;
END;
$$ LANGUAGE PLPGSQL;

/* 7. Получить все сооружения по фильтру */
CREATE OR REPLACE FUNCTION find_all_military_buildings_by_filter(
    search_name text,
    military_building_category_ids smallint[],
    military_formation_ids integer[]
)
    RETURNS SETOF military_buildings
AS
$$
DECLARE
    name_template text := '%' || LOWER(search_name) || '%';
BEGIN
    RETURN QUERY
        SELECT mb.*
        FROM military_buildings AS mb
        WHERE LOWER(mb.name) LIKE name_template
          AND ((CARDINALITY(military_building_category_ids) != 0 AND mb.military_building_category_id = ANY (military_building_category_ids)) OR CARDINALITY(military_building_category_ids) = 0)
          AND ((CARDINALITY(military_formation_ids) != 0 AND mb.military_formation_id = ANY (military_formation_ids)) OR CARDINALITY(military_formation_ids) = 0)
        ORDER BY mb.id;
END;
$$ LANGUAGE PLPGSQL;

/* 8. Получить все формирования по фильтру боевой техники */
CREATE OR REPLACE FUNCTION find_all_military_formations_by_combat_vehicle_filter(
    combat_vehicle_category_id smallint,
    min_number_of_units bigint,
    max_number_of_units bigint
)
    RETURNS setof military_formations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        WITH child AS (
            SELECT mf.*
            FROM military_formations AS mf
                     FULL OUTER JOIN combat_vehicles AS cv ON mf.id = cv.military_formation_id
            WHERE cv.combat_vehicle_category_id = $1
               OR cv.combat_vehicle_category_id IS NULL
            GROUP BY cv.combat_vehicle_category_id, mf.id
            HAVING COUNT(cv) BETWEEN min_number_of_units AND max_number_of_units
        )
        SELECT *
        FROM child
        GROUP BY child.id, child.name, child.date_of_formation, child.military_formation_type_id, child.commander_id, child.dislocation_id, child.parent_id
        ORDER BY child.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить всё вооружение формирования */
CREATE OR REPLACE FUNCTION find_all_armaments_by_military_formation_id(military_formation_id integer)
    RETURNS SETOF armaments
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT armaments.*
        FROM armaments
                 INNER JOIN military_formations AS mf ON armaments.military_formation_id = mf.id
        WHERE mf.id = $1
        GROUP BY armaments.id
        ORDER BY armaments.id;
END;
$$ LANGUAGE PLPGSQL;

/* 9. Получить всё вооружение по фильтру */
CREATE OR REPLACE FUNCTION find_all_armaments_by_filter(
    search_name text,
    armament_category_ids smallint[],
    military_formation_ids integer[]
)
    RETURNS SETOF armaments
AS
$$
DECLARE
    name_template text := '%' || LOWER(search_name) || '%';
BEGIN
    RETURN QUERY
        SELECT a.*
        FROM armaments AS a
                 INNER JOIN armament_categories AS ac ON ac.id = a.armament_category_id
        WHERE LOWER(a.name) LIKE name_template
          AND ((CARDINALITY(armament_category_ids) != 0 AND a.armament_category_id = ANY (armament_category_ids)) OR CARDINALITY(armament_category_ids) = 0)
          AND ((CARDINALITY(military_formation_ids) != 0 AND a.military_formation_id = ANY (military_formation_ids)) OR CARDINALITY(military_formation_ids) = 0)
        ORDER BY a.id;
END;
$$ LANGUAGE PLPGSQL;

/* 10.1. Получить все специальности по фильтру */
CREATE OR REPLACE FUNCTION find_all_military_specialties_by_filter(
    military_formation_id integer,
    min_number_of_specialists bigint,
    max_number_of_specialists bigint
)
    RETURNS SETOF military_specialties
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        WITH child AS (SELECT *
                       FROM military_men AS mm
                                FULL OUTER JOIN military_men__military_formations AS mmmf ON mm.id = mmmf.military_man_id
                       WHERE mmmf.military_formation_id = $1)
        SELECT ms.*
        FROM child
                 INNER JOIN military_men__military_specialties AS mmms ON mmms.military_man_id = child.military_man_id
                 FULL OUTER JOIN military_specialties AS ms ON mmms.military_specialty_id = ms.id
        GROUP BY mmms.military_specialty_id, ms.id
        HAVING COUNT(mmms.military_specialty_id) BETWEEN min_number_of_specialists AND max_number_of_specialists
        ORDER BY ms.id;
END;
$$ LANGUAGE PLPGSQL;

/* 10.2. Получить все специальности по фильтру */
CREATE OR REPLACE FUNCTION find_all_military_specialties_by_filter(
    min_number_of_specialists bigint,
    max_number_of_specialists bigint
)
    RETURNS SETOF military_specialties
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        SELECT ms.*
        FROM military_specialties AS ms
                 FULL OUTER JOIN military_men__military_specialties AS mmms ON ms.id = mmms.military_specialty_id
        GROUP BY ms.id
        HAVING COUNT(mmms.military_specialty_id) BETWEEN min_number_of_specialists AND max_number_of_specialists
        ORDER BY ms.id;
END;
$$ LANGUAGE PLPGSQL;

/* 12. Получить все формирования по фильтру вооружения */
CREATE OR REPLACE FUNCTION find_all_military_formations_by_armament_filter(
    armament_category_id smallint,
    min_number_of_units bigint,
    max_number_of_units bigint
)
    RETURNS setof military_formations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        WITH child AS (
            SELECT mf.*
            FROM military_formations AS mf
                     FULL OUTER JOIN armaments AS a ON mf.id = a.military_formation_id
            WHERE a.armament_category_id = $1
               OR a.armament_category_id IS NULL
            GROUP BY a.armament_category_id, mf.id
            HAVING COUNT(a) BETWEEN min_number_of_units AND max_number_of_units
        )
        SELECT *
        FROM child
        GROUP BY child.id, child.name, child.date_of_formation, child.military_formation_type_id, child.commander_id, child.dislocation_id, child.parent_id
        ORDER BY child.id;
END;
$$ LANGUAGE PLPGSQL;

/* 13. Получить все формирования по максимальному числу подчиненных */
CREATE OR REPLACE FUNCTION find_all_military_formations_by_max_number_of_subordinated()
    RETURNS setof military_formations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        WITH child AS (SELECT *,
                              (SELECT COUNT(*)
                               FROM military_formations
                               WHERE parent_id = mf.id) AS number_of_children
                       FROM military_formations AS mf)
        SELECT child.id, child.name, child.date_of_formation, child.military_formation_type_id, child.commander_id, child.dislocation_id, child.parent_id
        FROM child
        WHERE number_of_children = (SELECT MAX(number_of_children) FROM child)
        ORDER BY number_of_children;
END;
$$ LANGUAGE PLPGSQL;

/* 13. Получить все формирования по минимальному числу подчиненных */
CREATE OR REPLACE FUNCTION find_all_military_formations_by_min_number_of_subordinated()
    RETURNS setof military_formations
AS
$$
DECLARE
BEGIN
    RETURN QUERY
        WITH child AS (SELECT *,
                              (SELECT COUNT(*)
                               FROM military_formations
                               WHERE parent_id = mf.id) AS number_of_children
                       FROM military_formations AS mf)
        SELECT child.id, child.name, child.date_of_formation, child.military_formation_type_id, child.commander_id, child.dislocation_id, child.parent_id
        FROM child
        WHERE number_of_children = (SELECT MIN(number_of_children) FROM child)
        ORDER BY number_of_children;
END;
$$ LANGUAGE PLPGSQL;

/* Получить все звания по фильтру */
CREATE OR REPLACE FUNCTION find_all_ranks_by_filter(
    search_name text,
    rank_category_ids smallint[],
    staff_category_ids smallint[]
)
    RETURNS SETOF ranks
AS
$$
DECLARE
    name_template text := '%' || LOWER(search_name) || '%';
BEGIN
    RETURN QUERY
        SELECT r.*
        FROM ranks AS r
                 INNER JOIN rank_categories AS rc ON rc.id = r.rank_category_id
                 INNER JOIN staff_categories AS sc ON sc.id = r.staff_category_id
        WHERE LOWER(r.name) LIKE name_template
          AND ((CARDINALITY(rank_category_ids) != 0 AND r.rank_category_id = ANY (rank_category_ids)) OR CARDINALITY(rank_category_ids) = 0)
          AND ((CARDINALITY(staff_category_ids) != 0 AND r.staff_category_id = ANY (staff_category_ids)) OR CARDINALITY(staff_category_ids) = 0)
        ORDER BY r.id;
END;
$$ LANGUAGE PLPGSQL;

/* Получить все категории боевой техники по фильтру */
CREATE OR REPLACE FUNCTION find_all_combat_vehicle_categories_by_filter(
    search_name text,
    combat_vehicle_group_ids smallint[]
)
    RETURNS SETOF combat_vehicle_categories
AS
$$
DECLARE
    name_template text := '%' || LOWER(search_name) || '%';
BEGIN
    RETURN QUERY
        SELECT cvc.*
        FROM combat_vehicle_categories AS cvc
                 INNER JOIN combat_vehicle_groups AS cvg ON cvg.id = cvc.combat_vehicle_group_id
        WHERE LOWER(cvc.name) LIKE name_template
          AND ((CARDINALITY(combat_vehicle_group_ids) != 0 AND cvc.combat_vehicle_group_id = ANY (combat_vehicle_group_ids)) OR CARDINALITY(combat_vehicle_group_ids) = 0)
        ORDER BY cvc.id;
END;
$$ LANGUAGE PLPGSQL;
