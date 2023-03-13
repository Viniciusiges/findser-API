package br.com.findserv.api.domain.agendamento.validacoes;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import br.com.findserv.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorAgendamentoVisita {

    @Autowired
    private ClienteRepository repository;

    public void validar (DadosAgendamentoVisita dados) {
        var clienteEstaAtivo = repository.findAtivoById(dados.idCliente());
        if (!clienteEstaAtivo) {
            throw new ValidacaoException("Visita não pode ser agendada com cliente excluido do sistema");
        }
    }


}
