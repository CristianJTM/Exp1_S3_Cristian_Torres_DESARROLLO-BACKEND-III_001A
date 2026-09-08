package com.bancoxyz.bff.mobile.exceptions;

public class CuentaNoEncontradaException extends RuntimeException {

    public CuentaNoEncontradaException(Long cuentaId) {
        super("La cuenta " + cuentaId + " no fue encontrada");
    }
}
