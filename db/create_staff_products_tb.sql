CREATE TABLE staff_products (
    id serial PRIMARY KEY,
    staff_id int REFERENCES staff (id),
    product_id int REFERENCES products (id)
);



