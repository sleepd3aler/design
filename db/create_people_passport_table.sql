create table passport_people(id serial primary key,
passport_id int references passport (id) unique,
people_id int references people2 (id) unique 
);