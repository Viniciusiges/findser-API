package br.com.findserv.api.domain.agendamento.validacoes;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import br.com.findserv.api.domain.prestador.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPrestadorAtivo implements ValidadorAgendamentoVisita {

    @Autowired
    private PrestadorRepository repository;

    public void validar (DadosAgendamentoVisita dados) {
        if (dados.idPrestador() == null) {
            return;
        }

        var prestadorEstaAtivo = repository.findAtivoById(dados.idPrestador());
        if (!prestadorEstaAtivo) {
            throw new ValidacaoException("Visita não pode ser agendada com prestador excluido do sistema");
        }
    }

}
