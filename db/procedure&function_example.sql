create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    int default 0,
    price    int
);


create
    or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
as
$$
begin
    insert into products(name, producer, count, price)
    values (i_name, prod, i_count, i_price);
end;
$$
    language 'plpgsql';

call insert_data('product_2', 'producer_2', 15, 32);

create
    or replace procedure update_data(u_count integer, tax float, u_id integer)
    language 'plpgsql'
as
$$
BEGIN
    if u_count > 0 THEN
        update products
        set count = count - u_count
        where id = u_id;
    end if;
    if
        tax > 0 THEN
        update products
        set price = price + price * tax;
    end if;
END;
$$;



call update_data(10, 0, 1);

call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);

alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

call update(0, 0.2, 0);

drop procedure update(u_ount int, tax float, u_id int);

delete
from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create
    or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    returns void
    language 'plpgsql'
as
$$
begin
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

end;

create or replace function f_update_data(u_count int, tax float, u_id int)
    returns int
    language 'plpgsql'
as
$$
declare
    result int;
begin
    if u_count > 0 then
        update products
        set count = count - u_count
        where id = u_id;
        select into result count
        from products
        where id = u_id;
    end if;
    if tax > 0 then
        update products
        set price = price + price * tax;
        select into result sum(price)
        from products;
    end if;
    return result;
end;
$$;


select f_update_data(10, 0, 1);

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);


select f_update_data(0, 0.2, 0);


create or replace procedure delete_empty(d_id int)
    language 'plpgsql'
as
$$
begin
    delete
    from products
    where id = d_id
      and count <= 0;
end ;
$$;

create function f_delete_if_empty(d_id int)
    returns text
    language 'plpgsql'
as
$$
declare
    result text;
begin
    if (
        select count from products
                     where id = d_id
        ) = 0 then
        delete from products
    where id = d_id;
    select into result 'Delete successful';
    return result;
    end if;
select into result 'Delete failed, count must be 0';
return result;
end;
$$;


select f_delete_if_empty(1);
