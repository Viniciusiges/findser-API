create table agendamento(

    id uuid primary key not null default uuid_generate_v4(),
    prestador_id uuid not null,
    cliente_id uuid not null,
    data timestamp not null,

    foreign key(prestador_id) references prestador(id),
    foreign key(cliente_id) references cliente(id)

);