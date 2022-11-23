package com.api.obra.domain.inspecao.model;

import com.api.obra.repository.inspecao.model.InspecaoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InspecaoResponse {

    private LocalDateTime data;
    private String observacoes;

    public InspecaoResponse(InspecaoEntity inspecaoEntity) {
        if (nonNull(inspecaoEntity)) {
            this.data = inspecaoEntity.getData();
            this.observacoes = inspecaoEntity.getObservacoes();
        }
    }
}
