package com.bancoxyz.core.repositories;

import com.bancoxyz.core.entities.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Long> {

    List<TransaccionEntity> findByCuentaId(Long cuentaId);
}