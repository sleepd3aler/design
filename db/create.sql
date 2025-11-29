CREATE TABLE roles (
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE rules (
    id serial PRIMARY KEY,
    permission varchar(255)
);

CREATE TABLE roles_rules (
    id serial PRIMARY KEY,
    role_id int REFERENCES roles (id),
    rule_id int REFERENCES rules (id)
);

CREATE TABLE categories (
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE states (
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    name varchar(255),
    role_id int REFERENCES roles (id)
);

CREATE TABLE items (
    id serial PRIMARY KEY,
    name varchar(255),
    user_id int REFERENCES users (id),
    category_id int REFERENCES categories (id),
    state_id int REFERENCES states (id)
);

CREATE TABLE attachs (
    id serial PRIMARY KEY,
    file_name varchar(255),
    item_id int REFERENCES items (id)
);

CREATE TABLE comments (
    id serial PRIMARY KEY,
    content text,
    item_id int REFERENCES items (id),
    user_id int REFERENCES users (id)
);

