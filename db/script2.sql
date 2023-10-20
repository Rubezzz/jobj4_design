create table wife(
    id serial primary key,
    name varchar(255),
    number int
);
create table husband(
    id serial primary key,
    name varchar(255),
    number int
);
create table wife_husband(
    id serial primary key,
    wife_id int references wife(id) unique,
    husband_id int references husband(id) unique
);

create table imei(
    id serial primary key,
    imei varchar(255)
);
create table phone(
    id serial primary key,
    name varchar(255),
    imei_id int references imei(id) unique
);

 create table house(
     id serial primary key,
     name varchar(255)
 );
 create table owner(
     id serial primary key,
     name varchar(255)
 );
 create table house_owner(
     id serial primary key,
     house_id int references house(id),
     owner_id int references owner(id)
 );

 create table mather(
    id serial primary key,
    name varchar(255)
);
create table children(
    id serial primary key,
    name varchar(255),
	mather_id int references mather(id)
);


