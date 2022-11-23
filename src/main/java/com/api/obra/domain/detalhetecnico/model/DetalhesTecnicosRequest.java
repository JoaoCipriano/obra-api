package com.api.obra.domain.detalhetecnico.model;

import com.api.obra.repository.detalhetecnico.model.ObraRisco;
import com.api.obra.repository.detalhetecnico.model.ObraTipo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetalhesTecnicosRequest {

    private ObraTipo tipo;
    private ObraRisco risco;
}
