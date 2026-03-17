CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar(255),
    producer varchar(255),
    count int DEFAULT 0,
    price int
);

CREATE OR REPLACE FUNCTION discount ()
    RETURNS TRIGGER
    AS $$
BEGIN
    UPDATE
        products
    SET
        price = price - price * 0.2
    WHERE
        count <= 5
        AND id = NEW.id;
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER discount_trigger
    AFTER INSERT ON products
    FOR EACH ROW
    EXECUTE PROCEDURE discount ();

INSERT INTO products (name, producer, count, price)
    VALUES ('product_3', 'producer_3', 8, 115);

INSERT INTO products (name, producer, count, price)
    VALUES ('product_1', 'producer_1', 3, 50);

ALTER TABLE products DISABLE TRIGGER discount_trigger;

INSERT INTO products (name, producer, count, price)
    VALUES ('product_1', 'producer_1', 3, 50);

DROP TRIGGER discount_trigger ON products;

CREATE OR REPLACE FUNCTION tax ()
    RETURNS TRIGGER
    AS $$
BEGIN
    UPDATE
        products
    SET
        price = price - price * 0.2
    WHERE
        id = (
            SELECT
                id
            FROM
                inserted)
        AND count <= 5;
    RETURN new;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger
    AFTER INSERT ON products referencing new TABLE AS inserted
    FOR EACH statement
    EXECUTE PROCEDURE tax ();

CREATE OR REPLACE FUNCTION tax ()
    RETURNS TRIGGER
    AS $$
BEGIN
    UPDATE
        products
    SET
        price = price + price * 0.22
    WHERE
        id = (
            SELECT
                id
            FROM
                inserted);
    RETURN new;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER tax_trigger
    AFTER INSERT ON products referencing new TABLE AS inserted
    FOR EACH statement
    EXECUTE PROCEDURE tax ();

INSERT INTO products (name, producer, count, price)
    VALUES ('Iphone 15', 'Apple', 1, 100000);

CREATE OR REPLACE FUNCTION tax ()
    RETURNS TRIGGER
    AS $$
BEGIN
    NEW.price = NEW.price + NEW.price * 0.2;
    RETURN new;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER tax_trigger
    BEFORE INSERT ON products
    FOR EACH ROW
    EXECUTE PROCEDURE tax ();

INSERT INTO products (name, producer, count, price)
    VALUES ('Iphone 13', 'Apple', 1, 50000);

CREATE TABLE history_of_price (
    id serial PRIMARY KEY,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION to_history ()
    RETURNS TRIGGER
    AS $$
BEGIN
    INSERT INTO history_of_price (name, price, date)
        VALUES (NEW.name, NEW.price, CURRENT_DATE);
    RETURN new;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER add_to_history
    AFTER INSERT ON products
    FOR EACH ROW
    EXECUTE PROCEDURE to_history ();

INSERT INTO products (name, producer, count, price)
    VALUES ('Berserk', 'Kentaro Miura', 1, 2500);

CREATE TABLE products2 (
    id serial PRIMARY KEY,
    name varchar
);

ALTER TABLE products2
    ADD price int,
    ADD quantity int;

CREATE TABLE priceAudit (
    id serial PRIMARY KEY,
    product_id int REFERENCES products2 (id),
    old_price int,
    new_price int,
    change_date timestamp
);

CREATE OR REPLACE FUNCTION addNewPrice ()
    RETURNS TRIGGER
    AS $$
BEGIN
    INSERT INTO priceAudit (product_id, old_price, new_price, change_date)
        VALUES (OLD.id, OLD.price, NEW.price, CURRENT_TIMESTAMP);
    RETURN new;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER test_trigger
    AFTER UPDATE ON products2
    FOR EACH ROW
    EXECUTE FUNCTION addNewPrice ();



SELECT
    *
FROM
    products2;

SELECT
    *
FROM
    priceAudit;

INSERT INTO products2 (name, price, quantity)
    VALUES ('pr1', 100, 1);

UPDATE
    products2
SET
    price = 120
WHERE
    id = 1;



