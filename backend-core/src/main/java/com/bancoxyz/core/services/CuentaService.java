package com.bancoxyz.core.services;

import com.bancoxyz.core.dtos.CuentaDTO;
import com.bancoxyz.core.model.Cuenta;
import com.bancoxyz.core.exceptions.CuentaNoEncontradaException;
import com.bancoxyz.core.repositories.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaDTO buscarPorId(Long cuentaId) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() ->
                        new CuentaNoEncontradaException(
                                "No se encontró la cuenta con ID: " + cuentaId
                        )
                );

        return convertirADTO(cuenta);
    }

    private CuentaDTO convertirADTO(Cuenta cuenta) {

        CuentaDTO dto = new CuentaDTO();

        dto.setCuentaId(cuenta.getCuentaId());
        dto.setSaldo(cuenta.getSaldo());
        dto.setTipo(cuenta.getTipo());

        return dto;
    }
}