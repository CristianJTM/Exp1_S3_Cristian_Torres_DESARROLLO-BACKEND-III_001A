package com.bancoxyz.core.repositories;

import com.bancoxyz.core.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByCuentaId(Long cuentaId);

    @Query("SELECT MAX(t.id) FROM Transaccion t")
    Long obtenerUltimoId();
}