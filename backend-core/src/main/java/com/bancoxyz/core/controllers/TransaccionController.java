package com.bancoxyz.core.controllers;

import com.bancoxyz.core.dtos.TransaccionDTO;
import com.bancoxyz.core.services.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @GetMapping("/{id}")
    public TransaccionDTO buscarPorId(@PathVariable Long id) {

        return transaccionService.buscarPorId(id);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<TransaccionDTO> buscarPorCuenta(@PathVariable Long cuentaId) {

        return transaccionService.buscarPorCuenta(cuentaId);
    }
}
