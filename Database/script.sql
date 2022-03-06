DROP TABLE IF EXISTS military_armies;
DROP TABLE IF EXISTS military_districts__subjects;
DROP TABLE IF EXISTS dislocations;
DROP TABLE IF EXISTS military_personnel;

-- Субъекты РФ
DROP TABLE IF EXISTS subjects;
CREATE TABLE subjects (
    id   serial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

-- Тип дислокации (Город, населенный пункт и т.д.)
DROP TABLE IF EXISTS dislocation_types;
CREATE TABLE dislocation_types (
    id   serial PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (LENGTH(name) > 0)
);

-- Дислокации
CREATE TABLE dislocations (
    id         serial PRIMARY KEY,
    name       text   NOT NULL CHECK (LENGTH(name) > 0),
    type_id    serial NOT NULL,
    subject_id serial NOT NULL,
    FOREIGN KEY (type_id) REFERENCES dislocation_types (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

-- Военные округа
DROP TABLE IF EXISTS military_districts;
CREATE TABLE military_districts (
    id                serial PRIMARY KEY,
    name              text NOT NULL UNIQUE CHECK (LENGTH(name) > 0),
    headquarters      text NOT NULL UNIQUE CHECK (LENGTH(headquarters) > 0),
    date_of_formation date NOT NULL
);

-- Связи военных округов с субъектами РФ
CREATE TABLE military_districts__subjects (
    military_district_id serial NOT NULL,
    subject_id           serial NOT NULL,
    FOREIGN KEY (military_district_id) REFERENCES military_districts (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

-- Армии
CREATE TABLE military_armies (
    id                serial PRIMARY KEY,
    name              text   NOT NULL CHECK (LENGTH(name) > 0),
    date_of_formation date   NOT NULL,
    dislocation_id    serial NOT NULL,
    district_id       serial NOT NULL,
    FOREIGN KEY (dislocation_id) REFERENCES dislocations (id),
    FOREIGN KEY (district_id) REFERENCES military_districts (id)
);

-- Дивизии
DROP TABLE IF EXISTS military_divisions;
CREATE TABLE military_divisions (
    id      serial PRIMARY KEY,
    name    text NOT NULL CHECK (LENGTH(name) > 0),
    address text NOT NULL CHECK (LENGTH(address) > 0)
);

-- Звания
DROP TABLE IF EXISTS ranks;
CREATE TABLE ranks (
    id   serial PRIMARY KEY,
    name text NOT NULL CHECK (LENGTH(name) > 0)
);

-- Военнослужащие
DROP TABLE IF EXISTS military_personnel;
CREATE TABLE military_personnel (
    id      serial PRIMARY KEY,
    name    text   NOT NULL CHECK (LENGTH(name) > 0),
    rank_id serial NOT NULL,
    FOREIGN KEY (rank_id) REFERENCES ranks (id)
);

-- Маршалы
DROP TABLE IF EXISTS marshals;
CREATE TABLE marshals (
    id                    serial PRIMARY KEY,
    military_personnel_id serial NOT NULL
);

-- Генералы армии
DROP TABLE IF EXISTS army_generals;
CREATE TABLE army_generals (
    id                    serial PRIMARY KEY,
    military_personnel_id serial NOT NULL
);

-- Генерал-полковники
DROP TABLE IF EXISTS colonel_generals;
CREATE TABLE colonel_generals (
    id                    serial PRIMARY KEY,
    military_personnel_id serial NOT NULL
);

-- Генерал-лейтенанты
DROP TABLE IF EXISTS lieutenant_generals;
CREATE TABLE lieutenant_generals (
    id                    serial PRIMARY KEY,
    military_personnel_id serial NOT NULL,
    date_of_award         date   NOT NULL
);

-- Воинские специальности
DROP TABLE IF EXISTS military_specialties;
CREATE TABLE military_specialties (
    id   serial PRIMARY KEY,
    name text NOT NULL CHECK (LENGTH(name) > 0)
);

INSERT INTO subjects
VALUES (DEFAULT, 'Алтайский край'),
       (DEFAULT, 'Амурская область'),
       (DEFAULT, 'Архангельская область'),
       (DEFAULT, 'Астраханская область'),
       (DEFAULT, 'Белгородская область'),
       (DEFAULT, 'Брянская область'),
       (DEFAULT, 'Владимирская область'),
       (DEFAULT, 'Волгоградская область'),
       (DEFAULT, 'Вологодская область'),
       (DEFAULT, 'Воронежская область'),
       (DEFAULT, 'Еврейская автономная область'),
       (DEFAULT, 'Забайкальский край'),
       (DEFAULT, 'Ивановская область'),
       (DEFAULT, 'Иркутская область'),
       (DEFAULT, 'Кабардино-Балкарская Республика'),
       (DEFAULT, 'Калининградская область'),
       (DEFAULT, 'Калужская область'),
       (DEFAULT, 'Камчатский край'),
       (DEFAULT, 'Карачаево-Черкесская Республика'),
       (DEFAULT, 'Кемеровская область'),
       (DEFAULT, 'Кировская область'),
       (DEFAULT, 'Костромская область'),
       (DEFAULT, 'Краснодарский край'),
       (DEFAULT, 'Красноярский край'),
       (DEFAULT, 'Республика Крым'),
       (DEFAULT, 'Курганская область'),
       (DEFAULT, 'Курская область'),
       (DEFAULT, 'Ленинградская область'),
       (DEFAULT, 'Липецкая область'),
       (DEFAULT, 'Магаданская область'),
       (DEFAULT, 'Москва'),
       (DEFAULT, 'Московская область'),
       (DEFAULT, 'Мурманская область'),
       (DEFAULT, 'Ненецкий автономный округ'),
       (DEFAULT, 'Нижегородская область'),
       (DEFAULT, 'Новгородская область'),
       (DEFAULT, 'Новосибирская область'),
       (DEFAULT, 'Омская область'),
       (DEFAULT, 'Оренбургская область'),
       (DEFAULT, 'Орловская область'),
       (DEFAULT, 'Пензенская область'),
       (DEFAULT, 'Пермский край'),
       (DEFAULT, 'Приморский край'),
       (DEFAULT, 'Псковская область'),
       (DEFAULT, 'Республика Адыгея'),
       (DEFAULT, 'Республика Алтай'),
       (DEFAULT, 'Республика Башкортостан'),
       (DEFAULT, 'Республика Бурятия'),
       (DEFAULT, 'Республика Дагестан'),
       (DEFAULT, 'Республика Ингушетия'),
       (DEFAULT, 'Республика Калмыкия'),
       (DEFAULT, 'Республика Карелия'),
       (DEFAULT, 'Республика Коми'),
       (DEFAULT, 'Республика Марий Эл'),
       (DEFAULT, 'Республика Мордовия'),
       (DEFAULT, 'Республика Саха (Якутия)'),
       (DEFAULT, 'Республика Северная Осетия - Алания'),
       (DEFAULT, 'Республика Татарстан'),
       (DEFAULT, 'Республика Тыва'),
       (DEFAULT, 'Республика Хакасия'),
       (DEFAULT, 'Ростовская область'),
       (DEFAULT, 'Рязанская область'),
       (DEFAULT, 'Самарская область'),
       (DEFAULT, 'Санкт-Петербург'),
       (DEFAULT, 'Саратовская область'),
       (DEFAULT, 'Сахалинская область'),
       (DEFAULT, 'Свердловская область'),
       (DEFAULT, 'Севастополь'),
       (DEFAULT, 'Смоленская область'),
       (DEFAULT, 'Ставропольский край'),
       (DEFAULT, 'Тамбовская область'),
       (DEFAULT, 'Тверская область'),
       (DEFAULT, 'Томская область'),
       (DEFAULT, 'Тульская область'),
       (DEFAULT, 'Тюменская область'),
       (DEFAULT, 'Удмуртская Республика'),
       (DEFAULT, 'Ульяновская область'),
       (DEFAULT, 'Хабаровский край'),
       (DEFAULT, 'Ханты-Мансийский автономный округ - Югра'),
       (DEFAULT, 'Челябинская область'),
       (DEFAULT, 'Чеченская Республика'),
       (DEFAULT, 'Чувашская Республика'),
       (DEFAULT, 'Чукотский автономный округ'),
       (DEFAULT, 'Ямало-Ненецкий автономный округ'),
       (DEFAULT, 'Ярославская область');

INSERT INTO dislocation_types
VALUES (DEFAULT, 'Город'),
       (DEFAULT, 'Населённый пункт');

INSERT INTO military_districts
VALUES (DEFAULT, 'Западный военный округ', 'Санкт-Петербург', '21-oct-2010'),
       (DEFAULT, 'Южный военный округ', 'Ростов-на-Дону', '1-sep-2010'),
       (DEFAULT, 'Центральный военный округ', 'Екатеринбург', '1-dec-2010'),
       (DEFAULT, 'Восточный военный округ', 'Хабаровск', '21-oct-2010'),
       (DEFAULT, 'Северный флот', 'Североморск', '1-dec-2014');

INSERT INTO military_districts__subjects
SELECT military_districts.id, subjects.id
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

INSERT INTO dislocations
VALUES (DEFAULT, 'Чита', 1, 12),
       (DEFAULT, 'Одинцово', 1, 32),
       (DEFAULT, 'Агалатово', 2, 28),
       (DEFAULT, 'Уссурийск', 1, 43);

INSERT INTO military_armies
VALUES (DEFAULT, '29-я общевойсковая армия', '23-aug-2010', 1, 4),
       (DEFAULT, '1-я гвардейская танковая Краснознамённая армия', '13-nov-2014', 2, 1),
       (DEFAULT, '6-я общевойсковая Краснознамённая армия', '9-aug-2010', 3, 1),
       (DEFAULT, '5-я общевойсковая Краснознамённая армия', '1-jul-2006', 4, 4);

INSERT INTO ranks
VALUES (DEFAULT, 'Маршал'),
       (DEFAULT, 'Генерал армии'),
       (DEFAULT, 'Генерал-полковник'),
       (DEFAULT, 'Генерал-лейтенант'),
       (DEFAULT, 'Генерал-майор'),
       (DEFAULT, 'Полковник'),
       (DEFAULT, 'Подполковник'),
       (DEFAULT, 'Майор'),
       (DEFAULT, 'Капитан'),
       (DEFAULT, 'Старший лейтенант'),
       (DEFAULT, 'Лейтенант'),
       (DEFAULT, 'Младший лейтенант'),
       (DEFAULT, 'Старший прапорщик'),
       (DEFAULT, 'Прапорщик'),
       (DEFAULT, 'Старшина'),
       (DEFAULT, 'Старший сержант'),
       (DEFAULT, 'Сержант'),
       (DEFAULT, 'Младший сержант'),
       (DEFAULT, 'Ефрейтор'),
       (DEFAULT, 'Рядовой');

INSERT INTO military_specialties
VALUES (DEFAULT, 'Артиллерист'),
       (DEFAULT, 'Лётчик'),
       (DEFAULT, 'Переводчик'),
       (DEFAULT, 'Связист'),
       (DEFAULT, 'Десантник'),
       (DEFAULT, 'Зенитчик'),
       (DEFAULT, 'Пограничник'),
       (DEFAULT, 'Разведчик'),
       (DEFAULT, 'Ракетчик'),
       (DEFAULT, 'Сапёр'),
       (DEFAULT, 'Танкист'),
       (DEFAULT, 'Шифровальщик (Криптограф)'),
       (DEFAULT, 'Штурман'),
       (DEFAULT, 'Специалист по обеспечению');

INSERT INTO military_personnel
VALUES (DEFAULT, 'Кисель Сергей Александрович', 4),
       (DEFAULT, 'Ершов Владислав Николаевич', 4);

INSERT INTO lieutenant_generals
VALUES (DEFAULT, 1, '20-feb-2020'),
       (DEFAULT, 2, '18-feb-2021');

SELECT military_armies.name,
       military_armies.date_of_formation,
       dislocation_types.name  AS dislocation_type,
       dislocations.name       AS dislocation_name,
       subjects.name           AS subject_name,
       military_districts.name AS military_district_name
FROM military_armies,
     dislocation_types,
     dislocations,
     subjects,
     military_districts
WHERE military_armies.dislocation_id = dislocations.id
  AND dislocations.type_id = dislocation_types.id
  AND dislocations.subject_id = subjects.id
  AND military_armies.district_id = military_districts.id;

