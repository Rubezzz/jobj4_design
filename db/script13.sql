CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values('Ivan', 'Ivanov', 20, 'RU');
insert into customers(first_name, last_name, age, country) values('Peyr', 'Petrov', 20, 'KZ');
insert into customers(first_name, last_name, age, country) values('Igor', 'Ddhdsfh', 27, 'RU');
insert into customers(first_name, last_name, age, country) values('Anna', 'Gdsdgsd', 40, 'RU');

select * from customers c where c.age = (select min(c2.age) from customers c2);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values(100, 1);
insert into orders(amount, customer_id) values(52, 3);
insert into orders(amount, customer_id) values(13, 3);

select * from customers c where c.id not in (select o.customer_id from orders o);
