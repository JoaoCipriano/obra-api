package com.api.obra.domain.obra.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
public class ObraRequest {

    @NotEmpty @NotBlank @Size(max = 45)
    private String nome;

    @Min(value = 1500, message = "Ano mínimo é 1500")
    @Max(value = 2500, message = "Ano máximo é 2500")
    private Integer anoConstrucao;

    @NotEmpty @NotBlank @Size(max = 45)
    private String coordenacao;

    @NotEmpty @NotBlank @Size(max = 45)
    private String gerencia;

    @NotEmpty @NotBlank @Size(max = 45)
    private String diretoria;

    @NotEmpty @NotBlank @Size(max = 45)
    private String outorga;

    @NotEmpty @NotBlank @Size(max = 45)
    private String titularidade;
}
