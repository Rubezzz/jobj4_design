create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Silver fish', '9000', '1911-01-01');
insert into fauna(name, avg_age, discovery_date) values('White Bear', '11000', '1960-05-05');
insert into fauna(name, avg_age, discovery_date) values('Akula', '15000', '1800-10-10');
insert into fauna(name, avg_age, discovery_date) values('Blach fish', '18000', null);
insert into fauna(name, avg_age, discovery_date) values('Elephant', '20000', '1700-10-10');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';