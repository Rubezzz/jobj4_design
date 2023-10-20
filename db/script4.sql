create table mather(
    id serial primary key,
    name varchar(255)
);
create table children(
    id serial primary key,
    name varchar(255),
	age int,
	mather_id int references mather(id)
);

insert into mather(name) values('Anna');
insert into mather(name) values('Elena');
insert into children(name, age, mather_id) values('Egor', 10, 1);
insert into children(name, age, mather_id) values('Mike', 8, 1);
insert into children(name, age, mather_id) values('Kate', 25, 2);

select m.name, ch.name from children ch join mather m on ch.mather_id = m.id;
select m.name "Мама", ch.name "Ребенок" from children ch join mather m on ch.mather_id = m.id where m.name = 'Elena';
select m.name "Мама", ch.name "Ребенок", ch.age "Возраст" from children ch join mather m on ch.mather_id = m.id where ch.age > 8;