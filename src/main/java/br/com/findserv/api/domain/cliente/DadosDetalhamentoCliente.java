package br.com.findserv.api.domain.cliente;

import br.com.findserv.api.domain.endereco.Endereco;
import java.util.UUID;

public record DadosDetalhamentoCliente(UUID id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoCliente (Cliente cliente) {

           this(cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEndereco());

    }

}
