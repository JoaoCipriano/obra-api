package com.api.obra.repository.inspecao;

import com.api.obra.repository.inspecao.model.InspecaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspecaoRepository extends JpaRepository<InspecaoEntity, Long> {
}
