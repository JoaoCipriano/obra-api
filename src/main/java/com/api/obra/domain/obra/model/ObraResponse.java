package com.api.obra.domain.obra.model;

import com.api.obra.domain.detalhetecnico.model.DetalhesTecnicosResponse;
import com.api.obra.domain.inspecao.model.ObraInspecaoResponse;
import com.api.obra.domain.localizacao.model.LocalizacaoResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ObraResponse {

    private Long id;
    private String nome;
    private Integer anoConstrucao;
    private String coordenacao;
    private String gerencia;
    private String diretoria;
    private String outorga;
    private String titularidade;
    private LocalizacaoResponse localizacao;
    private DetalhesTecnicosResponse detalhesTecnicos;
    private ObraInspecaoResponse obraInspecao;
}
