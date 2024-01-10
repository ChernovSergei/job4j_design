create table taxNumber(
	id serial primary key,
	taxNumber int
);

create table customer(
	id serial primary key,
	name varchar(255),
	taxNumber_id int references taxNumber(id) unique
);

insert into taxNumber(taxNumber) values (1111);
insert into taxNumber(taxNumber) values (2222);
insert into taxNumber(taxNumber) values (3333);
insert into customer(name, taxNumber_id) values ('Alexey', 1);
insert into customer(name, taxNumber_id) values ('Ogren', 2);
insert into customer(name, taxNumber_id) values ('Miyo', 3);

select * from customer;
