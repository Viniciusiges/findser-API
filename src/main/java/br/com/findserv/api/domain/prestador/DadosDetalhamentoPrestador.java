package br.com.findserv.api.domain.prestador;

import br.com.findserv.api.domain.endereco.Endereco;

import java.util.UUID;

public record DadosDetalhamentoPrestador(UUID id, String nome, String email, String cpf, String telefone, ServicoPrestado servicoPrestado, Endereco endereco) {

    public DadosDetalhamentoPrestador (Prestador prestador) {

        this(prestador.getId(),
                prestador.getNome(),
                prestador.getEmail(),
                prestador.getCpf(),
                prestador.getTelefone(),
                prestador.getServicoPrestado(),
                prestador.getEndereco());

    }
}
