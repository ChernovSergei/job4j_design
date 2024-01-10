create table bankAccount(
	id serial primary key,
	bankAccount numeric
);

create table customer(
	id serial primary key,
	name varchar(255),
	bankAccount_id int references bankAccount(id)
);

insert into bankAccount(bankAccount) values (0001);
insert into bankAccount(bankAccount) values (0010);
insert into bankAccount(bankAccount) values (0011);
insert into bankAccount(bankAccount) values (0111);
insert into bankAccount(bankAccount) values (1111);
insert into customer(name, bankAccount_id) values ('Alexey', 1);
insert into customer(name, bankAccount_id) values ('Alexey', 2);
insert into customer(name, bankAccount_id) values ('Ogren', 3);
insert into customer(name, bankAccount_id) values ('Miyo', 4);
insert into customer(name, bankAccount_id) values ('Miyo', 5);

select * from customer;