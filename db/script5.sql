create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('IPhone 14', 1000);
insert into devices(name, price) values('IPhone 15', 1500);
insert into devices(name, price) values('Honor', 700);
insert into devices(name, price) values('Realme', 500);
insert into people(name) values('Anton');
insert into people(name) values('Elena');
insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(3, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(4, 2);

select avg(d.price) from devices d;
select avg(d.price) "Ср. цена устройств", p.name "Имя владельца" from devices_people dp join people p on dp.people_id = p.id join devices d on dp.device_id = d.id group by p.name;
select avg(d.price) "Ср. цена устройств", p.name "Имя владельца" from devices_people dp join people p on dp.people_id = p.id join devices d on dp.device_id = d.id group by p.name having avg(d.price) > 5000;