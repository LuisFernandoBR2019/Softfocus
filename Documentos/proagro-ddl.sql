drop database softfocus;
create database softfocus with encoding 'utf8';

\c softfocus;

create table comunicacao_perda(
id bigserial primary key,
nome text not null,
email text not null,
cpf varchar(14) not null,
latitude double precision not null,
longitude double precision not null,
tipo_lavoura text not null,
data_colheita text not null,
evento_ocorrido text not null
);