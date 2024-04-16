-- v3__create-table-competencias.sql

create table competencias (
    id serial primary key,
    descricao varchar(255) not null,
    nivel_proficiencia integer not null,
    candidato_id bigint not null references candidatos(id)
);
