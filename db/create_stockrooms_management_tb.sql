CREATE TABLE roles (
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE staff (
    id serial PRIMARY KEY,
    name varchar(255),
    role_id int REFERENCES roles (id),
    manager_id int REFERENCES staff (id)
);

CREATE TABLE categories (
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE stockrooms (
    id serial PRIMARY KEY,
    name varchar(255),
    category_id int REFERENCES categories (id),
    manager_id int REFERENCES staff (id)
);

CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar(255),
    category_id int REFERENCES categories (id),
    stockroom_id int REFERENCES stockrooms (id)
);

CREATE TABLE staff_products (
    id serial PRIMARY KEY,
    staff_id int REFERENCES staff (id),
    product_id int REFERENCES products (id)
);

