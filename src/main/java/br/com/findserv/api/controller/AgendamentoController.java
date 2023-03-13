package br.com.findserv.api.controller;

import br.com.findserv.api.domain.agendamento.AgendamentoDeVisita;
import br.com.findserv.api.domain.agendamento.DadosAgendamentoVisita;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoDeVisita visita;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoVisita dados) {
        var dto = visita.agendar(dados);
        return ResponseEntity.ok(dto);

    }

}
