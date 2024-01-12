create table devices (
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id serial primary key,
    name varchar(255)
);

create table devices_people
(
    id serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values('Iphone T1000', 160000);
insert into devices(name, price) values('Corona', 300);
insert into devices(name, price) values('Kia K8', 2000000);
insert into devices(name, price) values('I Matches', 160);

insert into people(name) values('Maria');
insert into people(name) values('Dorian');
insert into people(name) values('Konokava');

insert into devices_people(device_id, people_id) values(4, 1);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(2, 3);

select avg(price) from devices;
select p.name as Name, avg(d.price) as Price from devices_people dp join devices d on dp.device_id=d.id join people p on dp.people_id = p.id group by p.name;
select p.name as Name, avg(d.price) as Price from devices_people dp join devices d on dp.device_id=d.id join people p on dp.people_id = p.id group by p.name having avg(d.price)>5000;