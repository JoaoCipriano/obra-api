package com.api.obra.repository.inspecao.model;

import com.api.obra.repository.obra.model.ObraEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "obra_inspecao")
public class ObraInspecaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "obra_id")
    private ObraEntity obra;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private InspecaoFrequencia frequencia;

    private Integer mes;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private InspecaoStatus status;

    private Integer prioridade;

    @OneToMany(mappedBy = "obraInspecao", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InspecaoEntity> inspecoes;
}
