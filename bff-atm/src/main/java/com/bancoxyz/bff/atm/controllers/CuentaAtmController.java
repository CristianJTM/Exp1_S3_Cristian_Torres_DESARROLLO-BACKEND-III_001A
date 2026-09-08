package com.bancoxyz.bff.atm.controllers;

import com.bancoxyz.bff.atm.dtos.CuentaAtmDTO;
import com.bancoxyz.bff.atm.dtos.RetiroAtmRequestDTO;
import com.bancoxyz.bff.atm.dtos.RetiroAtmResponseDTO;
import com.bancoxyz.bff.atm.services.BffAtmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm/cuentas")
@RequiredArgsConstructor
public class CuentaAtmController {

    private final BffAtmService bffAtmService;

    @GetMapping("/{cuentaId}")
    public CuentaAtmDTO consultarSaldo(
            @PathVariable Long cuentaId) {

        return bffAtmService.consultarSaldo(cuentaId);
    }

    @PostMapping("/{cuentaId}/retiros")
    public RetiroAtmResponseDTO realizarRetiro(
            @PathVariable Long cuentaId,
            @RequestBody RetiroAtmRequestDTO retiro) {

        return bffAtmService.realizarRetiro(
                cuentaId,
                retiro
        );
    }
}