create table student_courses(
id serial primary key, 
student_id int references students(id),
course_id int references courses(id));