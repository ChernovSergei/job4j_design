create table alians(
	id serial primary key,
	name varchar(100),
	limbs_number integer
)

insert into alians(name, limbs_number) values ('Alian', 5);
insert into alians(name, limbs_number) values ('Predator', 4);
insert into alians(name, limbs_number) values ('Thing', null);

set session transaction isolation level read uncommited;