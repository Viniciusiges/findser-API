package br.com.findserv.api.domain.cliente;

import java.util.UUID;

public record DadosListagemCliente(UUID id, String nome, String email, String telefone) {

    public DadosListagemCliente (Cliente cliente) {
        this(cliente.getId() ,cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }

}
