create table car_bodies
(
    id   serial primary key,
    name varchar(255)
);

create table car_engines
(
    id   serial primary key,
    name varchar(255)
);

create table car_transmissions
(
    id   serial primary key,
    name varchar(255)
);

create table cars
(
    id              serial primary key,
    name            varchar(255),
    body_id         int references car_bodies (id),
    engine_id       int references car_engines (id),
    transmission_id int references car_transmissions (id)
);

insert into car_bodies(name)
values ('Седан'),
       ('Хэтчбек'),
       ('Пикап'),
       ('Купе'),
       ('Минивэн');

insert into car_engines(name)
values ('Бензиновый'),
       ('Дизельный'),
       ('Электрический'),
       ('Гибрид'),
       ('Вечный'),
       ('Атомный');

insert into car_transmissions(name)
values ('Механика'),
       ('Автомат'),
       ('Робот'),
       ('Вариатор'),
       ('Телепатическая');

insert into cars(name, body_id, engine_id, transmission_id)
values ('Toyota Camry', 1, 1, 2),
       ('BMW M4', 4, 1, 3),
       ('Tesla Model 3', 1, 3, 2),
       ('Ford F - 150', 3, 2, 1),
       ('Honda Civic', 2, 1, 4),
       ('Audi A5', 4, 4, 2),
       ('Nissan Lead', 2, 3, 1),
       ('Mercedes E200', 1, 1, 2),
       ('Volkswagen Amarok', 3, 2, 1),
       ('Lexus UX', 2, 4, 2),
       ('Bat - mobile', 1, null, null),
       ('Big - Foot', null, null, null);

select c.id, c.name Автомобиль, cb.name Кузов, ce.name Двигатель, ct.name КПП
from cars c
         left join car_bodies cb on c.body_id = cb.id
         left join car_engines ce on c.engine_id = ce.id
         left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name Кузов
from cars c
         right join car_bodies cb on c.body_id = cb.id
where c.body_id is null;

select ce.name Двигатель
from car_engines ce
left join cars c on c.engine_id = ce.id
where c.engine_id is null;

select ct.name КПП
from cars c
right join car_transmissions ct on ct.id = c.transmission_id
where c.transmission_id is null;
