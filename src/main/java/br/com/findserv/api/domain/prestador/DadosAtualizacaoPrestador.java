package br.com.findserv.api.domain.prestador;

import br.com.findserv.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAtualizacaoPrestador(

        @NotNull
        UUID id,
        String nome,
        String telefone,
        DadosEndereco endereco,
        ServicoPrestado servicoPrestado) {
}
