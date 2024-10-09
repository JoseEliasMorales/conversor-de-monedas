package com.aluracursos.conversorDeMonedas.modulos;

import com.aluracursos.conversorDeMonedas.records.MonedaConvertida;
import com.aluracursos.conversorDeMonedas.request.RequestApi;

public class Moneda {
    private double cambio;
    public double getCambio() {
        return cambio;
    }

    public Moneda(MonedaConvertida monedaConvertida){
        this.cambio = Double.parseDouble(monedaConvertida.conversion_result());
    }



}
