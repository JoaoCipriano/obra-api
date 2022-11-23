package com.api.obra.repository.obra;

import com.api.obra.repository.obra.model.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<ObraEntity, Long> {
}
