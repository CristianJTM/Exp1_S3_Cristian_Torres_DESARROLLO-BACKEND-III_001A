package com.bancoxyz.bff.web.dtos.core;

import java.math.BigDecimal;

public class CuentaCoreDTO {

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