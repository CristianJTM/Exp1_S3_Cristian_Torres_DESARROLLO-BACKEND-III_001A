package com.bancoxyz.bff.atm.dtos;

import java.math.BigDecimal;

public class CuentaAtmDTO {

    private Long cuentaId;
    private BigDecimal saldo;

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
}