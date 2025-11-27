CREATE TABLE stockroom (
    id serial PRIMARY KEY,
    products_category varchar(255),
    manager_id int REFERENCES managers (id) unique 
    );
