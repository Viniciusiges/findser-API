package br.com.findserv.api.domain.agendamento.validacoes;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoVisita {

    public void validar (DadosAgendamentoVisita dados) {
        var dataVisita = dados.data();
        var agora = LocalDateTime.now();
        var diferencaHoras = Duration.between(agora, dataVisita).toHours();

        if (diferencaHoras < 2) {
            throw new ValidacaoException("A visita deve ser agenda com no minimo 2 horas de antecedencia");
        }
    }

}
