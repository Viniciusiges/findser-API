package br.com.findserv.api.domain.agendamento.validacoes;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.AgendamentoRepository;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteOutraVisitaMesmaData implements ValidadorAgendamentoVisita {

    @Autowired
    private AgendamentoRepository repository;

    public void validar (DadosAgendamentoVisita dados) {
        var primeiroHorario = dados.data().withHour(8);
        var ultimoHorario = dados.data().withHour(18);
        var clienteePossuiOutraVisitaNoDia = repository.existsByClienteIdAndDataBetween(dados.idCliente(), primeiroHorario, ultimoHorario);
        if (clienteePossuiOutraVisitaNoDia) {
            throw new ValidacaoException("Cliente ja possui uma consulta agendada neste dia");
        }


    }

}
