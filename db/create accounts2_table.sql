create table accounts2
(
    id       serial primary key,
    email    text,
    password int,
    email_id int references emails (id) unique
);