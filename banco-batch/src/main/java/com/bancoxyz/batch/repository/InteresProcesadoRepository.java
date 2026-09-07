package com.bancoxyz.batch.repository;

import com.bancoxyz.batch.model.InteresProcesado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresProcesadoRepository extends JpaRepository<InteresProcesado, Long> {

}
