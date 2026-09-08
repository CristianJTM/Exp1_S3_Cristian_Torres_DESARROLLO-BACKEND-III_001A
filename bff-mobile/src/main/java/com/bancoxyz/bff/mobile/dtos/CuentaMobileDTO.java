package com.bancoxyz.bff.mobile.dtos;

import java.math.BigDecimal;
import java.util.List;

public class CuentaMobileDTO {

    private Long cuentaId;
    private BigDecimal saldo;
    private List<MovimientoMobileDTO> ultimosMovimientos;

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

    public List<MovimientoMobileDTO> getUltimosMovimientos() {
        return ultimosMovimientos;
    }

    public void setUltimosMovimientos(List<MovimientoMobileDTO> ultimosMovimientos) {
        this.ultimosMovimientos = ultimosMovimientos;
    }
}