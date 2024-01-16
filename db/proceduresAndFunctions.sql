create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(nam varchar, prod varchar, coun integer, pric integer)
language 'plpgsql' as $$
	begin
		insert into products(name, producer, count, price)
		values(nam, prod, coun, pric);
	end
$$;

call insert_data('apples', 'Belarus', 1000, 10);
call insert_data('bananas', 'Ecuador', 500, 50);
call insert_data('tulpan', 'Niderlands', 10, 1000);

create or replace procedure clear_products(p_price integer, p_count integer)
language 'plpgsql' as $$
	BEGIN
		if p_count > 0 then
			delete from products where count < p_count;
		end if;
		if p_price > 0 then
			delete from products where price < p_price;
		end if;
	END;
$$;

call clear_products(20, 0);
call clear_products(0, 400);
select * from products;

call insert_data('apples', 'Belarus', 1000, 10);
call insert_data('tulpan', 'Niderlands', 10, 1000);

create or replace function del_products(p_price integer, p_count integer)
returns integer
language 'plpgsql' as $$
	declare
		result integer;
		records_before integer;
		records_after integer;
	begin
		select into records_before count(id) from products;
		if p_price > 0 then
			delete from products where price < p_price;			
		end if;
		if p_count > 0 then
			delete from products where count < p_count;
		end if;
		select into records_after count(id) from products;
		result = records_before - records_after;
		return result;
	end;
$$;

select del_products(0, 700);

call insert_data('bananas', 'Ecuador', 500, 50);
call insert_data('tulpan', 'Niderlands', 10, 1000);

select del_products(20, 0);