package br.com.findserv.api.domain.agendamento.validacoes;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.AgendamentoRepository;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorVisitaPrestadorMesmoHorario implements ValidadorAgendamentoVisita {
    @Autowired
    private AgendamentoRepository repository;

    public void validar (DadosAgendamentoVisita dados) {
        var prestadorPossuiOutraVisitaMesmoHorario = repository.existsByPrestadorIdAndData(dados.idPrestador(), dados.data());
        if (prestadorPossuiOutraVisitaMesmoHorario) {
            throw new ValidacaoException("O prestador ja possui uma visita nesse horario");
        }
    }
}
