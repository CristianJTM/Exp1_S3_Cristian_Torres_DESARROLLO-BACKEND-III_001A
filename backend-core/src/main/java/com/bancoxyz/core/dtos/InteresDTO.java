package com.bancoxyz.core.dtos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class InteresDTO {

    private Long cuentaId;

    private String nombre;

    private BigDecimal saldo;

    private Integer edad;

    private String tipo;

    // GETTERS Y SETTERS


    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}