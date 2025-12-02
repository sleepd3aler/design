CREATE TABLE devices (
    id serial PRIMARY KEY,
    name varchar(255),
    price float
);

CREATE TABLE people (
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE devices_people (
    id serial PRIMARY KEY,
    device_id int REFERENCES devices (id),
    people_id int REFERENCES people (id)
);

INSERT INTO devices (name, price)
VALUES
    ('Iphone', 10000),
    ('Apple Watch', 20000),
    ('Dyson', 52000),
    ('Wi-Fi Router', 1200);

INSERT INTO people (name)
VALUES
    ('Александр'),
    ('Мария'),
    ('Галина'),
    ('Елена'),
    ('Георгий'),
    ('Ярослав'),
    ('Михаил');

INSERT INTO devices_people (device_id, people_id)
    VALUES (1, 1),
    (2, 1),
    (1, 2),
    (3, 2),
    (4, 3),
    (4, 4),
    (1, 5),
    (2, 6),
    (4, 6);

SELECT
    avg(price)
FROM
    devices;

SELECT
    p.name,
    avg(d.price)
FROM
    devices_people dp
    JOIN people p ON dp.people_id = p.id
    JOIN devices d ON dp.device_id = d.id
GROUP BY
    p.name
HAVING
    AVG(d.price) > 5000;

