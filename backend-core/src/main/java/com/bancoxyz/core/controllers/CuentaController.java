package com.bancoxyz.core.controllers;

import com.bancoxyz.core.dtos.CuentaDTO;
import com.bancoxyz.core.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping("/{cuentaId}")
    public CuentaDTO buscarPorId(@PathVariable Long cuentaId) {

        return cuentaService.buscarPorId(cuentaId);
    }
}