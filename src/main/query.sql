CREATE DATABASE crud_pessoa;

CREATE TABLE IF NOT EXISTS pessoa(
    id Serial NOT NULL PRIMARY KEY ,
    nome text NOT NULL,
    senha text NOT NULL

);
select * from pessoa