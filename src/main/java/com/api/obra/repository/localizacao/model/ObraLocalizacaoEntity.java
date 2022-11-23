package com.api.obra.repository.localizacao.model;

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
@Entity(name = "obra_localizacao")
public class ObraLocalizacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 45)
    private String cidade;

    @Column(length = 45)
    private String estado;

    @Column(length = 45)
    private String latidude;

    @Column(length = 45)
    private String longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id")
    private ObraEntity obra;
}
