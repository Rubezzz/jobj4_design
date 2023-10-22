delete from products;
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

select * from products;

begin transaction;
delete from products where name = 'product_1';
savepoint save_p1;
insert into products(name, price) values('product_1', 0);
select * from products;
rollback to save_p1;
select * from products;
commit;
select * from products;
