create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure delete_data(i_name varchar)
language 'plpgsql'
as $$
    BEGIN
    delete from products where name = i_name;
    END
$$;

call delete_data('product_2');

create or replace function f_delete_data(i_name varchar)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
		delete from products where name = i_name;
        select into result count(*) from products;
        return result;
    end;
$$;

select f_delete_data('product_1');