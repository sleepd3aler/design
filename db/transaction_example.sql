begin transaction;
insert into products(name, producer, count, price)
values ('product_5', 'producer_5', 17, 45);
savepoint first_savepoint;
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
select * from products;
rollback to first_savepoint;
commit;

begin transaction;
delete from products where name in ('product_1', 'product_2');
savepoint first_sp;
update products set price = 999 where name in ('product_1', 'product_2');
insert into products(name, producer, count, price)
values ('product_6', 'producer_6',37, 999);
savepoint second_sp;
release savepoint first_sp;
rollback;
