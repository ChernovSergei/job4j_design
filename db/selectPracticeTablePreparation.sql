create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date) values('Elephan',600000,'01-02-1400');
insert into fauna(name, avg_age, discovery_date) values('Kakapo',480000,'24-08-1845');
insert into fauna(name, avg_age, discovery_date) values('Cat',160000,'01-05-0001');
insert into fauna(name, avg_age, discovery_date) values('Cockroach',5000,'15-12-0981');
insert into fauna(name, avg_age, discovery_date) values('fish',17000,'01-02-2000');
insert into fauna(name, avg_age, discovery_date) values('Alian',18413,'01-01-2024');
insert into fauna(name, avg_age, discovery_date) values('Ant',19321,'01-02-1721');