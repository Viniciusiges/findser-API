package br.com.findserv.api.domain.prestador;
import br.com.findserv.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Table(name = "prestador")
@Entity(name = "Prestador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Prestador {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private ServicoPrestado servicoPrestado;

    @Embedded
    private Endereco endereco;

    public Prestador(DadosCadastroPrestador dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.servicoPrestado = dados.servicoPrestado();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoesPrestador(DadosAtualizacaoPrestador dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
        if (dados.servicoPrestado() != null) {
            this.servicoPrestado = dados.servicoPrestado();
        }

    }

    public void inativar() {
        this.ativo = false;
    }
}
