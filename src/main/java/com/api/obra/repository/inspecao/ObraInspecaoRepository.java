package com.api.obra.repository.inspecao;

import com.api.obra.repository.inspecao.model.ObraInspecaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraInspecaoRepository extends JpaRepository<ObraInspecaoEntity, Long> {
}
