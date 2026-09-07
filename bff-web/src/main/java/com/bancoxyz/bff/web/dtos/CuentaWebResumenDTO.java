package com.bancoxyz.bff.web.dtos;

public class CuentaWebResumenDTO {

    private Long cuentaId;
    private ResumenMovimientosDTO resumen;

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public ResumenMovimientosDTO getResumen() {
        return resumen;
    }

    public void setResumen(ResumenMovimientosDTO resumen) {
        this.resumen = resumen;
    }
}