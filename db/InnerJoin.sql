create table productTypes(
	id serial primary key,
	productType text
);

create table products_db(
	id serial primary key,
	name text,
	type_id int references productTypes(id)
);

insert into productTypes(productType) values('Toy');
insert into productTypes(productType) values('Kitchenware');
insert into productTypes(productType) values('Furniture');

insert into products_db(name, type_id) values('Spider Man', 1);
insert into products_db(name, type_id) values('Lego', 1);
insert into products_db(name, type_id) values('Dishes', 2);
insert into products_db(name, type_id) values('Chair', 3);
insert into products_db(name, type_id) values('Sofa', 3);

select p.name, t.productType from products_db as p join productTypes as t on p.type_id = t.id;
select p.name as Name, t.productType as Type from products_db p join productTypes t on p.type_id = t.id;
select p.name as "Product Name", t.productType as "Product Type" from products_db p join productTypes t on p.type_id = t.id;