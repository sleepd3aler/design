CREATE TABLE customer
(
    first_name text,
    last_name  text,
    status     text
);

CREATE TABLE employee
(
    first_name text,
    last_name  text,
    emp_status text
);

CREATE TABLE referrer
(
    first_name text,
    last_name  text
);

INSERT INTO referrer
VALUES ('Евгений', 'Онегин'),
       ('Петр', 'Сергеев'),
       ('Александр', 'Ожегов'),
       ('Анна', 'Опушкина'),
       ('Михаил', 'Кругов');

INSERT INTO customer
VALUES ('Иван', 'Иванов', 'Active'),
       ('Петр', 'Сергеев', 'Active'),
       ('Ирина', 'Бросова', 'Active'),
       ('Анна', 'Опушкина', 'Active'),
       ('Потап', 'Потапов', 'Passive');

INSERT INTO employee
VALUES ('Кристина', 'Позова', 'Current'),
       ('Михаил', 'Кругов', 'Current'),
       ('Анна', 'Опушкина', 'Current'),
       ('Иван', 'Иванов', 'Current'),
       ('Сергей', 'Петров', 'Current');


select first_name, last_name
from customer
union
select first_name, last_name
from employee
order by first_name, last_name;

SELECT first_name, last_name
FROM customer
WHERE status = 'Active'
UNION
SELECT first_name, last_name
FROM employee
WHERE emp_status = 'Current'
ORDER BY first_name, last_name;


SELECT e.first_name,
       e.last_name,
       c.first_name,
       c.last_name
FROM employee e
         INNER JOIN customer c
                    ON e.first_name = c.first_name
                        AND e.last_name = c.last_name;

select first_name, last_name
from customer
union all
select first_name, last_name
from employee
order by first_name, last_name;

SELECT first_name, last_name
FROM customer
EXCEPT
SELECT first_name, last_name
FROM employee;

SELECT first_name, last_name
FROM customer
INTERSECT
SELECT first_name, last_name
FROM employee;

SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
UNION
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee
EXCEPT
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
(SELECT first_name, last_name
 FROM employee
 EXCEPT
 SELECT first_name, last_name
 FROM referrer)
ORDER BY first_name, last_name;


CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');


select name
from movie
intersect
select title
from book;

select title
from book
except
select name
from movie;

select name
from movie
except
select title
from book
union
(select title
 from book
 except
 select name
 from movie)
order by name;



