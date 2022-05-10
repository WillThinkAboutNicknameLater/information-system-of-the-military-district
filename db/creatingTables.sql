/**
  * Создание таблиц *
 */

DROP TABLE IF EXISTS staff_categories CASCADE;
DROP TABLE IF EXISTS rank_categories CASCADE;
DROP TABLE IF EXISTS ranks CASCADE;
DROP TABLE IF EXISTS military_specialties CASCADE;
DROP TABLE IF EXISTS military_men CASCADE;
DROP TABLE IF EXISTS military_men__military_specialties CASCADE;
DROP TABLE IF EXISTS subjects CASCADE;
DROP TABLE IF EXISTS dislocation_types CASCADE;
DROP TABLE IF EXISTS dislocations CASCADE;
DROP TABLE IF EXISTS military_districts CASCADE;
DROP TABLE IF EXISTS military_districts__subjects CASCADE;
DROP TABLE IF EXISTS military_formation_types CASCADE;
DROP TABLE IF EXISTS military_formations CASCADE;
DROP TABLE IF EXISTS military_men__military_formations CASCADE;
DROP TABLE IF EXISTS combat_vehicle_groups CASCADE;
DROP TABLE IF EXISTS combat_vehicle_categories CASCADE;
DROP TABLE IF EXISTS combat_vehicles CASCADE;
DROP TABLE IF EXISTS armament_categories CASCADE;
DROP TABLE IF EXISTS armaments CASCADE;
DROP TABLE IF EXISTS military_building_categories CASCADE;
DROP TABLE IF EXISTS military_buildings CASCADE;

/* Категории составов */
CREATE TABLE staff_categories (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Категории званий */
CREATE TABLE rank_categories (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Звания */
CREATE TABLE ranks (
    id                smallserial PRIMARY KEY,
    name              text     NOT NULL CHECK (LENGTH(name) > 0),
    staff_category_id smallint NOT NULL REFERENCES staff_categories (id),
    rank_category_id  smallint NOT NULL REFERENCES rank_categories (id),
    UNIQUE (name, staff_category_id, rank_category_id)
);

/* Воинские специальности */
CREATE TABLE military_specialties (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Военнослужащие */
CREATE TABLE military_men (
    id                    serial PRIMARY KEY,
    second_name           text     NOT NULL CHECK (LENGTH(second_name) > 0),
    first_name            text     NOT NULL CHECK (LENGTH(first_name) > 0),
    patronymic            text CHECK (LENGTH(patronymic) > 0),
    date_of_birth         date     NOT NULL CHECK (date_of_birth <= NOW()),
    date_of_award         date     NOT NULL CHECK (date_of_award <= NOW()),
    identification_number text     NOT NULL UNIQUE CHECK (LENGTH(identification_number) > 0),
    rank_id               smallint NOT NULL REFERENCES ranks (id)
);

/* Связь военнослужащих с воинскими специальностями */
CREATE TABLE military_men__military_specialties (
    military_man_id       integer  NOT NULL REFERENCES military_men (id),
    military_specialty_id smallint NOT NULL REFERENCES military_specialties (id),
    PRIMARY KEY (military_man_id, military_specialty_id)
);

/* Субъекты РФ */
CREATE TABLE subjects (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Типы дислокаций */
CREATE TABLE dislocation_types (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Дислокации */
CREATE TABLE dislocations (
    id                  serial PRIMARY KEY,
    name                text        NOT NULL CHECK (LENGTH(name) > 0),
    okato               text UNIQUE NOT NULL CHECK (LENGTH(okato) > 0),
    dislocation_type_id smallint    NOT NULL REFERENCES dislocation_types (id),
    subject_id          smallint    NOT NULL REFERENCES subjects (id)
);

/* Военные округа */
CREATE TABLE military_districts (
    id                          smallserial PRIMARY KEY,
    name                        text    NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    date_of_formation           date    NOT NULL CHECK (date_of_formation < NOW()),
    headquarters_dislocation_id integer NOT NULL REFERENCES dislocations (id),
    commander_id                integer NOT NULL REFERENCES military_men (id)
);

/* Связь военных округов с субъектами РФ */
CREATE TABLE military_districts__subjects (
    military_district_id smallint NOT NULL REFERENCES military_districts (id),
    subject_id           smallint NOT NULL UNIQUE REFERENCES subjects (id),
    PRIMARY KEY (military_district_id, subject_id)
);

/* Типы воинских формирований */
CREATE TABLE military_formation_types (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Воинские формирования */
CREATE TABLE military_formations (
    id                         serial PRIMARY KEY,
    name                       text     NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    date_of_formation          date     NOT NULL CHECK (date_of_formation < NOW()),
    military_formation_type_id smallint NOT NULL REFERENCES military_formation_types (id),
    commander_id               integer  NOT NULL REFERENCES military_men (id),
    dislocation_id             integer  NOT NULL REFERENCES dislocations (id),
    parent_id                  integer REFERENCES military_formations (id)
);

/* Связь военнослужащих с воинскими формированиями */
CREATE TABLE military_men__military_formations (
    military_man_id       integer NOT NULL REFERENCES military_men (id),
    military_formation_id integer NOT NULL REFERENCES military_formations (id),
    PRIMARY KEY (military_man_id, military_formation_id)
);

/* Группы боевой техники */
CREATE TABLE combat_vehicle_groups (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Категории боевой техники */
CREATE TABLE combat_vehicle_categories (
    id                      smallserial PRIMARY KEY,
    name                    text     NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    combat_vehicle_group_id smallint NOT NULL REFERENCES combat_vehicle_groups (id)
);

/* Боевая техника */
CREATE TABLE combat_vehicles (
    id                         serial PRIMARY KEY,
    name                       text     NOT NULL CHECK (LENGTH(name) > 0),
    serial_number              text     NOT NULL UNIQUE CHECK (LENGTH(serial_number) > 0),
    combat_vehicle_category_id smallint NOT NULL REFERENCES combat_vehicle_categories (id),
    military_formation_id      integer  NOT NULL REFERENCES military_formations (id)
);

/* Категории вооружения */
CREATE TABLE armament_categories (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Вооружение */
CREATE TABLE armaments (
    id                    serial PRIMARY KEY,
    name                  text     NOT NULL CHECK (LENGTH(name) > 0),
    serial_number         text     NOT NULL UNIQUE CHECK (LENGTH(serial_number) > 0),
    armament_category_id  smallint NOT NULL REFERENCES armament_categories (id),
    military_formation_id integer  NOT NULL REFERENCES military_formations (id)
);

/* Категории сооружений */
CREATE TABLE military_building_categories (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Сооружения */
CREATE TABLE military_buildings (
    id                            serial PRIMARY KEY,
    name                          text     NOT NULL CHECK (LENGTH(name) > 0),
    military_building_category_id smallint NOT NULL REFERENCES military_building_categories (id),
    military_formation_id         integer  NOT NULL REFERENCES military_formations (id),
    UNIQUE (name, military_building_category_id, military_formation_id)
);
