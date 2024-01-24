create table movie (
	id serial primary key,
	name text,
	director text
);

create table book (
	id serial primary key,
	title text,
	author text
);

insert into movie(name, director)
values ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');
	   
INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

select m.name from movie m intersect select b.title from book b;
select b.title from book b except select m.name from movie m;

(select b.title from book b except select m.name from movie m)
union
(select m.name from movie m except select b.title from book b);