package com.bancoxyz.core.controllers;

import com.bancoxyz.core.dtos.InteresDTO;
import com.bancoxyz.core.services.InteresService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/intereses")
@RequiredArgsConstructor
public class InteresController {

    private final InteresService interesService;

    @GetMapping("/cuenta/{cuentaId}")
    public InteresDTO buscarPorCuenta(@PathVariable Long cuentaId) {

        return interesService.buscarPorCuenta(cuentaId);
    }
}