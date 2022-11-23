package com.api.obra.repository.obra.model;

import com.api.obra.repository.detalhetecnico.model.ObraDetalheTecnicoEntity;
import com.api.obra.repository.inspecao.model.ObraInspecaoEntity;
import com.api.obra.repository.localizacao.model.ObraLocalizacaoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "obra")
public class ObraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 45)
    private String nome;

    @Column(name = "ano_construcao")
    private Integer anoConstrucao;

    @Column(length = 45)
    private String coordenacao;

    @Column(length = 45)
    private String gerencia;

    @Column(length = 45)
    private String diretoria;

    @Column(length = 45)
    private String outorga;

    @Column(length = 45)
    private String titularidade;

    @OneToOne(mappedBy = "obra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ObraLocalizacaoEntity localizacao;

    @OneToOne(mappedBy = "obra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ObraDetalheTecnicoEntity detalhesTecnicos;

    @OneToOne(mappedBy = "obra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ObraInspecaoEntity obraInspecao;

    public ObraEntity(Long obraId) {
        this.id = obraId;
    }
}
