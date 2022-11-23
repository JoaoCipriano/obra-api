package com.api.obra.domain.detalhetecnico.model;

import com.api.obra.domain.obra.model.ObraResponse;
import com.api.obra.repository.detalhetecnico.model.ObraDetalheTecnicoEntity;
import com.api.obra.repository.detalhetecnico.model.ObraRisco;
import com.api.obra.repository.detalhetecnico.model.ObraTipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObraDetalhesTecnicosResponse {

    private Long id;
    private ObraTipo tipo;
    private ObraRisco risco;
    private ObraResponse obra;

    public ObraDetalhesTecnicosResponse(ObraDetalheTecnicoEntity obraDetalheTecnicoEntity) {
        if (nonNull(obraDetalheTecnicoEntity)) {
            this.id = obraDetalheTecnicoEntity.getId();
            this.tipo = obraDetalheTecnicoEntity.getTipo();
            this.risco = obraDetalheTecnicoEntity.getRisco();
            this.obra = ObraResponse.builder()
                    .id(obraDetalheTecnicoEntity.getObra().getId())
                    .nome(obraDetalheTecnicoEntity.getObra().getNome())
                    .build();
        }
    }
}
