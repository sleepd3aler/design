CREATE TABLE students (
    id serial PRIMARY KEY,
    name text
);

CREATE TABLE subjects (
    id serial PRIMARY KEY,
    name text
);

CREATE TABLE students_subjects (
    id serial PRIMARY KEY,
    mark float,
    student_id int REFERENCES students (id),
    subject_id int REFERENCES subjects (id)
);

INSERT INTO students (name)
VALUES
    ('Аня'),
    ('Ваня'),
    ('Боря');

INSERT INTO subjects (name)
VALUES
    ('Математика'),
    ('Русский'),
    ('Информатика');

INSERT INTO students_subjects (student_id, subject_id, mark)
VALUES
    (1, 1, 5),
    (1, 2, 5),
    (1, 3, 5);

INSERT INTO students_subjects (student_id, subject_id, mark)
VALUES
    (2, 1, 5),
    (2, 2, 4),
    (2, 3, 4);

INSERT INTO students_subjects (student_id, subject_id, mark)
VALUES
    (3, 1, 3),
    (3, 2, 5),
    (3, 3, 3);

SELECT
    AVG(mark)
FROM
    students_subjects;

SELECT
    MIN(mark)
FROM
    students_subjects;

SELECT
    MAX(mark)
FROM
    students_subjects;

SELECT
    s.name,
    avg(ss.mark)
FROM
    students_subjects AS ss
    JOIN subjects s ON ss.subject_id = s.id
GROUP BY
    s.name;

SELECT
    s.name,
    avg(ss.mark)
FROM
    students_subjects AS ss
    JOIN students s ON ss.student_id = s.id
GROUP BY
    s.name;

SELECT
    s.name,
    avg(ss.mark)
FROM
    students_subjects ss
    JOIN subjects s ON ss.subject_id = s.id
GROUP BY
    s.name
HAVING
    avg(ss.mark) > 4.2;










