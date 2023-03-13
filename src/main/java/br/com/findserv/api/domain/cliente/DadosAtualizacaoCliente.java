package br.com.findserv.api.domain.cliente;

import br.com.findserv.api.domain.endereco.DadosEndereco;
import br.com.findserv.api.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAtualizacaoCliente(


        @NotNull
        UUID id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
