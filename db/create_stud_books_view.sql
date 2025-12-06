create table students
(
    id   serial primary key,
    name varchar(255)
);
create table authors
(
    id   serial primary key,
    name varchar(255)
);
create table books
(
    id        serial primary key,
    name      varchar(255),
    author_id int references authors (id)
);
create table orders
(
    id         serial primary key,
    active     bool default true,
    book_id    int references books (id),
    student_id int references authors (id)
);

insert into students (name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');

insert into authors (name)
values ('Александр Пушкин');
insert into authors (name)
values ('Николай Гоголь');

insert into books (name, author_id)
values ('Евгений Онегин', 1);
insert into books (name, author_id)
values ('Капитанская дочка', 1);
insert into books (name, author_id)
values ('Дубровский', 1);
insert into books (name, author_id)
values ('Мертвые души', 2);
insert into books (name, author_id)
values ('Вий', 2);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);


select s.name, count(a.name), a.name
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 2;

create view show_students_with_2_or_more_books
as
select s.name as student, count(a.name), a.name as author
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 2;

select * from show_students_with_2_or_more_books;


