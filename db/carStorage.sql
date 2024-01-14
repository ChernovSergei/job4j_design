create table car_bodies (
	id serial primary key,
	name text
);

create table car_engines (
	id serial primary key,
	name text
);

create table car_transmissions(
	id serial primary key,
	name text
);

create table cars(
	id serial primary key,
	name text,
	transmission_id int references car_transmissions(id),
	engine_id int references car_engines(id),
	body_id int references car_bodies(id)
);

insert into car_bodies(name) values('sedan');
insert into car_bodies(name) values('pickup');
insert into car_bodies(name) values('van');

insert into car_engines(name) values('cat');
insert into car_engines(name) values('hyundai');
insert into car_engines(name) values('cummins');

insert into car_transmissions(name) values('auto');
insert into car_transmissions(name) values('mechanic');
insert into car_transmissions(name) values('semi-auto');

insert into cars(name, transmission_id, engine_id, body_id) values ('Tesla S', 1, null, 1);
insert into cars(name, transmission_id, engine_id, body_id) values ('Kamaz', 2, 2, null);
insert into cars(name, transmission_id, engine_id, body_id) values ('Toyota hilux', 1, 1, 2);
insert into cars(name, transmission_id, engine_id, body_id) values ('JJ', null, 1, 1);
insert into cars(name, transmission_id, engine_id, body_id) values ('Mazda 6', 1, 2, 1);

select c.id as "car id", c.name as "car name", b.name as "body type", e.name as "engine", t.name as "transmission"
from cars c 
left join car_transmissions t on c.transmission_id=t.id 
left join car_engines e on c.engine_id=e.id 
left join car_bodies b on c.body_id=b.id;

select c.id as "car id", c.name as "car name", b.name as "body type", e.name as "engine", t.name as "transmission"
from cars c 
right join car_transmissions t on c.transmission_id=t.id 
left join car_engines e on c.engine_id=e.id 
left join car_bodies b on c.body_id=b.id
where c.transmission_id is null; 

select c.id as "car id", c.name as "car name", b.name as "body type", e.name as "engine", t.name as "transmission"
from cars c 
left join car_transmissions t on c.transmission_id=t.id 
right join car_engines e on c.engine_id=e.id 
left join car_bodies b on c.body_id=b.id
where c.engine_id is null; 

select c.id as "car id", c.name as "car name", b.name as "body type", e.name as "engine", t.name as "transmission"
from cars c 
left join car_transmissions t on c.transmission_id=t.id 
left join car_engines e on c.engine_id=e.id 
right join car_bodies b on c.body_id=b.id
where c.body_id is null; 
