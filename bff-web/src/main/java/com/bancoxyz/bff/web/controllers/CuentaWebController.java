package com.bancoxyz.bff.web.controllers;

import com.bancoxyz.bff.web.dtos.CuentaWebDetalleDTO;
import com.bancoxyz.bff.web.dtos.CuentaWebResumenDTO;
import com.bancoxyz.bff.web.services.BffWebService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/cuentas")
public class CuentaWebController {

    private final BffWebService bffWebService;

    public CuentaWebController(BffWebService bffWebService) {
        this.bffWebService = bffWebService;
    }

    @GetMapping("/{cuentaId}")
    public ResponseEntity<CuentaWebDetalleDTO> obtenerDetalleCuenta(
            @PathVariable Long cuentaId) {

        return ResponseEntity.ok(
                bffWebService.obtenerDetalleCuenta(cuentaId)
        );
    }

    @GetMapping("/{cuentaId}/resumen")
    public ResponseEntity<CuentaWebResumenDTO> obtenerResumenCuenta(
            @PathVariable Long cuentaId) {

        return ResponseEntity.ok(
                bffWebService.obtenerResumenCuenta(cuentaId)
        );
    }
}