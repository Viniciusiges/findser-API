create table usuario(

   id uuid primary key not null default uuid_generate_v4(),
   login varchar(100) not null,
   senha varchar(255) not null

);

insert into usuario (login, senha) values ('joao.alberto@findserv.com.br', '$2a$12$ATrJ.bRmppyLmlvDijVqPu7wQ2dMcXGmHbBjMUyWg/i6XMiBUxnN.')