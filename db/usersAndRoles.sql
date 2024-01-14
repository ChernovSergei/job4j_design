create table categories(
	id serial primary key,
	category text
);

create table states(
	id serial primary key,
	itemstate text
);

create table rules(
	id serial primary key,
	rule text
);

create table roles(
	id serial primary key,
	name text
);

create table rolesAndRules(
	id serial primary key,
	rule_id int references rules(id),
	role_id int references roles(id)
);



create table users(
	id serial primary key,
	name text,
	role_id int references roles(id)
);

create table items(
	id serial primary key,
	item text,
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

create table itemcomments(
	id serial primary key,
	itemcomment text,
	item_id int references items(id)
);

create table attachments(
	id serial primary key,
	attachment text,
	item_id int references items(id)
);

insert into rules(rule) values('Manage a company');
insert into roles(name) values('CEO');
insert into rolesAndRules(rule_id, role_id) values(1, 1);
insert into users(name, role_id) values('Greg Rojas', 1);
insert into categories(category) values('Furniture');
insert into states(itemstate) values('active');
insert into items(item, user_id, category_id, state_id) values('CEO chair', 1, 1, 1);
insert into attachments(attachment, item_id) values('Chair photo', 1);
insert into itemcomments(itemcomment, item_id) values('Leathern chair', 1);