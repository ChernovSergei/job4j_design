
create table itemcomments(
	id serial primary key,
	itemcomment text
);

create table attachments(
	id serial primary key,
	attachment text
);

create table items(
	id serial primary key,
	item text,
	comment_id int references itemcomments(id),
	attach_id int references attachments(id)
);

create table users(
	id serial primary key,
	name text,
	item_id int references items(id)
);

create table rules(
	id serial primary key,
	rule text
);

create table roles(
	id serial primary key,
	name text,
	user_id int references users(id),
	rule_id int references rules(id)
);


create table categories(
	id serial primary key,
	category text,
	item_id int references items(id)
);

create table states(
	id serial primary key,
	itemstate text,
	item_id int references items(id)
);

insert into attachments(attachment) values('Chair photo');
insert into itemcomments(itemcomment) values('Leathern chair');
insert into items(item, comment_id, attach_id) values('CEO chair', 1, 1);
insert into categories(category, item_id) values('Furniture', 1);
insert into states(itemstate, item_id) values('active', 1);
insert into users(name, item_id) values('Greg Rojas', 1);
insert into rules(rule) values('Manage a company');
insert into roles(name, user_id, rule_id) values('CEO', 1, 1);