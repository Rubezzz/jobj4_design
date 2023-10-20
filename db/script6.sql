create table type(
    id serial primary key,
    name varchar(255)
);
create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('ХЛЕБ');
insert into type(name) values('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '2023-11-10', 200);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 1, '2024-01-10', 300);
insert into product(name, type_id, expired_date, price) values('Сыр домашний', 1, '2024-01-05', 350);
insert into product(name, type_id, expired_date, price) values('Сыр домашний 2', 1, '2024-01-05', 350);
insert into product(name, type_id, expired_date, price) values('Молоко коровкино', 2, '2023-12-20', 80);
insert into product(name, type_id, expired_date, price) values('Молоко домик в деревне', 2, '2023-12-30', 90);
insert into product(name, type_id, expired_date, price) values('Молоко просроченое', 2, '2022-12-30', 5);
insert into product(name, type_id, expired_date, price) values('Мороженое ванильное', 4, '2024-05-20', 50);
insert into product(name, type_id, expired_date, price) values('Мороженое шоколадное', 4, '2024-05-10', 60);

select p.name "Продукт", t.name "Тип" from product p join type t on p.type_id = t.id where t.name = 'СЫР';
select p.name "Продукт" from product p where lower(p.name) like '%мороженое%';
select p.name "Продукт", p.expired_date "Срок годности" from product p where p.expired_date < current_date;
select p.name "Продукт", p.price "Цена" from product p where p.price = (select max(price) from product);
select t.name "Тип", count(*) "Количество продуктов" from product p join type t on p.type_id = t.id group by t.name;
select p.name "Продукт", t.name "Тип" from product p join type t on p.type_id = t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';
select t.name "Тип" from type t where 10 < (select count(*) from product p where p.type_id = t.id);
select p.name "Продукт", t.name "Тип" from product p join type t on p.type_id = t.id;