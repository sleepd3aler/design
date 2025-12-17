create table companies
(
    id   serial primary key,
    city text
);
create table goods
(
    id         serial primary key,
    name       text,
    company_id int references companies (id),
    price      int
);

create table sales_managers
(
    id          serial primary key,
    last_name   text,
    first_name  text,
    company_id  int references companies (id),
    manager_fee int
);

create table managers
(
    id         serial primary key,
    company_id int references companies (id)
);

insert into companies
values (1, 'Москва'),
       (2, 'Нью - Йорк'),
       (3, 'Мюнхен');

insert into goods
values (1, 'Небольшая квартира', 3, 5000),
       (2, 'Небольшая квартира в центре', 1, 4500),
       (3, 'Квартира у метро', 1, 3200),
       (4, 'Лофт', 2, 6700),
       (5, 'Загородный дом', 2, 9800);

INSERT INTO sales_managers
VALUES (1, 'Доу', 'Джон', 2, 2250),
       (2, 'Грубер', 'Ганс', 3, 3120),
       (3, 'Смит', 'Сара', 2, 1640),
       (4, 'Иванов', 'Иван', 1, 4500),
       (5, 'Купер', 'Грета', 3, 2130);

INSERT INTO managers
VALUES (1, 2),
       (2, 3),
       (4, 1);

select *
from sales_managers
where manager_fee > (select avg(manager_fee) from sales_managers);

select name, price, (select avg(price) as "средняя цена" from goods)
from goods;

select avg(manager_fee)
from sales_managers
where sales_managers.id NOT in (select managers.id
                                from managers);

select city                        as c,
       (select count(*)
        from goods g
        where c.id = g.company_id) as total_goods
from companies c;

SELECT c.city, COUNT(g.name) AS total_goods
FROM companies c
         JOIN goods g ON c.id = g.company_id
GROUP BY c.city;


select last_name, first_name, manager_fee
from sales_managers sm1
where sm1.manager_fee >= (select avg(manager_fee)
                          from sales_managers sm2
                          where sm2.company_id = sm1.company_id);

SELECT company_id, AVG(price) AS average_price
FROM goods
GROUP BY company_id
HAVING AVG(price) > (SELECT MAX(price) FROM goods) / 2;


CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into customers
values (1, 'Alex', 'Novitskiy', 32, 'Russia'),
       (2, 'Bogdan', 'Kireev', 18, 'Ukraine'),
       (3, 'Konstantin', 'Reznik', 18, 'Belarus'),
       (4, 'Dmitriy', 'Lihatskiy', 33, 'Japan'),
       (5, 'Kim', 'Nam - Joon', 31, 'Korea'),
       (6, 'Wang', 'Wei', 21, 'China');

insert into orders
values (1, 2, 1),
       (2,3,2),
       (3,4,3);

select first_name, last_name, age from customers
where age = (select min(age) from customers );

select id, first_name, last_name from customers
where customers.id not in (select id from orders);