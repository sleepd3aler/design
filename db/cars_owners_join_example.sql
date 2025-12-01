CREATE TABLE cars (
    id serial PRIMARY KEY,
    name varchar(255),
    model varchar(255),
    reg_number varchar(255) UNIQUE
);

CREATE TABLE owners (
    id serial PRIMARY KEY,
    name varchar(255),
    car_id int REFERENCES cars (id)
);

INSERT INTO cars (name, model, reg_number)
    VALUES ('Toyota', 'Mark II', 'A-666-93');

INSERT INTO cars (name, model, reg_number)
    VALUES ('BMW', 'M-90', 'B-777-92');

INSERT INTO cars (name, model, reg_number)
    VALUES ('Nissan', 'Tilda', 'C-123-85');

INSERT INTO cars (name, model)
    VALUES ('Toyota', 'Supra');

INSERT INTO owners (name, car_id)
    VALUES ('Alexander', 1);

INSERT INTO owners (name, car_id)
    VALUES ('Boris', 1);

INSERT INTO owners (name, car_id)
    VALUES ('Mariya', 3);

INSERT INTO owners (name, car_id)
    VALUES ('Evgeniy', 4);

INSERT INTO owners (name, car_id)
    VALUES ('Alina', 2);

INSERT INTO owners (name)
    VALUES ('Georgiy');

SELECT
    o.name AS Владелец,
    c.name AS Марка,
    c.model AS Модель
FROM
    owners AS o
    JOIN cars AS c ON o.car_id = c.id;

SELECT
    c.reg_number AS "Регистрационный номер",
    c.model AS Марка,
    o.name AS Владелец
FROM
    cars AS c
    JOIN owners AS o ON c.id = o.car_id
        AND c.reg_number IS NOT NULL;

SELECT
    o.name AS Владелец,
    c.reg_number AS "Регистрационный номер"
FROM
    owners AS o
    JOIN cars AS c ON o.car_id = c.id
        AND c.reg_number IS NULL;

