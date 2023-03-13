package br.com.findserv.api.domain.prestador;


import java.util.UUID;

public record DadosListagemPrestador(UUID id, String nome, String email, String telefone, ServicoPrestado servicoPrestado) {

    public DadosListagemPrestador (Prestador prestador) {
        this(prestador.getId(), prestador.getNome(), prestador.getEmail(), prestador.getTelefone(), prestador.getServicoPrestado());
    }

}
