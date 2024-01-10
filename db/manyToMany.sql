create table company(
	id serial primary key,
	name varchar(255)
);

create table building(
	id serial primary key,
	name varchar(255)
);

create table rentedBuildings(
	id serial primary key,
	company_id int references company(id),
	building_id int references building(id)
);

insert into company(name) values('Apple');
insert into company(name) values('Microsoft');
insert into company(name) values('Cat');
insert into company(name) values('Siemens');
insert into company(name) values('Fujitsu');
insert into building(name) values('1A');
insert into building(name) values('1B');
insert into building(name) values('2A');
insert into building(name) values('3C');
insert into rentedBuildings(company_id, building_id) values(1, 1);
insert into rentedBuildings(company_id, building_id) values(1, 2);
insert into rentedBuildings(company_id, building_id) values(1, 4);
insert into rentedBuildings(company_id, building_id) values(2, 2);
insert into rentedBuildings(company_id, building_id) values(3, 2);
insert into rentedBuildings(company_id, building_id) values(4, 3);
insert into rentedBuildings(company_id, building_id) values(5, 4);

select * from rentedBuildings;