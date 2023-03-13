package br.com.findserv.api.domain.prestador;

import br.com.findserv.api.domain.agendamento.Agendamento;
import br.com.findserv.api.domain.cliente.Cliente;
import br.com.findserv.api.domain.cliente.DadosCadastroCliente;
import br.com.findserv.api.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PrestadorRepositoryTest {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico prestador cadastrado não esta disponivel na data")
    void escolherPrestadorAleatorioCenario1() {

    //given ou arrange
    var proximaSegundaAs10 = LocalDate.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .atTime(10, 0);
    var prestador = cadastrarPrestador("Prestador", "prestador@findserv.com.br", "12345678912", ServicoPrestado.ELETRICISTA);
    var cliente = cadastrarCliente("Cliente", "cliente@findserv.com.br", "12345678912");
    agendarVisita(prestador, cliente, proximaSegundaAs10);

    //when ou act
    var prestadorLivre = prestadorRepository.escolherPrestadorAleatorio(ServicoPrestado.ELETRICISTA, proximaSegundaAs10);

    //then ou assert
    assertThat(prestadorLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver prestador quando ele estiver disponivel na data")
    void escolherPrestadorAleatorioCenario2() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var prestador = cadastrarPrestador("Prestador", "prestador@findserv.com.br", "123456", ServicoPrestado.ELETRICISTA);

        //when ou act
        var prestadorLivre = prestadorRepository.escolherPrestadorAleatorio(ServicoPrestado.ELETRICISTA, proximaSegundaAs10);

        //then ou assert
        assertThat(prestadorLivre).isEqualTo(prestador);
    }

    private void agendarVisita(Prestador prestador, Cliente cliente, LocalDateTime data) {
        em.persist(new Agendamento(null, prestador, cliente, data));
    }

    private Prestador cadastrarPrestador(String nome, String email, String cpf, ServicoPrestado servicoPrestado) {
        var prestador = new Prestador(dadosPrestador(nome, email, cpf, servicoPrestado));
        em.persist(prestador);
        return prestador;
    }

    private Cliente cadastrarCliente(String nome, String email, String cpf) {
        var cliente = new Cliente(dadosCliente(nome, email, cpf));
        em.persist(cliente);
        return cliente;
    }

    private DadosCadastroPrestador dadosPrestador(String nome, String email, String cpf, ServicoPrestado servicoPrestado) {
        return new DadosCadastroPrestador(
                nome,
                email,
                "12345678912",
                cpf,
                servicoPrestado,
                dadosEndereco()
        );
    }

    private DadosCadastroCliente dadosCliente(String nome, String email, String cpf) {
        return new DadosCadastroCliente(
                nome,
                email,
                "12345678912",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }





}