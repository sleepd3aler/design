CREATE TABLE users
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username text NOT NULL
);

INSERT INTO users (username)
SELECT 'person' || n
FROM generate_series(1, 1000) AS n;

explain
select *
from users
order by username;


EXPLAIN
SELECT *
FROM users
LIMIT 1;

analyze verbose users (username);

explain
select count(*)
from users;


SELECT reltuples, relpages
FROM pg_class
WHERE relname = 'users';


EXPLAIN ANALYZE
SELECT *
FROM users
ORDER BY username;

create index people_username_index on users (username);

analyze users;

