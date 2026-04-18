insert into formats(name, pattern) values ('date_format', 'dd:MM:yyyy');

select * from formats where name = 'date_format';
update formats set pattern = 'dd:MM:yyyy' where name = 'date_format';