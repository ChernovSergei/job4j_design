select * from fauna where discovery_date<'01.01.1950';
select * from fauna where discovery_date is null;
select * from fauna where avg_age>15000 and avg_age<=21000;
select * from fauna where name='fish';