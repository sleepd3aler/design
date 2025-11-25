CREATE TABLE IF NOT EXISTS managers_info (
id serial primary key,
name text,
birthday date,
role text,
sex boolean,
phone varchar(11),
salary int
);
