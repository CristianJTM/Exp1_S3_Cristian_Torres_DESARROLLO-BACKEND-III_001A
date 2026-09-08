package com.bancoxyz.bff.atm.dtos;

import java.math.BigDecimal;

public class RetiroAtmRequestDTO {

    private BigDecimal monto;

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}