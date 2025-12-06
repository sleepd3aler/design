create table customers
(
    id            serial primary key,
    name          varchar(255),
    city          varchar(255),
    registered_at date
);
create table products
(
    id       serial primary key,
    name     varchar(255),
    category varchar(255),
    price    float
);
create table orders
(
    id          serial primary key,
    customer_id int references customers (id),
    order_date  date,
    status      varchar(255)
);
create table order_items
(
    id         serial primary key,
    order_id   int references orders (id),
    product_id int references products (id),
    quantity   int
);

insert into customers(name, city, registered_at)
values ('Иван', 'Москва', '2023-01-15'),                --1
       ('Мария', 'Севастополь', '2023-03-20'),          --2
       ('Олег', 'Санкт - Петербург', '	2023-06-10'), --3
       ('Дмитрий', 'Ставрополь', '2023-05-21'),         --4
       ('Геннадий', 'Симферополь', '2023-01-15'),       --5
       ('Елена', 'Ялта', '2023-07-22'); --6

insert into products(name, category, price)
values ('Iphone 15', 'electronics', 60000),                --1
       ('Macbook Air M4 16 / 256', 'electronics', 102000), --2
       ('Dyson', 'electronics', 60000),                    --3
       ('Adidas Niteball', 'clothes', 15000),              --4
       ('Carhartt WIP sweatshirt', 'clothes', 10000),      --5
       ('Guess shopper', 'clothes', 15000),                --6
       ('Olive Oil', 'food', 300),                         --7
       ('Berserk', 'books', 102000); --8

insert into orders(customer_id, order_date, status)
values (1, '2023-02-13', 'completed'), --1
       (1, '2023-05-16', 'pending'),   --2
       (2, '2023-10-12', 'pending'),   --3
       (2, '2023-05-16', 'pending'),   --4
       (3, '2023-07-12', 'canceled'),  --5
       (3, '2023-12-03', 'pending'),   --6
       (3, '2023-11-16', 'completed'), --7
       (4, '2023-06-15', 'completed'), --8
       (4, '2023-08-05', 'canceled'),  --9
       (4, '2023-09-03', 'completed'), --10
       (4, '2023-09-03', 'completed'); --11

insert into order_items (order_id, product_id, quantity)
values (1, 1, 4),
       (1, 2, 2),
       (2, 8, 3),
       (2, 3, 5),
       (2, 6, 7),
       (3, 3, 1),
       (4, 5, 1),
       (4, 6, 1);

create view total_city_orders as
select c.city Город, sum(price * quantity) "Итоговая сумма заказов"
from customers c
         join orders o on c.id = o.customer_id
         join order_items oi on o.id = oi.order_id
         join products p on oi.product_id = p.id
where quantity > 2 and registered_at < current_date
group by c.city
having sum(price * quantity) > 100000
order by Город DESC;

select * from total_city_orders;





