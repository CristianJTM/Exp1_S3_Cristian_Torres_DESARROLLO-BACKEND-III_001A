package com.bancoxyz.batch.repository;
import com.bancoxyz.batch.model.TransaccionProcesada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransaccionProcesadaRepository
        extends JpaRepository<TransaccionProcesada, Long> {

    List<TransaccionProcesada> findByFecha(LocalDate fecha);

    List<TransaccionProcesada> findByTipo(String tipo);


    List<TransaccionProcesada> findByFechaBetween(
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

    @Query("SELECT DISTINCT t.fecha FROM TransaccionProcesada t ORDER BY t.fecha")
    List<LocalDate> findFechasProcesadas();
}
