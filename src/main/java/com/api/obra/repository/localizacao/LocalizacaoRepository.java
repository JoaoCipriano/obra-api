package com.api.obra.repository.localizacao;

import com.api.obra.repository.localizacao.model.ObraLocalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<ObraLocalizacaoEntity, Long> {
}
