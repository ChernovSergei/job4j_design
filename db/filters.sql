create table product(
	id serial primary key,
	name text,
	type_id int,
	expired_date date,
	price int
);

create table type(
	id serial primary key,
	name text
);

insert into type(name) values('fish');
insert into type(name) values('cheese');
insert into type(name) values('meat');

insert into product(name, type_id, expired_date, price) values('brynza', 2, '01-09-2019', 1000);
insert into product(name, type_id, expired_date, price) values('cheddar', 2, '01-03-2025', 5000);
insert into product(name, type_id, expired_date, price) values('ham leg', 3, '27-11-2031', 73000);
insert into product(name, type_id, expired_date, price) values('ice cream', 2, '16-10-2021', 500);
insert into product(name, type_id, expired_date, price) values('salmon', 1, '21-02-2024', 3000);

select * from product pr join type tp on pr.type_id=tp.id where tp.name='cheese';
select * from product pr join type tp on pr.type_id=tp.id where pr.name like '%cream%';
select * from product pr join type tp on pr.type_id=tp.id where pr.expired_date<current_date;
select pr.name, price, tp.name from product pr join type tp on pr.type_id=tp.id where price = (select max(pr.price) from product pr);
select pr.name, max(pr.price) from product pr group by pr.name;
select tp.name, count(tp.name) from product pr join type tp on pr.type_id=tp.id group by tp.name;
select tp.name, count(tp.name) from product pr join type tp on pr.type_id=tp.id group by tp.name having count(tp.name)<10;
select * from product pr join type tp on pr.type_id=tp.id group by tp.id, pr.id having tp.name like 'milk' or tp.name like 'cheese';
select * from product pr join type tp on pr.type_id=tp.id;


