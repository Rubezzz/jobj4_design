CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values(1, 'company 1');
insert into company(id, name) values(3, 'company 3');
insert into company(id, name) values(5, 'company 5');

insert into person(id, name, company_id) values(1, 'Ivan', 1);
insert into person(id, name, company_id) values(2, 'Max', 3);
insert into person(id, name, company_id) values(3, 'Yana', 5);
insert into person(id, name, company_id) values(4, 'Serj', 1);
insert into person(id, name, company_id) values(5, 'Anna', 1);
insert into person(id, name, company_id) values(6, 'Dmitr', 3);
insert into person(id, name, company_id) values(7, 'Petr', 3);

select p.name, c.name 
from person p 
left join company c on p.company_id = c.id 
where c.id <> 5;

select c.name "Компания", count(*) "Кол-во сотрудников"
from person p 
left join company c on p.company_id = c.id 
group by c.name
having count(*) = (select max(t.count) 
				   from (select count(*) count
				   	     from person p 
				         left join company c on p.company_id = c.id 
				         group by c.name) t);
