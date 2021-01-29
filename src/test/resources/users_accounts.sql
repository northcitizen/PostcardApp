CREATE TABLE USER(
    id uuid,
    email varchar,
    first_name varchar,
    last_name varchar
);
delete from USER;
insert into user (id, email, first_name, last_name) values ('a315e6ea-0c35-496e-9859-431211185371', 'af@gmail.com', 'Alex', 'Fisher');
SELECT * FROM USER;
insert into user (id, email, first_name, last_name) values ('b315e6ea-0c35-496e-9859-431211185371', 'af@gmail.com', 'Alex', 'Fisher');
