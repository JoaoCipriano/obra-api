package com.api.obra.domain.detalhetecnico.model;

import com.api.obra.repository.detalhetecnico.model.ObraDetalheTecnicoEntity;
import com.api.obra.repository.detalhetecnico.model.ObraRisco;
import com.api.obra.repository.detalhetecnico.model.ObraTipo;
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
public class DetalhesTecnicosResponse {

    @JsonIgnore
    private Long id;
    private ObraTipo tipo;
    private ObraRisco risco;

    public DetalhesTecnicosResponse(ObraDetalheTecnicoEntity obraDetalheTecnicoEntity) {
        if (nonNull(obraDetalheTecnicoEntity)) {
            this.id = obraDetalheTecnicoEntity.getId();
            this.tipo = obraDetalheTecnicoEntity.getTipo();
            this.risco = obraDetalheTecnicoEntity.getRisco();
        }
    }
}
