package com.bancoxyz.bff.atm.dtos;

import java.math.BigDecimal;

public class RetiroAtmResponseDTO {

    private Long cuentaId;
    private BigDecimal montoRetirado;
    private BigDecimal saldo;
    private String estado;

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public BigDecimal getMontoRetirado() {
        return montoRetirado;
    }

    public void setMontoRetirado(BigDecimal montoRetirado) {
        this.montoRetirado = montoRetirado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}