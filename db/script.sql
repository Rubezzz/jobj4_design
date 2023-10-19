create table dogs(
      id serial primary key,
      name varchar(250),
      years smallint,
	  color varchar(250)
);
insert into dogs (name, years, color) values ('gav', '4', 'black');
update dogs set years = '5';
delete from dogs;