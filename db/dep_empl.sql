create table departments
(
    id   serial primary key,
    name text
);

create table employees
(
    id            serial primary key,
    name          varchar(255),
    department_id int references departments (id)
);

create table departments_employees
(
    id            serial primary key,
    department_id int references departments (id),
    employee_id   int references employees (id)
);

create table teens
(
    id     serial primary key,
    name   varchar(255),
    gender varchar(1)
);

insert into departments(name)
values ('Кухня'),
       ('Бар'),
       ('Зал'),
       ('Клининг');

insert into employees(name)
values ('Михаил'),     --1
       ('Ярослав'),    --2
       ('Никита'),     --3
       ('Мария'),      --4
       ('Александра'), --5
       ('Андрей'),     --6
       ('Руслана'),    --7
       ('Лариса'),     --8
       ('Валентина'); --9

insert into departments_employees(department_id, employee_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7),
       (4, 8),
       (4, 9);

insert into teens(name, gender)
values ('Маша', 'Ж'),
       ('Саша', 'М'),
       ('Лера', 'Ж'),
       ('Вася', 'М');

select d.name
from departments d
         left join departments_employees de on d.id = de.department_id
where employee_id is null;

select d.name Подразделение, e.name Сотрудник
from departments d
         full join departments_employees de on d.id = de.department_id
         join employees e on de.employee_id = e.id;

select d.name, employee_id
from departments d
         left join departments_employees de on d.id = de.department_id
where department_id = 3;

select d.name, employee_id
from departments_employees de
         right join departments d on d.id = de.department_id
where department_id = 3;

select t1.name, t2.name
from teens t1
         cross join teens t2
where t1.name != t2.name
  and t1.gender != t2.gender
  and t1.id < t2.id;
