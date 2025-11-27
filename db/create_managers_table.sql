create table if not exists managers_info
(
    id                  serial primary key,
    name                text,
    role                text,
    birthday            date,
    officially_employed boolean,
    phone               varchar(11),
    salary              int
);
