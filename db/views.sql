create table applications
(
	id serial primary key,
	name text,
	type_id int,
	creation_date date,
	price int
);

create table app_type(
	id serial primary key,
	type text
);

insert into app_type(type) values('web');
insert into app_type(type) values('mobile');
insert into app_type(type) values('windows');
insert into app_type(type) values('IOS');

insert into applications(name, type_id, creation_date, price) values('winamp', 3, '01-03-1996', 300);
insert into applications(name, type_id, creation_date, price) values('photoshop', 3, '15-08-1998', 10000);
insert into applications(name, type_id, creation_date, price) values('i tunes', 4, '01-03-2001', 13300);
insert into applications(name, type_id, creation_date, price) values('vkontakte', 1, '12-12-2004', 10);
insert into applications(name, type_id, creation_date, price) values('google', 1, '27-05-1992', 200);
insert into applications(name, type_id, creation_date, price) values('tetris', 2, '12-02-1990', 300);
insert into applications(name, type_id, creation_date, price) values('say hi', 2, '22-07-2020', 1000);

create view most_cheapest_application as
select app.name, app.creation_date, tp.type, app.price from applications as app join app_type tp on app.type_id=tp.id where app.price=(select min(applications.price) from applications);

select * from most_cheapest_application