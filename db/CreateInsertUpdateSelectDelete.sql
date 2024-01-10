create table engines_db(
	id serial primary key,
	type varchar(255),
	model text,
	power numeric
);

insert into engines_db(type, model, power) values('Turbine', 'Frame-5', '25000');

update engines_db set type='reciprocating';
update engines_db set model='Cat62';

select * from engines_db;

delete from engines_db;