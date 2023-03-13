package br.com.findserv.api.domain.agendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosDetalhamentoVisita(UUID id, UUID idPrestador, UUID idCliente, LocalDateTime data) {

    public DadosDetalhamentoVisita (Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getPrestador().getId(), agendamento.getCliente().getId(), agendamento.getData());

    }

}
