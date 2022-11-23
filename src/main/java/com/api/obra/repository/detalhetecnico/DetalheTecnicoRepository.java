package com.api.obra.repository.detalhetecnico;

import com.api.obra.repository.detalhetecnico.model.ObraDetalheTecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalheTecnicoRepository extends JpaRepository<ObraDetalheTecnicoEntity, Long> {
}
