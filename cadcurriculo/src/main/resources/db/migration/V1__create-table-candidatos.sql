-- v1__create-table-candidatos.sql

create table candidatos (
    id serial primary key,
    nome varchar(255) not null,
    cpf varchar(11) not null,
    data_nascimento date,
    email varchar(255) not null,
    telefone varchar(15) not null,
    escolaridade varchar(50),
    funcao varchar(255)
);

insert into candidatos (nome, cpf, data_nascimento, email, telefone, escolaridade, funcao)
values ('Albertenry Menezes', '00000000000', '13-03-1979', 'albertenry2@gmail.com', '85982334210', 'SUPERIOR_INCOMPLETO', 'Fullstack');
