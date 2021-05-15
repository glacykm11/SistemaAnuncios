-- Criando a base de dados
create database dbAnuncios;

-- Usando a base de dados dbAnuncios
use dbAnuncios;

-- Criando tabela de anúncios
create table tbAnuncios(
    idAnuncio int primary key auto_increment,
    nomeAnuncio varchar (50) not null,
    cliente varchar (50) not null unique,
    dataInicio varchar(10) not null,
    dataTermino varchar(10) not null,
    investimento float not null
);   




