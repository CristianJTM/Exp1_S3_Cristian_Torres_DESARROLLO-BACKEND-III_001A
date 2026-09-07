package com.bancoxyz.core.exceptions;

public class CuentaNoEncontradaException extends RuntimeException {

    public CuentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}