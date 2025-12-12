create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    int,
    price    int

);

create table employees
(
    id       serial primary key,
    name     varchar(50),
    position varchar(50),
    salary   int
);

insert into employees(name, position, salary)
values ('Alex', 'Manager', 10000),
       ('Mary', 'Manager', 10000),
       ('Andrey', 'Intern', 30000),
       ('Alexey', 'Intern', 30000);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);
select *
from products;

begin transaction isolation level serializable;
select *
from employees;
delete
from employees
where position = 'Manager';
commit;

begin transaction isolation level serializable;
select *
from employees;
update employees
set salary = 120000
where position = 'Manager';




