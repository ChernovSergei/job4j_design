create table customers (
	id serial primary key,
	first_name text,
	last_name text,
	age int,
	country text
);

insert into customers(first_name, last_name, age, country) values ('sergei', 'chernov', 38, 'Kazakhstan');
insert into customers(first_name, last_name, age, country) values ('vlad', 'deriglazov', 55, 'Georgia');
insert into customers(first_name, last_name, age, country) values ('alexandra', 'mamutchenko', 27, 'Uganda');
insert into customers(first_name, last_name, age, country) values ('inna', 'terutchenko', 43, 'UK');
insert into customers(first_name, last_name, age, country) values ('tumbulak', 'telegenov', 31, 'USA');

select * from customers c where c.age = (select max(customers.age) from customers);

create table orders(
	id serial primary key,
	amount int,
	customer_id int references customers(id)
);

insert into orders(amount, customer_id) values(1000, 1);
insert into orders(amount, customer_id) values(3000, 3);
insert into orders(amount, customer_id) values(2423, 1);
insert into orders(amount, customer_id) values(5923, 4);
insert into orders(amount, customer_id) values(59493, 3);

select * from customers c where c.id not in (select o.customer_id from orders o);