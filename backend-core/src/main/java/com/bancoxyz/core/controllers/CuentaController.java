package com.bancoxyz.core.controllers;

import com.bancoxyz.core.dtos.CuentaDTO;
import com.bancoxyz.core.dtos.RetiroDTO;
import com.bancoxyz.core.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping("/{cuentaId}")
    public CuentaDTO buscarPorId(@PathVariable Long cuentaId) {

        return cuentaService.buscarPorId(cuentaId);
    }

    @PostMapping("/{cuentaId}/retiros")
    public CuentaDTO realizarRetiro(
            @PathVariable Long cuentaId,
            @RequestBody RetiroDTO retiro) {

        return cuentaService.realizarRetiro(
                cuentaId,
                retiro.getMonto()
        );
    }
}