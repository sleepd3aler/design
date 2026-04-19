insert into formats(name, pattern) values ('date_format', 'dd:MM:yyyy');

select * from formats;
update formats set pattern = 'dd:MM:yyyy HH:mm' where name = 'date_format';

alter table formats add interval int default 30;