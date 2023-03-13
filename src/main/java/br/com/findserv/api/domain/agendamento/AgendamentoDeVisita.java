package br.com.findserv.api.domain.agendamento;

import br.com.findserv.api.domain.ValidacaoException;
import br.com.findserv.api.domain.agendamento.validacoes.ValidadorAgendamentoVisita;
import br.com.findserv.api.domain.cliente.ClienteRepository;
import br.com.findserv.api.domain.prestador.Prestador;
import br.com.findserv.api.domain.prestador.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoDeVisita {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private List<ValidadorAgendamentoVisita> validadores;
    public DadosDetalhamentoVisita agendar (DadosAgendamentoVisita dados) {
        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Id do Cliente informado não existente");
        }

        if (dados.idPrestador() != null && !prestadorRepository.existsById(dados.idPrestador())) {
            throw new ValidacaoException("Id do Prestador informado não existente");
        }


        validadores.forEach(v -> v.validar(dados));

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var prestador = escolherPrestador(dados);
        if (prestador == null) {
            throw new ValidacaoException("Não existe um prestador disponivel nessa data");
        }

        var agendamento = new Agendamento(null, prestador, cliente, dados.data());

        agendamentoRepository.save(agendamento);

        return new DadosDetalhamentoVisita(agendamento);


    }

    private Prestador escolherPrestador(DadosAgendamentoVisita dados) {
        if (dados.idPrestador() != null) {
            return prestadorRepository.getReferenceById(dados.idPrestador());
        }
        if (dados.servicoPrestado() == null) {
            throw new ValidacaoException ("Serviço prestado é obrigatorio quando prestador não for selecionado");
        }
        return prestadorRepository.escolherPrestadorAleatorio(dados.servicoPrestado(), dados.data());
    }

}
