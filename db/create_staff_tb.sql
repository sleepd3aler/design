CREATE TABLE staff (
    id serial PRIMARY KEY,
    role varchar(255),
    name varchar(255),
    manager_id int REFERENCES managers (id)
);


