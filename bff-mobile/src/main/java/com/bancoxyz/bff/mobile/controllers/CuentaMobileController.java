package com.bancoxyz.bff.mobile.controllers;

import com.bancoxyz.bff.mobile.dtos.CuentaMobileDTO;
import com.bancoxyz.bff.mobile.services.BffMobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/cuentas")
public class CuentaMobileController {

    private final BffMobileService bffMobileService;

    public CuentaMobileController(BffMobileService bffMobileService) {
        this.bffMobileService = bffMobileService;
    }

    @GetMapping("/{cuentaId}")
    public ResponseEntity<CuentaMobileDTO> obtenerCuenta(
            @PathVariable Long cuentaId) {

        return ResponseEntity.ok(
                bffMobileService.obtenerCuenta(cuentaId)
        );
    }
}