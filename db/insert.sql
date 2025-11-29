INSERT INTO roles (name)
    VALUES ('Admin');

INSERT INTO roles (name)
    VALUES ('User');

INSERT INTO rules (permission)
    VALUES ('Create item');

INSERT INTO rules (permission)
    VALUES ('Delete item');

INSERT INTO rules (permission)
    VALUES ('Show item');

INSERT INTO rules (permission)
    VALUES ('Update state');

INSERT INTO roles_rules (role_id, rule_id)
    VALUES (1, 4);

INSERT INTO roles_rules (role_id, rule_id)
    VALUES (2, 1);

INSERT INTO roles_rules (role_id, rule_id)
    VALUES (2, 2);

INSERT INTO roles_rules (role_id, rule_id)
    VALUES (2, 3);

INSERT INTO categories (name)
    VALUES ('Request');

INSERT INTO categories (name)
    VALUES ('Issue');

INSERT INTO categories (name)
    VALUES ('Info');

INSERT INTO states (name)
    VALUES ('NEW');

INSERT INTO states (name)
    VALUES ('IN PROGRESS');

INSERT INTO states (name)
    VALUES ('DONE');

INSERT INTO users (name, role_id)
    VALUES ('Alex', 1);

INSERT INTO users (name, role_id)
    VALUES ('Mary', 2);

INSERT INTO items (name, user_id, category_id, state_id)
    VALUES ('Send invoice for payment', 2, 1, 1);

INSERT INTO attachs (file_name, item_id)
    VALUES ('Company profile.pdf', 1);

INSERT INTO comments (content, item_id, user_id)
    VALUES ('Dont forget about invoice stamp', 1, 2);

