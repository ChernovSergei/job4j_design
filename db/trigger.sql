create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function tax_statement()
	returns trigger as 
$$
BEGIN
	update products
	set price = price + price * 0.4
	where id = (select id from inserted);
	return new;
END;
$$
LANGUAGE 'plpgsql';

create trigger after_tax_trigger
after insert
on products
referencing new table as inserted
for each statement
execute procedure tax_statement();

create or replace function tax_for_rows()
returns trigger as
$$
BEGIN
	update products set price = price + price * 0.3 where id = new.id;
	return NEW;
END;
$$
LANGUAGE 'plpgsql';

create or replace trigger before_tax_trigger
before insert on products
for each row
execute function tax_for_rows();

create or replace function update_history()
returns trigger as
$$
BEGIN
	insert into history_of_price (name, price, date) values (NEW.name, NEW.price + NEW.price *0.2, current_date);
	return NEW;
END;
$$
LANGUAGE 'plpgsql';

create or replace trigger history
before insert on products
for each row
execute function update_history();