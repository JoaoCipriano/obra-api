package com.api.obra.domain.inspecao.model;

import com.api.obra.repository.inspecao.model.InspecaoFrequencia;
import com.api.obra.repository.inspecao.model.InspecaoStatus;
import com.api.obra.repository.inspecao.model.ObraInspecaoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObraInspecaoResponse {

    private InspecaoFrequencia frequencia;
    private Integer mes;
    private InspecaoStatus status;
    private Integer prioridade;
    private List<InspecaoResponse> inspecoes;

    public ObraInspecaoResponse(ObraInspecaoEntity obraInspecaoEntity) {
        if (nonNull(obraInspecaoEntity)) {
            this.frequencia = obraInspecaoEntity.getFrequencia();
            this.mes = obraInspecaoEntity.getMes();
            this.status = obraInspecaoEntity.getStatus();
            this.prioridade = obraInspecaoEntity.getPrioridade();
            this.inspecoes = CollectionUtils.isEmpty(obraInspecaoEntity.getInspecoes()) ?
                    emptyList() : obraInspecaoEntity.getInspecoes().stream().map(InspecaoResponse::new).collect(Collectors.toList());
        }
    }
}
