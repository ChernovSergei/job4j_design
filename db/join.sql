create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments(name) values('IT');
insert into departments(name) values('Account');
insert into departments(name) values('Maintenance');

insert into employees(name, department_id) values('John', 1);
insert into employees(name, department_id) values('Vasiliy', null);
insert into employees(name, department_id) values('Juan', 2);
insert into employees(name, department_id) values('Mishel', 2);
insert into employees(name, department_id) values('Andreana', null);

select * from employees left join departments on employees.department_id=departments.id
select * from employees left righ departments on employees.department_id=departments.id
select * from employees left cross departments on employees.department_id=departments.id

select * from departments left join employees on employees.department_id=departments.id where employees.id is null

select employees.name, departments.name from employees right join departments on departments.id = employees.department_id
select employees.name, departments.name from departments left join employees on departments.id = employees.department_id

create table teens(
	id serial primary key,
	name text unique,
	gender text
);

insert into teens(name, gender) values('Mishel', 'M');
insert into teens(name, gender) values('Azura', 'F');
insert into teens(name, gender) values('Tomiko', 'F');
insert into teens(name, gender) values('Julian', 'M');
insert into teens(name, gender) values('Nikita', 'M');

select t1.name, t2.name from teens t1 cross join teens t2 where t1.id>t2.id