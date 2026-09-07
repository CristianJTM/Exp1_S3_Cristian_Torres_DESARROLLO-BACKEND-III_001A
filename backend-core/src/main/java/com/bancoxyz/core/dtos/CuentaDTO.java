package com.bancoxyz.core.dtos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class CuentaDTO {

    private Long cuentaId;

    // GETTERS Y SETTERS

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }
}