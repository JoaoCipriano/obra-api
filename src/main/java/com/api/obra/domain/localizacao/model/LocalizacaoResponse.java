package com.api.obra.domain.localizacao.model;

import com.api.obra.repository.localizacao.model.ObraLocalizacaoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizacaoResponse {

    @JsonIgnore
    private Long id;
    private String cidade;
    private String estado;
    private String latidude;
    private String longitude;

    public LocalizacaoResponse(ObraLocalizacaoEntity obraLocalizacaoEntity) {
        if (nonNull(obraLocalizacaoEntity)) {
            this.id = obraLocalizacaoEntity.getId();
            this.cidade = obraLocalizacaoEntity.getCidade();
            this.estado = obraLocalizacaoEntity.getEstado();
            this.latidude = obraLocalizacaoEntity.getLatidude();
            this.longitude = obraLocalizacaoEntity.getLongitude();
        }
    }
}
