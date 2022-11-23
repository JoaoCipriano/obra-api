package com.api.obra.repository.detalhetecnico.model;

import com.api.obra.repository.obra.model.ObraEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "obra_detalhes_tecnicos")
public class ObraDetalheTecnicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id")
    private ObraEntity obra;

    @Enumerated(EnumType.STRING)
    private ObraTipo tipo;

    @Enumerated(EnumType.STRING)
    private ObraRisco risco;
}
