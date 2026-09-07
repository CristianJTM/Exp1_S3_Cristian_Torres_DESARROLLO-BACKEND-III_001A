package com.bancoxyz.core.repositories;

import com.bancoxyz.core.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}