package com.bancoxyz.core.dtos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class CuentaDTO {

    private Long cuentaId;

    private BigDecimal saldo;

    private String tipo;

    // GETTERS Y SETTERS

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}