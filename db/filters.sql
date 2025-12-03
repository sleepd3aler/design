create table type
(
    id   serial primary key,
    name text
);
create table products
(
    id           serial primary key,
    name         text,
    type_id      int references type (id),
    expired_date date,
    price        float
);

create table type_products
(
    id         serial primary key,
    type_id    int references type (id),
    product_id int references products (id)
);

insert into type (name)
values ('СЫР'),    --1
       ('МОЛОКО'), --2
       ('МЯСО'),   --3
       ('ПТИЦА'),  --4
       ('РЫБА'),   --5
       ('КРУПА'); --6


insert into products (name, type_id, expired_date, price)
values ('Сыр плавленный', 1, '2025-10-03', 120),
       ('Пармезан', 1, '2025-11-05', 500),
       ('Моцарелла', 1, '2026-03-03', 200),
       ('БМК', 2, '2024-12-31', 93),
       ('Parmalat', 2, '2026-10-5', 120),
       ('Чудское озеро', 2, '2026-02-12', 145),
       ('Мираторг "Рибай"', 3, '2025-12-31', 2500),
       ('Мираторг "Фланк"', 3, '2024-12-10', 1500),
       ('Утка', 4, '2026-01-15', 2000),
       ('Курица', 4, '2025-11-18', 700),
       ('Перепёлка', 4, '2026-02-05', 1000),
       ('Лосось', 5, '2026-01-01', 2200),
       ('Селёдка', 5, '2025-11-07', 1000),
       ('Сибас', 5, '2026-02-05', 3200),
       ('Овсянка', 6, '2030-10-03', 200),
       ('Гречка', 6, '2030-12-30', 150),
       ('Рис', 6, '2025-11-07', 120),
       ('Кус - Кус', 6, '2027-05-13', 170),
       ('Киноа', 6, '2025-12-01', 180);

insert into type_products(type_id, product_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (4, 9),
       (4, 10),
       (4, 11),
       (5, 12),
       (5, 13),
       (5, 14),
       (6, 15),
       (6, 16),
       (6, 17),
       (6, 18),
       (6, 19);

select t.name, p.name, p.expired_date, p.price
from products p
         join type_products tp on p.id = tp.product_id
         join type t on tp.type_id = t.id
where t.name = 'СЫР';

select *
from products p
where name like '%мороженое%';

select t.name, p.name, p.expired_date
from products p
         join type_products tp on p.id = tp.product_id
         join type t on tp.type_id = t.id
where expired_date < current_date;

select name, price
from products
where price = (select max(price) from products);


select t.name, count(*)
from type_products tp
         join type t on t.id = tp.type_id
         join products p on p.id = tp.id
group by t.name;


select p.name, t.name
from type_products tp
         join type t on t.id = tp.type_id
         join products p on p.id = tp.product_id
where t.name = 'СЫР'
   or t.name = 'МОЛОКО';


select t.name, count(*)
from type_products tp
         join type t on tp.type_id = t.id
         join products p on tp.product_id = p.id
group by t.name
having count(*) < 10;













