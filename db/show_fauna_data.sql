SELECT
    *
FROM
    fauna
WHERE
    name LIKE '%Fish%';

SELECT
    *
FROM
    fauna
WHERE
    avg_age >= 10000
    AND avg_age <= 21000;

SELECT
    *
FROM
    fauna
WHERE
    discovery_date IS NULL;

SELECT
    *
FROM
    fauna
WHERE
    discovery_date < '1950-01-01';

