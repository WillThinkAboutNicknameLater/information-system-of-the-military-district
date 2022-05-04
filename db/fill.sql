/**
  * Заполнение таблиц *
 */

/* Категории составов */
INSERT INTO staff_categories (id, name)
VALUES (DEFAULT, 'Высший офицерский состав'),
       (DEFAULT, 'Старший офицерский состав'),
       (DEFAULT, 'Младший офицерский состав'),
       (DEFAULT, 'Прапорщики и мичманы'),
       (DEFAULT, 'Сержантский состав'),
       (DEFAULT, 'Рядовой состав');

/* Категории званий */
INSERT INTO rank_categories (id, name)
VALUES (DEFAULT, 'Войсковое'),
       (DEFAULT, 'Корабельное');

/* Звания */
INSERT INTO ranks (id, name, staff_category_id, rank_category_id)
VALUES (DEFAULT, 'Маршал РФ', 1, 1),
       (DEFAULT, 'Генерал армии', 1, 1),
       (DEFAULT, 'Генерал-полковник', 1, 1),
       (DEFAULT, 'Генерал-лейтенант', 1, 1),
       (DEFAULT, 'Генерал-майор', 1, 1),
       (DEFAULT, 'Полковник', 2, 1),
       (DEFAULT, 'Подполковник', 2, 1),
       (DEFAULT, 'Майор', 2, 1),
       (DEFAULT, 'Капитан', 3, 1),
       (DEFAULT, 'Старший лейтенант', 3, 1),
       (DEFAULT, 'Старший лейтенант', 3, 2),
       (DEFAULT, 'Лейтенант', 3, 1),
       (DEFAULT, 'Лейтенант', 3, 2),
       (DEFAULT, 'Младший лейтенант', 3, 1),
       (DEFAULT, 'Младший лейтенант', 3, 2),
       (DEFAULT, 'Старший прапорщик', 4, 1),
       (DEFAULT, 'Прапорщик', 4, 1),
       (DEFAULT, 'Старшина', 5, 1),
       (DEFAULT, 'Старший сержант', 5, 1),
       (DEFAULT, 'Сержант', 5, 1),
       (DEFAULT, 'Младший сержант', 5, 1),
       (DEFAULT, 'Ефрейтор', 6, 1),
       (DEFAULT, 'Рядовой', 6, 1),
       (DEFAULT, 'Адмирал флота', 1, 2),
       (DEFAULT, 'Адмирал', 1, 2),
       (DEFAULT, 'Вице-адмирал', 1, 2),
       (DEFAULT, 'Контр-адмирал', 1, 2),
       (DEFAULT, 'Капитан 1 ранга', 2, 2),
       (DEFAULT, 'Капитан 2 ранга', 2, 2),
       (DEFAULT, 'Капитан 3 ранга', 2, 2),
       (DEFAULT, 'Капитан-лейтенант', 3, 2),
       (DEFAULT, 'Старший мичман', 4, 2),
       (DEFAULT, 'Мичман', 4, 2),
       (DEFAULT, 'Главный корабельный старшина', 5, 2),
       (DEFAULT, 'Главный старшина', 5, 2),
       (DEFAULT, 'Старшина 1 статьи', 5, 2),
       (DEFAULT, 'Старшина 2 статьи', 5, 2),
       (DEFAULT, 'Старший матрос', 6, 2),
       (DEFAULT, 'Матрос', 6, 2);

/* Воинские специальности */
INSERT INTO military_specialties (id, name)
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

/* Военнослужащие */
INSERT INTO military_men (id, second_name, first_name, patronymic, date_of_birth, date_of_award, identification_number, rank_id)
VALUES (DEFAULT, 'Журавлёв', 'Александр', 'Александрович', '2-dec-1965', '22-feb-2017', 'Э-452362', 3),
       (DEFAULT, 'Дворников', 'Александр', 'Владимирович', '22-aug-1961', '23-jun-2020', 'Э-138124', 2),
       (DEFAULT, 'Лапин', 'Александр', 'Павлович', '1-jan-1964', '1-feb-2019', 'Ф-452362', 3),
       (DEFAULT, 'Чайко', 'Александр', 'Юрьевич', '27-jul-1971', '11-jun-2021', 'Э-764532', 3),
       (DEFAULT, 'Моисеев', 'Александр', 'Алексеевич', '16-apr-1962', '10-dec-2020', 'Э-623742', 25),
       (DEFAULT, 'Кисель', 'Сергей', 'Александрович', '27-mar-1971', '20-feb-2020', 'А-543523', 4),
       (DEFAULT, 'Журавлёв', 'Евгений', 'Николаевич', '21-oct-1970', '5-may-2016', 'ЕИ-091231', 6),
       (DEFAULT, 'Гришин', 'Иван', 'Иванович', '4-jan-1969', '12-aug-2016', 'Э-092389', 6),
       (DEFAULT, 'Ершов', 'Владислав', 'Николаевич', '8-sep-1975', '18-feb-2021', 'Ф-451039', 4),
       (DEFAULT, 'Максимов', 'Сергей', 'Викторович', '5-nov-1975', '13-apr-2018', 'Э-462830', 6),
       (DEFAULT, 'Архипов', 'Андрей', 'Николаевич', '19-oct-1974', '20-mar-2017', 'ЕИ-410340', 6),
       (DEFAULT, 'Иванаев', 'Андрей', 'Сергеевич', '19-jan-1972', '11-jun-2019', 'Э-087291', 4),
       (DEFAULT, 'Власов', 'Вячеслав', 'Викторович', '9-nov-1970', '12-dec-2018', 'Ф-986382', 6),
       (DEFAULT, 'Рузинский', 'Андрей', 'Юрьевич', '24-oct-1970', '10-apr-2019', 'ЕИ-152712', 5),
       (DEFAULT, 'Петров', 'Иван', 'Васильевич', '12-aug-1969', '23-may-2016', 'А-492720', 6),
       (DEFAULT, 'Дёмин', 'Андрей', 'Геннадьевич', '29-mar-1965', '12-dec-2016', 'Э-037393', 4),
       (DEFAULT, 'Беляцкий', 'Дмитрий', 'Михайлович', '2-jan-1969', '31-jul-2019', 'ЕИ-349284', 5),
       (DEFAULT, 'Иванов', 'Василий', 'Петрович', '3-may-1972', '27-aug-2019', 'Ф-467290', 6),
       (DEFAULT, 'Васильев', 'Пётр', 'Иванович', '9-jul-1972', '13-sep-2018', 'Э-762292', 6),
       (DEFAULT, 'Бородин', 'Никита', 'Александрович', '30-dec-1969', '21-mar-2017', 'Ф-923892', 6),
       (DEFAULT, 'Грабчук', 'Сергей', 'Петрович', '7-apr-1970', '1-nov-2018', 'А-246171', 5),
       (DEFAULT, 'Попов', 'Владимир', 'Григорьевич', '8-sep-1971', '28-may-2018', 'ЕИ-219129', 6);

/* Связь военнослужащих с воинскими специальностями */
INSERT INTO military_men__military_specialties (military_man_id, military_specialty_id)
VALUES (1, 1),
       (1, 9),
       (1, 11),
       (2, 8),
       (2, 1),
       (3, 3),
       (3, 13),
       (4, 8),
       (4, 11),
       (4, 14);

/* Субъекты РФ */
INSERT INTO subjects (id, name)
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

/* Типы дислокаций */
INSERT INTO dislocation_types (id, name)
VALUES (DEFAULT, 'Город'),
       (DEFAULT, 'Посёлок'),
       (DEFAULT, 'Деревня');

/* Дислокации */
INSERT INTO dislocations (id, name, dislocation_type_id, subject_id)
VALUES (DEFAULT, 'Санкт-Петербург', 1, 64),
       (DEFAULT, 'Ростов-на-Дону', 1, 61),
       (DEFAULT, 'Екатеринбург', 1, 67),
       (DEFAULT, 'Хабаровск', 1, 78),
       (DEFAULT, 'Североморск', 1, 33),
       (DEFAULT, 'Одинцово', 1, 32),
       (DEFAULT, 'Наро-Фоминск', 1, 32),
       (DEFAULT, 'Красный Бор', 2, 69),
       (DEFAULT, 'Агалатово', 2, 28),
       (DEFAULT, 'Каменка', 2, 28),
       (DEFAULT, 'Луга', 1, 28),
       (DEFAULT, 'Воронеж', 1, 10),
       (DEFAULT, 'Маршала Жукова', 2, 27),
       (DEFAULT, 'Гусев', 1, 16),
       (DEFAULT, 'Калининград', 1, 16),
       (DEFAULT, 'Балашиха', 1, 32),
       (DEFAULT, 'Долгопрудный', 1, 32),
       (DEFAULT, 'Фуньково', 3, 32),
       (DEFAULT, 'Дубровка', 3, 32),
       (DEFAULT, 'Нестерово', 3, 32),
       (DEFAULT, 'Софрино', 2, 32),
       (DEFAULT, 'Клин', 1, 32);

/* Военные округа */
INSERT INTO military_districts (id, name, date_of_formation, headquarters_dislocation_id, commander_id)
VALUES (DEFAULT, 'Западный военный округ', '21-oct-2010', 1, 1),
       (DEFAULT, 'Южный военный округ', '1-sep-2010', 2, 2),
       (DEFAULT, 'Центральный военный округ', '1-dec-2010', 3, 3),
       (DEFAULT, 'Восточный военный округ', '21-oct-2010', 4, 4),
       (DEFAULT, 'Северный флот', '1-dec-2014', 5, 5);

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

/* Типы воинских формирований */
INSERT INTO military_formation_types (id, name)
VALUES (DEFAULT, 'Армия'),
       (DEFAULT, 'Дивизия'),
       (DEFAULT, 'Корпус'),
       (DEFAULT, 'Бригада'),
       (DEFAULT, 'Полк'),
       (DEFAULT, 'Рота'),
       (DEFAULT, 'Взвод'),
       (DEFAULT, 'Отделение');

/* Воинские формирования */
INSERT INTO military_formations (id, name, date_of_formation, military_formation_type_id, commander_id, dislocation_id, parent_id)
VALUES (DEFAULT, '1-я гвардейская танковая армия', '13-nov-2014', 1, 6, 6, NULL),
       (DEFAULT, '4-я гвардейская танковая дивизия', '1-may-2013', 2, 7, 7, 1),
       (DEFAULT, '49-я зенитная ракетная бригада', '14-nov-1967', 4, 8, 8, 1),
       (DEFAULT, '6-я общевойсковая армия', '9-aug-2010', 1, 9, 9, NULL),
       (DEFAULT, '138-я отдельная гвардейская мотострелковая бригада', '1-dec-1997', 4, 10, 10, 4),
       (DEFAULT, '25-я отдельная гвардейская мотострелковая бригада', '29-aug-1992', 4, 11, 11, 4),
       (DEFAULT, '20-я гвардейская общевойсковая армия', '18-mar-1945', 1, 12, 12, NULL),
       (DEFAULT, '53-я зенитная ракетная бригада', '1-oct-1967', 4, 13, 13, 7),
       (DEFAULT, '11-й армейский корпус', '1-apr-2016', 3, 14, 14, NULL),
       (DEFAULT, '7-й отдельный гвардейский мотострелковый полк', '1-jun-2002', 5, 15, 15, 9),
       (DEFAULT, '1-я армия противовоздушной и противоракетной обороны особого назначения', '1-aug-2015', 1, 16, 16, NULL),
       (DEFAULT, '4-я дивизия ПВО', '1-dec-2014', 2, 17, 17, 11),
       (DEFAULT, '93-й гвардейский зенитный ракетный полк', '12-nov-2013', 5, 18, 18, 12),
       (DEFAULT, '210-й зенитный ракетный полк', '12-aug-1998', 5, 19, 19, 12),
       (DEFAULT, '25-й радиотехнический полк', '9-feb-2007', 5, 20, 20, 12),
       (DEFAULT, '9-я дивизия ПВО', '1-oct-1998', 2, 21, 21, 11),
       (DEFAULT, '34-й полк связи', '15-jul-2003', 5, 22, 22, 16);

/* Связь военнослужащих с воинскими формированиями */
INSERT INTO military_men__military_formations (military_man_id, military_formation_id)
VALUES (6, 1),
       (7, 2),
       (8, 3),
       (9, 4),
       (10, 5),
       (11, 6),
       (12, 7),
       (13, 8),
       (14, 9),
       (15, 10),
       (16, 11),
       (17, 12),
       (18, 13),
       (19, 14),
       (20, 15),
       (21, 16),
       (22, 17);

/* Группы боевой техники */
INSERT INTO combat_vehicle_groups (id, name)
VALUES (DEFAULT, 'Бронетехника'),
       (DEFAULT, 'Артиллерийские орудия и тактические ракетные комплексы'),
       (DEFAULT, 'Противотанковые средства'),
       (DEFAULT, 'Средства разведки, управления и РЭП'),
       (DEFAULT, 'Военная техника Войск ПВО'),
       (DEFAULT, 'Военная техника Войск РХБ защиты'),
       (DEFAULT, 'Военная техника Войск связи'),
       (DEFAULT, 'Военная техника Инженерных войск'),
       (DEFAULT, 'Военная техника МТО'),
       (DEFAULT, 'Автомобили');

/* Категории боевой техники */
INSERT INTO combat_vehicle_categories (id, name, combat_vehicle_group_id)
VALUES (DEFAULT, 'Танки', 1),
       (DEFAULT, 'Боевые машины поддержки танков', 1),
       (DEFAULT, 'Боевые машины пехоты', 1),
       (DEFAULT, 'Бронетранспортёры', 1),
       (DEFAULT, 'Бронеавтомобили', 1),
       (DEFAULT, 'Боевые роботы', 1),
       (DEFAULT, 'Тактические ракетные комплексы', 2),
       (DEFAULT, 'Реактивные системы залпового огня', 2),
       (DEFAULT, 'Самоходные артиллерийские установки', 2),
       (DEFAULT, 'Буксируемая артиллерия', 2),
       (DEFAULT, 'Миномёты', 2),
       (DEFAULT, 'Боевые машины противотанковых ракетных комплексов', 3),
       (DEFAULT, 'Переносные противотанковые ракетные комплексы', 3),
       (DEFAULT, 'Противотанковая артиллерия', 3),
       (DEFAULT, 'Боевые разведывательные машины', 4),
       (DEFAULT, 'Радиоэлектронная разведка и подавление', 4),
       (DEFAULT, 'Радиолокационные станции', 4),
       (DEFAULT, 'Системы управления', 4),
       (DEFAULT, 'Беспилотные летательные аппараты', 4),
       (DEFAULT, 'ЗРК и ЗРПК', 5),
       (DEFAULT, 'Переносные зенитные ракетные комплексы', 5),
       (DEFAULT, 'Зенитная артиллерия', 5),
       (DEFAULT, 'Командно-штабные и машины обработки данных', 6),
       (DEFAULT, 'Машины РХБ разведки', 6),
       (DEFAULT, 'Боевая техника', 6),
       (DEFAULT, 'Средства дегазации и аэрозольной маскировки', 6),
       (DEFAULT, 'Системы связи', 7),
       (DEFAULT, 'Командно-штабные машины', 7),
       (DEFAULT, 'Разведывательные машины, устройства поиска и обнаружения', 8),
       (DEFAULT, 'Машины разграждения и разминирования', 8),
       (DEFAULT, 'Машины заграждения и минирования', 8),
       (DEFAULT, 'Мостоукладчики и переправочная техника', 8),
       (DEFAULT, 'Пожарная техника', 8),
       (DEFAULT, 'Ремонтные машины', 9),
       (DEFAULT, 'Седельные тягачи', 9),
       (DEFAULT, 'Артиллерийские тягачи', 10),
       (DEFAULT, 'Грузовики', 10),
       (DEFAULT, 'Легковые автомобили', 10);

/* Боевая техника */
INSERT INTO combat_vehicles (id, name, serial_number, combat_vehicle_category_id, military_formation_id)
VALUES (DEFAULT, 'Т-80У', '1GDGG31VX31992759', 1, 2),
       (DEFAULT, 'Т-80У', '1FMEU63E46UA80257', 1, 2),
       (DEFAULT, 'Т-80У', 'JF2SHADC9DH425396', 1, 2),
       (DEFAULT, 'Т-80БВМ', '1B7HC16XXXS140737', 1, 2),
       (DEFAULT, 'Т-80БВМ', '2V4RW3DG5BR641870', 1, 2),
       (DEFAULT, '2С19 «Мста-С»', 'WDBRF61J03E007794', 9, 2),
       (DEFAULT, 'БМП-2', '1FMCU04193KA11507', 3, 2),
       (DEFAULT, '9К51М «Торнадо-Г»', 'WBAXH5C52CDW02548', 8, 2),
       (DEFAULT, '9К35 Стрела-10/-10МН', '1B3CB7HB0AD566418', 20, 2),
       (DEFAULT, '9К35 Стрела-10/-10МН', '4T1BF3EK4BU763715', 20, 2),
       (DEFAULT, 'Р-142Н «Деймос-Н»', '1G6DC67A980166588', 28, 2),
       (DEFAULT, 'Р-142Н «Деймос-Н»', '2G4WS52J531287749', 28, 2),
       (DEFAULT, 'Р-142Н «Деймос-Н»', '1GCEK19C98Z246930', 28, 2),
       (DEFAULT, 'Р-142Н «Деймос-Н»', 'WBABR334XYEG98355', 28, 2),
       (DEFAULT, 'Тигр', '2HKRM4H79EH641873', 5, 2),
       (DEFAULT, 'Тигр', '1G8AJ55FX6Z122827', 5, 2);

/* Категории вооружения */
INSERT INTO armament_categories (id, name)
VALUES (DEFAULT, 'Пулемёты'),
       (DEFAULT, 'Снайперские винтовки'),
       (DEFAULT, 'Автоматы'),
       (DEFAULT, 'Пистолеты'),
       (DEFAULT, 'Противотанковые гранатомёты и реактивные гранаты'),
       (DEFAULT, 'Противотанковые мины'),
       (DEFAULT, 'Реактивные штурмовые гранаты'),
       (DEFAULT, 'Противопехотные гранатомёты'),
       (DEFAULT, 'Противопехотные мины'),
       (DEFAULT, 'Ручные гранаты и дымовые шашки');

/* Вооружение */
INSERT INTO armaments (id, name, serial_number, armament_category_id, military_formation_id)
VALUES (DEFAULT, 'АСВК', '2D4GP44L26R7', 2, 2),
       (DEFAULT, 'СВ-98', '3VWLZ7AJ1BM3', 2, 2),
       (DEFAULT, 'СВ-98', '3C4PDDBG9ET1', 2, 2),
       (DEFAULT, 'АК-74', 'JTHBF5C29A51', 3, 2),
       (DEFAULT, 'АК-74', '1GKKRPKD5EJ1', 3, 2),
       (DEFAULT, 'АК-74', 'JTJZB1BA8A20', 3, 2),
       (DEFAULT, 'АК-74', '2T1BR12E2YC2', 3, 2),
       (DEFAULT, 'Пистолет Макарова', 'VNKKTUD34EA0', 4, 2),
       (DEFAULT, 'Пистолет Макарова', 'JT3GN87R7W00', 4, 2),
       (DEFAULT, 'Пистолет Макарова', '1D8HN54P28B1', 4, 2),
       (DEFAULT, 'Пистолет Макарова', '1N4CL2AP5BC1', 4, 2),
       (DEFAULT, 'Пистолет Макарова', 'JTJHY7AX7A40', 4, 2),
       (DEFAULT, 'РГД-5', '2HGFA1F59BH5', 10, 2),
       (DEFAULT, 'Утёс', '5GTEN13E5881', 1, 2),
       (DEFAULT, 'ПКМ', 'JN8AZ1MW2CW2', 1, 2),
       (DEFAULT, 'ПКМ', '3D7UT2CLXBG5', 1, 2),
       (DEFAULT, 'ГП-34', '3D4GG57V49T5', 8, 2),
       (DEFAULT, 'ГП-34', '1G2WP1217WF2', 8, 2),
       (DEFAULT, 'ГП-34', '2G1WB5EK7B12', 8, 2),
       (DEFAULT, 'РШГ-1', '4T1GK12E7SU0', 7, 2),
       (DEFAULT, 'РШГ-1', '1G11C5SA6DF3', 7, 2),
       (DEFAULT, 'РШГ-1', '1N4AL3AP2DC1', 7, 2);

/* Сооружения */
INSERT INTO military_buildings (id, name, military_formation_id)
VALUES (DEFAULT, 'Казарма ©1', 2),
       (DEFAULT, 'Казарма ©2', 2),
       (DEFAULT, 'Столовая ©1', 2),
       (DEFAULT, 'Столовая ©2', 2),
       (DEFAULT, 'Склад ©1', 2),
       (DEFAULT, 'Склад ©2', 2),
       (DEFAULT, 'Склад ©3', 2);
