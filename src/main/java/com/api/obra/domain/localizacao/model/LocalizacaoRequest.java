package com.api.obra.domain.localizacao.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
public class LocalizacaoRequest {

    @NotEmpty @NotBlank @Size(max = 45)
    private String cidade;

    @NotEmpty @NotBlank @Size(max = 45)
    private String estado;

    @NotEmpty @NotBlank @Size(max = 45)
    private String latidude;

    @NotEmpty @NotBlank @Size(max = 45)
    private String longitude;
}
