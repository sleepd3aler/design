CREATE TABLE universities (
    id serial PRIMARY KEY,
    name text
);

CREATE TABLE students (
    id serial PRIMARY KEY,
    name text,
    course int,
    budget bool,
    speciality text,
    enroll_date timestamp,
    university_id int REFERENCES universities (id)
);

