DROP TABLE IF EXISTS military_formations;
DROP TABLE IF EXISTS military_formation_categories;
DROP TABLE IF EXISTS military_districts__subjects;
DROP TABLE IF EXISTS military_districts;
DROP TABLE IF EXISTS dislocations;
DROP TABLE IF EXISTS dislocation_categories;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS military_men__military_specialties;
DROP TABLE IF EXISTS military_men;
DROP TABLE IF EXISTS military_specialties;
DROP TABLE IF EXISTS ranks__rank_categories;
DROP TABLE IF EXISTS ranks;
DROP TABLE IF EXISTS rank_categories;
DROP TABLE IF EXISTS staff_categories;

/**
  * --------------- *
  * Создание таблиц *
  * --------------- *
 */

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
    name              text NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    staff_category_id smallserial REFERENCES staff_categories (id)
);

/* Связь званий с категориями званий */
CREATE TABLE ranks__rank_categories (
    rank_id          smallserial REFERENCES ranks (id),
    rank_category_id smallserial REFERENCES rank_categories (id),
    PRIMARY KEY (rank_id, rank_category_id)
);

/* Воинские специальности */
CREATE TABLE military_specialties (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Военнослужащие */
CREATE TABLE military_men (
    id            serial PRIMARY KEY,
    second_name   text   NOT NULL CHECK (LENGTH(second_name) > 0),
    first_name    text   NOT NULL CHECK (LENGTH(first_name) > 0),
    patronymic    text CHECK (LENGTH(patronymic) > 0),
    date_of_birth date CHECK (date_of_birth <= NOW()),
    date_of_award date CHECK (date_of_award <= NOW()),
    rank_id       SERIAL NOT NULL REFERENCES ranks (id)
);

/* Связь военнослужащих с воинскими специальностями */
CREATE TABLE military_men__military_specialties (
    military_man_id       serial REFERENCES military_men (id),
    military_specialty_id serial REFERENCES military_specialties (id),
    PRIMARY KEY (military_man_id, military_specialty_id)
);

/* Субъекты РФ */
CREATE TABLE subjects (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Категории дислокаций (Город, посёлок и т.д.) */
CREATE TABLE dislocation_categories (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Дислокации */
CREATE TABLE dislocations (
    id                      serial PRIMARY KEY,
    name                    text NOT NULL CHECK (LENGTH(name) > 0),
    dislocation_category_id smallserial REFERENCES dislocation_categories (id),
    subject_id              smallserial REFERENCES subjects (id)
);

/* Военные округа */
CREATE TABLE military_districts (
    id                          smallserial PRIMARY KEY,
    name                        text NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    date_of_formation           date NOT NULL CHECK (date_of_formation < NOW()),
    headquarters_dislocation_id serial REFERENCES dislocations (id),
    commander_id                serial REFERENCES military_men (id)
);

/* Связь военных округов с субъектами РФ */
CREATE TABLE military_districts__subjects (
    military_district_id serial REFERENCES military_districts (id),
    subject_id           serial REFERENCES subjects (id),
    PRIMARY KEY (military_district_id, subject_id)
);

/* Категории воинских формирований (Армия, бригада, воинская часть и т.д.) */
CREATE TABLE military_formation_categories (
    id   smallserial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

/* Воинские формирования */
CREATE TABLE military_formations (
    id                             serial PRIMARY KEY,
    name                           text NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    military_formation_category_id smallserial REFERENCES military_formation_categories (id),
    date_of_formation              date NOT NULL CHECK (date_of_formation < NOW()),
    commander_id                   serial REFERENCES military_men (id),
    dislocation_id                 serial REFERENCES dislocations (id),
    parent_id                      serial REFERENCES military_formations (id)
);

/**
  * ----------------- *
  * Заполнение таблиц *
  * ----------------- *
 */

/* Категории составов */
INSERT INTO staff_categories VALUES (DEFAULT, 'Высший офицерский состав');
INSERT INTO staff_categories VALUES (DEFAULT, 'Старший офицерский состав');
INSERT INTO staff_categories VALUES (DEFAULT, 'Младший офицерский состав');
INSERT INTO staff_categories VALUES (DEFAULT, 'Прапорщики и мичманы');
INSERT INTO staff_categories VALUES (DEFAULT, 'Сержантский состав');
INSERT INTO staff_categories VALUES (DEFAULT, 'Рядовой состав');

/* Категории званий */
INSERT INTO rank_categories VALUES (DEFAULT, 'Войсковое');
INSERT INTO rank_categories VALUES (DEFAULT, 'Корабельное');

/* Звания */
INSERT INTO ranks VALUES (DEFAULT, 'Маршал РФ', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Генерал армии', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Генерал-полковник', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Генерал-лейтенант', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Генерал-майор', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Полковник', 2);
INSERT INTO ranks VALUES (DEFAULT, 'Подполковник', 2);
INSERT INTO ranks VALUES (DEFAULT, 'Майор', 2);
INSERT INTO ranks VALUES (DEFAULT, 'Капитан', 3);
INSERT INTO ranks VALUES (DEFAULT, 'Старший лейтенант', 3);
INSERT INTO ranks VALUES (DEFAULT, 'Лейтенант', 3);
INSERT INTO ranks VALUES (DEFAULT, 'Младший лейтенант', 3);
INSERT INTO ranks VALUES (DEFAULT, 'Старший прапорщик', 4);
INSERT INTO ranks VALUES (DEFAULT, 'Прапорщик', 4);
INSERT INTO ranks VALUES (DEFAULT, 'Старшина', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Старший сержант', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Сержант', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Младший сержант', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Ефрейтор', 6);
INSERT INTO ranks VALUES (DEFAULT, 'Рядовой', 6);
INSERT INTO ranks VALUES (DEFAULT, 'Адмирал флота', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Адмирал', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Вице-адмирал', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Контр-адмирал', 1);
INSERT INTO ranks VALUES (DEFAULT, 'Капитан 1 ранга', 2);
INSERT INTO ranks VALUES (DEFAULT, 'Капитан 2 ранга', 2);
INSERT INTO ranks VALUES (DEFAULT, 'Капитан 3 ранга', 2);
INSERT INTO ranks VALUES (DEFAULT, 'Капитан-лейтенант', 3);
INSERT INTO ranks VALUES (DEFAULT, 'Старший мичман', 4);
INSERT INTO ranks VALUES (DEFAULT, 'Мичман', 4);
INSERT INTO ranks VALUES (DEFAULT, 'Главный корабельный старшина', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Главный старшина', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Старшина 1 статьи', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Старшина 2 статьи', 5);
INSERT INTO ranks VALUES (DEFAULT, 'Старший матрос', 6);
INSERT INTO ranks VALUES (DEFAULT, 'Матрос', 6);

/* Связь званий с категориями званий */
INSERT INTO ranks__rank_categories VALUES (1, 1);
INSERT INTO ranks__rank_categories VALUES (1, 2);
INSERT INTO ranks__rank_categories VALUES (2, 1);
INSERT INTO ranks__rank_categories VALUES (3, 1);
INSERT INTO ranks__rank_categories VALUES (4, 1);
INSERT INTO ranks__rank_categories VALUES (5, 1);
INSERT INTO ranks__rank_categories VALUES (6, 1);
INSERT INTO ranks__rank_categories VALUES (7, 1);
INSERT INTO ranks__rank_categories VALUES (8, 1);
INSERT INTO ranks__rank_categories VALUES (9, 1);
INSERT INTO ranks__rank_categories VALUES (10, 1);
INSERT INTO ranks__rank_categories VALUES (10, 2);
INSERT INTO ranks__rank_categories VALUES (11, 1);
INSERT INTO ranks__rank_categories VALUES (11, 2);
INSERT INTO ranks__rank_categories VALUES (12, 1);
INSERT INTO ranks__rank_categories VALUES (12, 2);
INSERT INTO ranks__rank_categories VALUES (13, 1);
INSERT INTO ranks__rank_categories VALUES (14, 1);
INSERT INTO ranks__rank_categories VALUES (15, 1);
INSERT INTO ranks__rank_categories VALUES (16, 1);
INSERT INTO ranks__rank_categories VALUES (17, 1);
INSERT INTO ranks__rank_categories VALUES (18, 1);
INSERT INTO ranks__rank_categories VALUES (19, 1);
INSERT INTO ranks__rank_categories VALUES (20, 1);
INSERT INTO ranks__rank_categories VALUES (21, 2);
INSERT INTO ranks__rank_categories VALUES (22, 2);
INSERT INTO ranks__rank_categories VALUES (23, 2);
INSERT INTO ranks__rank_categories VALUES (24, 2);
INSERT INTO ranks__rank_categories VALUES (25, 2);
INSERT INTO ranks__rank_categories VALUES (26, 2);
INSERT INTO ranks__rank_categories VALUES (27, 2);
INSERT INTO ranks__rank_categories VALUES (28, 2);
INSERT INTO ranks__rank_categories VALUES (29, 2);
INSERT INTO ranks__rank_categories VALUES (30, 2);
INSERT INTO ranks__rank_categories VALUES (31, 2);
INSERT INTO ranks__rank_categories VALUES (32, 2);
INSERT INTO ranks__rank_categories VALUES (33, 2);
INSERT INTO ranks__rank_categories VALUES (34, 2);
INSERT INTO ranks__rank_categories VALUES (35, 2);
INSERT INTO ranks__rank_categories VALUES (36, 2);

/* Воинские специальности */
INSERT INTO military_specialties VALUES (DEFAULT, 'Артиллерист');
INSERT INTO military_specialties VALUES (DEFAULT, 'Лётчик');
INSERT INTO military_specialties VALUES (DEFAULT, 'Переводчик');
INSERT INTO military_specialties VALUES (DEFAULT, 'Связист');
INSERT INTO military_specialties VALUES (DEFAULT, 'Десантник');
INSERT INTO military_specialties VALUES (DEFAULT, 'Зенитчик');
INSERT INTO military_specialties VALUES (DEFAULT, 'Пограничник');
INSERT INTO military_specialties VALUES (DEFAULT, 'Разведчик');
INSERT INTO military_specialties VALUES (DEFAULT, 'Ракетчик');
INSERT INTO military_specialties VALUES (DEFAULT, 'Сапёр');
INSERT INTO military_specialties VALUES (DEFAULT, 'Танкист');
INSERT INTO military_specialties VALUES (DEFAULT, 'Шифровальщик (Криптограф)');
INSERT INTO military_specialties VALUES (DEFAULT, 'Штурман');
INSERT INTO military_specialties VALUES (DEFAULT, 'Специалист по обеспечению');

/* Военнослужащие */
INSERT INTO military_men VALUES (DEFAULT, 'Журавлёв', 'Александр', 'Александрович', '2-dec-1965', '22-feb-2017', 10);
INSERT INTO military_men VALUES (DEFAULT, 'Дворников', 'Александр', 'Владимирович', '22-aug-1961', '23-jun-2020', 2);
INSERT INTO military_men VALUES (DEFAULT, 'Лапин', 'Александр', 'Павлович', '1-jan-1964', '1-feb-2019', 3);
INSERT INTO military_men VALUES (DEFAULT, 'Чайко', 'Александр', 'Юрьевич', '27-jul-1971', '11-jun-2021', 3);
INSERT INTO military_men VALUES (DEFAULT, 'Моисеев', 'Александр', 'Алексеевич', '16-apr-1962', '10-dec-2020', 22);

/* Связь военнослужащих с воинскими специальностями */
INSERT INTO military_men__military_specialties VALUES (1, 1);
INSERT INTO military_men__military_specialties VALUES (1, 9);
INSERT INTO military_men__military_specialties VALUES (1, 11);
INSERT INTO military_men__military_specialties VALUES (2, 8);
INSERT INTO military_men__military_specialties VALUES (2, 1);
INSERT INTO military_men__military_specialties VALUES (3, 3);
INSERT INTO military_men__military_specialties VALUES (3, 13);
INSERT INTO military_men__military_specialties VALUES (4, 8);
INSERT INTO military_men__military_specialties VALUES (4, 11);
INSERT INTO military_men__military_specialties VALUES (4, 14);

/* Субъекты РФ */
INSERT INTO subjects VALUES (DEFAULT, 'Алтайский край');
INSERT INTO subjects VALUES (DEFAULT, 'Амурская область');
INSERT INTO subjects VALUES (DEFAULT, 'Архангельская область');
INSERT INTO subjects VALUES (DEFAULT, 'Астраханская область');
INSERT INTO subjects VALUES (DEFAULT, 'Белгородская область');
INSERT INTO subjects VALUES (DEFAULT, 'Брянская область');
INSERT INTO subjects VALUES (DEFAULT, 'Владимирская область');
INSERT INTO subjects VALUES (DEFAULT, 'Волгоградская область');
INSERT INTO subjects VALUES (DEFAULT, 'Вологодская область');
INSERT INTO subjects VALUES (DEFAULT, 'Воронежская область');
INSERT INTO subjects VALUES (DEFAULT, 'Еврейская автономная область');
INSERT INTO subjects VALUES (DEFAULT, 'Забайкальский край');
INSERT INTO subjects VALUES (DEFAULT, 'Ивановская область');
INSERT INTO subjects VALUES (DEFAULT, 'Иркутская область');
INSERT INTO subjects VALUES (DEFAULT, 'Кабардино-Балкарская Республика');
INSERT INTO subjects VALUES (DEFAULT, 'Калининградская область');
INSERT INTO subjects VALUES (DEFAULT, 'Калужская область');
INSERT INTO subjects VALUES (DEFAULT, 'Камчатский край');
INSERT INTO subjects VALUES (DEFAULT, 'Карачаево-Черкесская Республика');
INSERT INTO subjects VALUES (DEFAULT, 'Кемеровская область');
INSERT INTO subjects VALUES (DEFAULT, 'Кировская область');
INSERT INTO subjects VALUES (DEFAULT, 'Костромская область');
INSERT INTO subjects VALUES (DEFAULT, 'Краснодарский край');
INSERT INTO subjects VALUES (DEFAULT, 'Красноярский край');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Крым');
INSERT INTO subjects VALUES (DEFAULT, 'Курганская область');
INSERT INTO subjects VALUES (DEFAULT, 'Курская область');
INSERT INTO subjects VALUES (DEFAULT, 'Ленинградская область');
INSERT INTO subjects VALUES (DEFAULT, 'Липецкая область');
INSERT INTO subjects VALUES (DEFAULT, 'Магаданская область');
INSERT INTO subjects VALUES (DEFAULT, 'Москва');
INSERT INTO subjects VALUES (DEFAULT, 'Московская область');
INSERT INTO subjects VALUES (DEFAULT, 'Мурманская область');
INSERT INTO subjects VALUES (DEFAULT, 'Ненецкий автономный округ');
INSERT INTO subjects VALUES (DEFAULT, 'Нижегородская область');
INSERT INTO subjects VALUES (DEFAULT, 'Новгородская область');
INSERT INTO subjects VALUES (DEFAULT, 'Новосибирская область');
INSERT INTO subjects VALUES (DEFAULT, 'Омская область');
INSERT INTO subjects VALUES (DEFAULT, 'Оренбургская область');
INSERT INTO subjects VALUES (DEFAULT, 'Орловская область');
INSERT INTO subjects VALUES (DEFAULT, 'Пензенская область');
INSERT INTO subjects VALUES (DEFAULT, 'Пермский край');
INSERT INTO subjects VALUES (DEFAULT, 'Приморский край');
INSERT INTO subjects VALUES (DEFAULT, 'Псковская область');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Адыгея');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Алтай');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Башкортостан');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Бурятия');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Дагестан');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Ингушетия');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Калмыкия');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Карелия');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Коми');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Марий Эл');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Мордовия');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Саха (Якутия)');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Северная Осетия - Алания');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Татарстан');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Тыва');
INSERT INTO subjects VALUES (DEFAULT, 'Республика Хакасия');
INSERT INTO subjects VALUES (DEFAULT, 'Ростовская область');
INSERT INTO subjects VALUES (DEFAULT, 'Рязанская область');
INSERT INTO subjects VALUES (DEFAULT, 'Самарская область');
INSERT INTO subjects VALUES (DEFAULT, 'Санкт-Петербург');
INSERT INTO subjects VALUES (DEFAULT, 'Саратовская область');
INSERT INTO subjects VALUES (DEFAULT, 'Сахалинская область');
INSERT INTO subjects VALUES (DEFAULT, 'Свердловская область');
INSERT INTO subjects VALUES (DEFAULT, 'Севастополь');
INSERT INTO subjects VALUES (DEFAULT, 'Смоленская область');
INSERT INTO subjects VALUES (DEFAULT, 'Ставропольский край');
INSERT INTO subjects VALUES (DEFAULT, 'Тамбовская область');
INSERT INTO subjects VALUES (DEFAULT, 'Тверская область');
INSERT INTO subjects VALUES (DEFAULT, 'Томская область');
INSERT INTO subjects VALUES (DEFAULT, 'Тульская область');
INSERT INTO subjects VALUES (DEFAULT, 'Тюменская область');
INSERT INTO subjects VALUES (DEFAULT, 'Удмуртская Республика');
INSERT INTO subjects VALUES (DEFAULT, 'Ульяновская область');
INSERT INTO subjects VALUES (DEFAULT, 'Хабаровский край');
INSERT INTO subjects VALUES (DEFAULT, 'Ханты-Мансийский автономный округ - Югра');
INSERT INTO subjects VALUES (DEFAULT, 'Челябинская область');
INSERT INTO subjects VALUES (DEFAULT, 'Чеченская Республика');
INSERT INTO subjects VALUES (DEFAULT, 'Чувашская Республика');
INSERT INTO subjects VALUES (DEFAULT, 'Чукотский автономный округ');
INSERT INTO subjects VALUES (DEFAULT, 'Ямало-Ненецкий автономный округ');
INSERT INTO subjects VALUES (DEFAULT, 'Ярославская область');

/* Категории дислокаций (Город, посёлок и т.д.) */
INSERT INTO dislocation_categories VALUES (DEFAULT, 'Город');
INSERT INTO dislocation_categories VALUES (DEFAULT, 'Деревня');
INSERT INTO dislocation_categories VALUES (DEFAULT, 'Посёлок');

/* Дислокации */
INSERT INTO dislocations VALUES (DEFAULT, 'Санкт-Петербург', 1, 64);
INSERT INTO dislocations VALUES (DEFAULT, 'Ростов-на-Дону', 1, 61);
INSERT INTO dislocations VALUES (DEFAULT, 'Екатеринбург', 1, 67);
INSERT INTO dislocations VALUES (DEFAULT, 'Хабаровск', 1, 78);
INSERT INTO dislocations VALUES (DEFAULT, 'Североморск', 1, 33);

/* Военные округа */
INSERT INTO military_districts VALUES (DEFAULT, 'Западный военный округ', '21-oct-2010', 1, 1);
INSERT INTO military_districts VALUES (DEFAULT, 'Южный военный округ', '1-sep-2010', 2, 2);
INSERT INTO military_districts VALUES (DEFAULT, 'Центральный военный округ', '1-dec-2010', 3, 3);
INSERT INTO military_districts VALUES (DEFAULT, 'Восточный военный округ', '21-oct-2010', 4, 4);
INSERT INTO military_districts VALUES (DEFAULT, 'Северный флот', '1-dec-2014', 5, 5);

/* Связь военных округов с субъектами РФ */
INSERT INTO military_districts__subjects
SELECT military_districts.id,
       subjects.id
FROM military_districts,
     subjects
WHERE (military_districts.name = 'Западный военный округ' AND (subjects.name = 'Республика Карелия' OR
                                                               subjects.name = 'Белгородская область' OR
                                                               subjects.name = 'Брянская область' OR
                                                               subjects.name = 'Владимирская область' OR
                                                               subjects.name = 'Вологодская область' OR
                                                               subjects.name = 'Воронежская область' OR
                                                               subjects.name = 'Ивановская область' OR
                                                               subjects.name = 'Калининградская область' OR
                                                               subjects.name = 'Калужская область' OR
                                                               subjects.name = 'Костромская область' OR
                                                               subjects.name = 'Курская область' OR
                                                               subjects.name = 'Ленинградская область' OR
                                                               subjects.name = 'Липецкая область' OR
                                                               subjects.name = 'Московская область' OR
                                                               subjects.name = 'Нижегородская область' OR
                                                               subjects.name = 'Новгородская область' OR
                                                               subjects.name = 'Орловская область' OR
                                                               subjects.name = 'Псковская область' OR
                                                               subjects.name = 'Рязанская область' OR
                                                               subjects.name = 'Смоленская область' OR
                                                               subjects.name = 'Тамбовская область' OR
                                                               subjects.name = 'Тверская область' OR
                                                               subjects.name = 'Тульская область' OR
                                                               subjects.name = 'Ярославская область' OR
                                                               subjects.name = 'Москва' OR
                                                               subjects.name = 'Санкт-Петербург'))
   OR (military_districts.name = 'Южный военный округ' AND (subjects.name = 'Республика Адыгея' OR
                                                            subjects.name = 'Республика Дагестан' OR
                                                            subjects.name = 'Республика Ингушетия' OR
                                                            subjects.name = 'Кабардино-Балкарская Республика' OR
                                                            subjects.name = 'Республика Калмыкия' OR
                                                            subjects.name = 'Карачаево-Черкесская Республика' OR
                                                            subjects.name = 'Республика Крым' OR
                                                            subjects.name = 'Республика Северная Осетия - Алания' OR
                                                            subjects.name = 'Чеченская Республика' OR
                                                            subjects.name = 'Краснодарский край' OR
                                                            subjects.name = 'Ставропольский край' OR
                                                            subjects.name = 'Астраханская область' OR
                                                            subjects.name = 'Волгоградская область' OR
                                                            subjects.name = 'Ростовская область' OR
                                                            subjects.name = 'Севастополь'))
   OR (military_districts.name = 'Центральный военный округ' AND (subjects.name = 'Республика Алтай' OR
                                                                  subjects.name = 'Республика Башкортостан' OR
                                                                  subjects.name = 'Республика Марий Эл' OR
                                                                  subjects.name = 'Республика Мордовия' OR
                                                                  subjects.name = 'Республика Татарстан' OR
                                                                  subjects.name = 'Республика Тыва' OR
                                                                  subjects.name = 'Удмуртская Республика' OR
                                                                  subjects.name = 'Республика Хакасия' OR
                                                                  subjects.name = 'Чувашская Республика' OR
                                                                  subjects.name = 'Алтайский край' OR
                                                                  subjects.name = 'Красноярский край' OR
                                                                  subjects.name = 'Пермский край' OR
                                                                  subjects.name = 'Иркутская область' OR
                                                                  subjects.name = 'Кемеровская область' OR
                                                                  subjects.name = 'Кировская область' OR
                                                                  subjects.name = 'Курганская область' OR
                                                                  subjects.name = 'Новосибирская область' OR
                                                                  subjects.name = 'Омская область' OR
                                                                  subjects.name = 'Оренбургская область' OR
                                                                  subjects.name = 'Пензенская область' OR
                                                                  subjects.name = 'Самарская область' OR
                                                                  subjects.name = 'Саратовская область' OR
                                                                  subjects.name = 'Свердловская область' OR
                                                                  subjects.name = 'Томская область' OR
                                                                  subjects.name = 'Тюменская область' OR
                                                                  subjects.name = 'Ульяновская область' OR
                                                                  subjects.name = 'Челябинская область' OR
                                                                  subjects.name = 'Ханты-Мансийский автономный округ - Югра' OR
                                                                  subjects.name = 'Ямало-Ненецкий автономный округ'))
   OR (military_districts.name = 'Восточный военный округ' AND (subjects.name = 'Республика Бурятия' OR
                                                                subjects.name = 'Республика Саха (Якутия)' OR
                                                                subjects.name = 'Забайкальский край' OR
                                                                subjects.name = 'Камчатский край' OR
                                                                subjects.name = 'Приморский край' OR
                                                                subjects.name = 'Хабаровский край' OR
                                                                subjects.name = 'Амурская область' OR
                                                                subjects.name = 'Магаданская область' OR
                                                                subjects.name = 'Сахалинская область' OR
                                                                subjects.name = 'Еврейская автономная область' OR
                                                                subjects.name = 'Чукотский автономный округ'))
   OR (military_districts.name = 'Северный флот' AND (subjects.name = 'Республика Коми' OR
                                                      subjects.name = 'Архангельская область' OR
                                                      subjects.name = 'Мурманская область' OR
                                                      subjects.name = 'Ненецкий автономный округ'));


/**
  * ------- *
  * Запросы *
  * ------- *
 */

SELECT military_districts.name, subjects.name
FROM military_districts,
     subjects,
     military_districts__subjects
WHERE military_districts__subjects.military_district_id = military_districts.id
  AND military_districts__subjects.subject_id = subjects.id
ORDER BY military_districts.id;


SELECT military_men.second_name || ' ' || military_men.first_name || ' ' || military_men.patronymic, military_specialties.name
FROM military_men,
     military_specialties,
     military_men__military_specialties
WHERE military_men.id = military_men__military_specialties.military_man_id
  AND military_specialties.id = military_men__military_specialties.military_specialty_id;


SELECT military_men.second_name || ' ' || military_men.first_name || ' ' || military_men.patronymic AS name,
       military_men.date_of_award,
       ranks.name                                                                                   AS rank_name,
       rank_categories.name                                                                           AS rank_category,
       staff_categories.name                                                                        AS staff_category
FROM military_men,
     ranks,
     rank_categories,
     ranks__rank_categories,
     staff_categories
WHERE military_men.rank_id = ranks.id
  AND ranks.id = ranks__rank_categories.rank_id
  AND rank_categories.id = ranks__rank_categories.rank_category_id
  AND ranks.staff_category_id = staff_categories.id;

SELECT ranks.name AS rank, staff_categories.name AS staff_category, rank_categories.name AS rank_category
FROM ranks,
     staff_categories,
     rank_categories,
     ranks__rank_categories
WHERE ranks.id = ranks__rank_categories.rank_id
  AND rank_categories.id = ranks__rank_categories.rank_category_id
  AND ranks.staff_category_id = staff_categories.id
ORDER BY staff_categories.id, ranks.id, rank_categories.id;
