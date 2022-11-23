package com.api.obra.domain.localizacao.model;

import com.api.obra.domain.obra.model.ObraResponse;
import com.api.obra.repository.localizacao.model.ObraLocalizacaoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObraLocalizacaoResponse {

    private Long id;
    private String cidade;
    private String estado;
    private String latidude;
    private String longitude;
    private ObraResponse obra;

    public ObraLocalizacaoResponse(ObraLocalizacaoEntity obraLocalizacaoEntity) {
        if (nonNull(obraLocalizacaoEntity)) {
            this.id = obraLocalizacaoEntity.getId();
            this.cidade = obraLocalizacaoEntity.getCidade();
            this.estado = obraLocalizacaoEntity.getEstado();
            this.latidude = obraLocalizacaoEntity.getLatidude();
            this.longitude = obraLocalizacaoEntity.getLongitude();
            this.obra = ObraResponse.builder()
                    .id(obraLocalizacaoEntity.getObra().getId())
                    .nome(obraLocalizacaoEntity.getObra().getNome())
                    .build();
        }
    }
}
