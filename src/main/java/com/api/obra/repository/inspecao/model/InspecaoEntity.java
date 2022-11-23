package com.api.obra.repository.inspecao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "inspecao")
public class InspecaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obra_inspecao_id")
    private ObraInspecaoEntity obraInspecao;

    private LocalDateTime data;

    @Column(columnDefinition="TEXT")
    private String observacoes;
}
