CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar(255),
    stockroom_id int REFERENCES stockroom (id) UNIQUE
);



