package br.com.findserv.api.controller;

import br.com.findserv.api.domain.cliente.*;;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBilder) {

        var cliente = new Cliente(dados);
        repository.save(cliente);

        var uri = uriBilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoesCliente(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    /*@DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable UUID id) {
        repository.deleteById(id);
    }*/

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativar (@PathVariable UUID id) {
        var cliente = repository.getReferenceById(id);
        cliente.inativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable UUID id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }



}
