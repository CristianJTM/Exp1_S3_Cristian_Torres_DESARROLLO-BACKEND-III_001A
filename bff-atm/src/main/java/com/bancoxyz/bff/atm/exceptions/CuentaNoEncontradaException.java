package com.bancoxyz.bff.atm.exceptions;

public class CuentaNoEncontradaException extends RuntimeException {

    public CuentaNoEncontradaException(Long cuentaId) {
        super("La cuenta " + cuentaId + " no fue encontrada");
    }
}
