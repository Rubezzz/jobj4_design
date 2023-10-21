create table departments(
    id serial primary key,
    name varchar(255)
);
create table employees(
    id serial primary key,
    name varchar(255),
	departments_id int references departments(id)
);
create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(1)
);


insert into departments(name) values('Бухгалтерия');
insert into departments(name) values('IT');
insert into departments(name) values('Администрация');
insert into departments(name) values('Охрана');
insert into employees(name, departments_id) values('Анна', 1);
insert into employees(name, departments_id) values('Елена', 1);
insert into employees(name, departments_id) values('Артем', 2);
insert into employees(name, departments_id) values('Иван', 3);
insert into employees(name, departments_id) values('Дмитрий', 3);
insert into employees(name, departments_id) values('Катерина', 3);

insert into teens(name, gender) values('Анна', 'ж');
insert into teens(name, gender) values('Елена', 'ж');
insert into teens(name, gender) values('Катерина', 'ж');
insert into teens(name, gender) values('Артем', 'м');
insert into teens(name, gender) values('Дмитрий', 'м');

select d.name from departments d left join employees e on e.departments_id = d.id where e.id is null;
select d.name "Департамент", e.name "Работник" from employees e left join departments d on e.departments_id = d.id; 
select d.name "Департамент", e.name "Работник" from departments d right join employees e on e.departments_id = d.id; 

select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;