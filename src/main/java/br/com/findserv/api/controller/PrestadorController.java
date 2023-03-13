package br.com.findserv.api.controller;

import br.com.findserv.api.domain.prestador.*;
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
@RequestMapping ("prestador")
public class PrestadorController {

    @Autowired
    private PrestadorRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroPrestador dados, UriComponentsBuilder uriBilder) {

        var prestador = new Prestador(dados);
        repository.save(prestador);

        var uri = uriBilder.path("/prestador/{id}").buildAndExpand(prestador.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoPrestador(prestador));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemPrestador>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPrestador::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoPrestador dados) {
        var prestador = repository.getReferenceById(dados.id());
        prestador.atualizarInformacoesPrestador(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPrestador(prestador));
    }

    /*@DeleteMapping("/{cpf}")
    @Transactional
    public void excluir (@PathVariable String cpf) {
        repository.deleteAll();
    }*/

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativar (@PathVariable UUID id) {
        var prestador = repository.getReferenceById(id);
        prestador.inativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable UUID id) {
        var prestador = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPrestador(prestador));
    }
}
