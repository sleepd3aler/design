CREATE TABLE companies
(
    id   serial NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
);

CREATE TABLE people
(
    id         serial NOT NULL,
    name       CHARACTER VARYING,
    company_id INTEGER REFERENCES companies (id),
    CONSTRAINT people_pkey PRIMARY KEY (id)
);


insert into companies (name)
values ('NVIDIA'),
       ('OpenAI'),
       ('Google'),
       ('Blizzard'),
       ('Valve');

insert into people (name, company_id)
values ('Tom', 1),
       ('Alex', 1),
       ('Mary', 2),
       ('Artem', 2),
       ('John', 2),
       ('Elana', 3),
       ('Helen', 4),
       ('Jack', 4),
       ('Mark', 5);


select p.name as person_name, c.name as company_name
from people p
         join companies c on c.id = p.company_id
where company_id != 5;


select c.name, count(p.id)
from companies c
         join people p on c.id = p.company_id
group by c.name
having count(p.id) = (select count(p2.id)
                      from companies c2
                               join people p2 on c2.id = p2.company_id
                      group by c2.name
                      order by count(p2.id) desc
                      limit 1)
order by count(p.id) desc;