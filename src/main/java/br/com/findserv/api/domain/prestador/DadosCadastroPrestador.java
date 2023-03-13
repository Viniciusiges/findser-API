package br.com.findserv.api.domain.prestador;

import br.com.findserv.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPrestador(

        @NotBlank(message = "Nome é obrigatorio")
        String nome,
        @NotBlank(message = "E-mail é obrigatorio")
        @Email(message = "Formato email invalido")
        String email,
        @NotBlank(message = "CPF é obrigatorio")
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank(message = "Telefone é obrigatorio")
        String telefone,
        @NotNull(message = "Serviço Prestado é obrigatorio")
        ServicoPrestado servicoPrestado,

        @NotNull @Valid DadosEndereco endereco) {

}