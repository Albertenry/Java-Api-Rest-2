-- v1__create-table-admin.sql

create table admin (
    id serial primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

insert into admin (username, password)
values ('admin', 'senha123');
