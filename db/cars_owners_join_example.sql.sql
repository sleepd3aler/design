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
    pp.name AS Владелец,
    p.name AS Марка,
    p.model AS Модель
FROM
    owners AS pp
    JOIN cars AS p ON pp.car_id = p.id;

SELECT
    pp.reg_number AS "Регистрационный номер",
    pp.model AS Марка,
    p.name AS Владелец
FROM
    cars AS pp
    JOIN owners AS p ON pp.id = p.car_id
        AND pp.reg_number IS NOT NULL;


SELECT
    pp.name AS Владелец,
    p.reg_number AS "Регистрационный номер"
FROM
    owners AS pp
    JOIN cars AS p ON pp.car_id = p.id
        AND p.reg_number IS NULL;



