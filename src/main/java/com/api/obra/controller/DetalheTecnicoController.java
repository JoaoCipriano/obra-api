package com.api.obra.controller;

import com.api.obra.domain.detalhetecnico.DetalheTecnicoService;
import com.api.obra.domain.detalhetecnico.model.DetalhesTecnicosRequest;
import com.api.obra.domain.detalhetecnico.model.ObraDetalhesTecnicosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class DetalheTecnicoController {

    private final DetalheTecnicoService detalheTecnicoService;

    @PostMapping("/{obraId}/detalhes-tecnicos")
    public ResponseEntity<Void> criarDetalhesTecnicos(@Valid @RequestBody DetalhesTecnicosRequest detalhesTecnicosRequest,
                                                      @PathVariable(name = "obraId") Long obraId) {
        var detalhesTecnicos = detalheTecnicoService.criar(detalhesTecnicosRequest, obraId);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(detalhesTecnicos.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/detalhes-tecnicos")
    public ResponseEntity<Page<ObraDetalhesTecnicosResponse>> listarTodosDetalhesTecnicos(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy
    ) {
        return ResponseEntity.ok(detalheTecnicoService.listarTodosDetalhesTecnicos(page, linesPerPage, direction, orderBy));
    }

    @GetMapping("/detalhes-tecnicos/{id}")
    public ResponseEntity<ObraDetalhesTecnicosResponse> recuperarDetalhesTecnicos(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(detalheTecnicoService.recuperarDetalhesTecnicosPorId(id));
    }

    @PatchMapping("/detalhes-tecnicos/{id}")
    public ResponseEntity<ObraDetalhesTecnicosResponse> atualizarDetalhesTecnicos(@PathVariable(name = "id") Long id,
                                                                                  @RequestBody DetalhesTecnicosRequest detalhesTecnicosRequest) {
        return ResponseEntity.ok(detalheTecnicoService.atualizarDetalhesTecnicos(id, detalhesTecnicosRequest));
    }

    @DeleteMapping("/detalhes-tecnicos/{id}")
    public ResponseEntity<Void> apagarDetalhesTecnicos(@PathVariable(name = "id") Long id) {
        detalheTecnicoService.apagarDetalhesTecnicos(id);
        return ResponseEntity.noContent().build();
    }
}
