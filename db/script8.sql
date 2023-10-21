create table car_bodies(
    id serial primary key,
    name varchar(255)
);
create table car_engines(
    id serial primary key,
    name varchar(255)
);
create table car_transmissions(
    id serial primary key,
    name varchar(255)
);
create table cars(
    id serial primary key,
    name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);


insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Хетчбек');
insert into car_bodies(name) values('Универсал');
insert into car_engines(name) values('3zz-fe');
insert into car_engines(name) values('1jz');
insert into car_engines(name) values('1zz-fe');
insert into car_transmissions(name) values('Механика');
insert into car_transmissions(name) values('Автомат');
insert into car_transmissions(name) values('Робот');

insert into cars(name, body_id, engine_id, transmission_id) values('Королла', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Опель', 2, 2, 2);
insert into cars(name, body_id) values('Лада', 1);

select c.name, b.name, e.name, t.name 
from cars c left join car_bodies b on c.body_id = b.id
			left join car_engines e on c.engine_id = e.id
			left join car_transmissions t on c.transmission_id = t.id;

select b.name "Не используется" from car_bodies b left join cars c on b.id = c.body_id where c.id is null;
select e.name "Не используется" from car_engines e left join cars c on e.id = c.body_id where c.id is null;
select t.name "Не используется" from car_transmissions t left join cars c on t.id = c.body_id where c.id is null;

