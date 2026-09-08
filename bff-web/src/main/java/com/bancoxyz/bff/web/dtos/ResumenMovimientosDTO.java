package com.bancoxyz.bff.web.dtos;

import java.math.BigDecimal;

public class ResumenMovimientosDTO {

    private Integer totalMovimientos;
    private BigDecimal totalDepositos;
    private BigDecimal totalRetiros;
    private BigDecimal saldo;

    public Integer getTotalMovimientos() {
        return totalMovimientos;
    }

    public void setTotalMovimientos(Integer totalMovimientos) {
        this.totalMovimientos = totalMovimientos;
    }

    public BigDecimal getTotalDepositos() {
        return totalDepositos;
    }

    public void setTotalDepositos(BigDecimal totalDepositos) {
        this.totalDepositos = totalDepositos;
    }

    public BigDecimal getTotalRetiros() {
        return totalRetiros;
    }

    public void setTotalRetiros(BigDecimal totalRetiros) {
        this.totalRetiros = totalRetiros;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}