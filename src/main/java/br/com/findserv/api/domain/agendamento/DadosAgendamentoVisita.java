package br.com.findserv.api.domain.agendamento;

import br.com.findserv.api.domain.prestador.ServicoPrestado;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosAgendamentoVisita(
        UUID idPrestador,

        @NotNull
        UUID idCliente,

        ServicoPrestado servicoPrestado,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data) {
}
