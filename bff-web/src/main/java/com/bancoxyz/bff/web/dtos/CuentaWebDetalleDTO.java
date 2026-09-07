package com.bancoxyz.bff.web.dtos;

import java.util.List;

public class CuentaWebDetalleDTO {

    private Long cuentaId;
    private CuentaWebResumenDTO resumen;
    private List<MovimientoWebDTO> movimientos;

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public CuentaWebResumenDTO getResumen() {
        return resumen;
    }

    public void setResumen(CuentaWebResumenDTO resumen) {
        this.resumen = resumen;
    }

    public List<MovimientoWebDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoWebDTO> movimientos) {
        this.movimientos = movimientos;
    }
}