package br.com.findserv.api.domain.agendamento.validacoes;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoVisita {

    public void validar(DadosAgendamentoVisita dados) {
        var dataAgendamento = dados.data();

        var domingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesHorario = dataAgendamento.getHour() < 8;
        var depoisHorario = dataAgendamento.getHour() > 18;

        if (domingo || antesHorario || depoisHorario) {
            throw new ValidacaoException("Agendamento fora do horario de funcionamento");
        }
    }

}
