package com.api.obra.controller;

import com.api.obra.domain.localizacao.LocalizacaoService;
import com.api.obra.domain.localizacao.model.LocalizacaoRequest;
import com.api.obra.domain.localizacao.model.ObraLocalizacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    @PostMapping("/{obraId}/localizacoes")
    public ResponseEntity<Void> criarLocalizacao(@Valid @RequestBody LocalizacaoRequest localizacaoRequest,
                                                 @PathVariable(name = "obraId") Long obraId) {
        var localizacao = localizacaoService.criar(localizacaoRequest, obraId);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(localizacao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/localizacoes")
    public ResponseEntity<Page<ObraLocalizacaoResponse>> listarTodasLocalizacoes(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy
    ) {
        return ResponseEntity.ok(localizacaoService.listarTodasLocalizacoes(page, linesPerPage, direction, orderBy));
    }

    @GetMapping("/localizacoes/{id}")
    public ResponseEntity<ObraLocalizacaoResponse> recuperarLocalizacao(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(localizacaoService.recuperarLocalizacaoPorId(id));
    }

    @PatchMapping("/localizacoes/{id}")
    public ResponseEntity<ObraLocalizacaoResponse> atualizarLocalizacao(@PathVariable(name = "id") Long id,
                                                                        @RequestBody LocalizacaoRequest localizacaoRequest) {
        return ResponseEntity.ok(localizacaoService.atualizarLocalizacaoPorId(id, localizacaoRequest));
    }

    @DeleteMapping("/localizacoes/{id}")
    public ResponseEntity<Void> apagarLocalizacao(@PathVariable(name = "id") Long id) {
        localizacaoService.apagarLocalizacaoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
