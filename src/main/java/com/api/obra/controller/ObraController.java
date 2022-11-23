package com.api.obra.controller;

import com.api.obra.domain.obra.ObraService;
import com.api.obra.domain.obra.model.ObraRequest;
import com.api.obra.domain.obra.model.ObraResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService obraService;

    @PostMapping
    public ResponseEntity<Void> criarObra(@Valid @RequestBody ObraRequest obraRequest) {
        var obra = obraService.criar(obraRequest);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obra.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<ObraResponse>> listarTodasObras(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value="orderBy", defaultValue="anoConstrucao") String orderBy
    ) {
        return ResponseEntity.ok(obraService.listarTodasObras(page, linesPerPage, direction, orderBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraResponse> recuperarObra(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(obraService.recuperarObraPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ObraResponse> atualizarObra(@PathVariable(name = "id") Long id, @RequestBody ObraRequest obraRequest) {
        return ResponseEntity.ok(obraService.atualizarObraPorId(id, obraRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarObra(@PathVariable(name = "id") Long id) {
        obraService.apagarObraPorId(id);
        return ResponseEntity.noContent().build();
    }
}
