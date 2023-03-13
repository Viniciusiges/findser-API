create table prestador(

   id uuid primary key not null default uuid_generate_v4(),
   nome varchar(100) not null,
   email varchar(100) not null unique,
   cpf varchar(11) not null unique,
   telefone varchar(10) not null,
   servico_prestado varchar(100) not null,
   cep varchar(9) not null,
   uf char(2) not null,
   cidade varchar(100) not null,
   bairro varchar(100) not null,
   rua varchar(100) not null,
   numero varchar(20),
   complemento varchar(100),
   ativo boolean

);

