create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
	
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 6000);
select * from products;

create table products2 (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);	
	
create or replace function tax2()
    returns trigger as
$$
    BEGIN
        update products2
        set price = price + price * 0.13
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger tax_trigger2
    after insert
    on products2
    for each row
    execute procedure tax2();
	

insert into products2 (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 5000);
select * from products2;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values(new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger history_trigger
    after insert
    on products
    for each row
    execute procedure history_of_price();
	
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 3, 10000);
select * from history_of_price;	
	
	
