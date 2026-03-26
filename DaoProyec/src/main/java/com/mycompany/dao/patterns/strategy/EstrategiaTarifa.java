package com.mycompany.dao.patterns.strategy;

/**
 * Strategy: Intercambia el algoritmo de cálculo de tarifa según el estrato.
 */
interface EstrategiaTarifa { double calcular(double consumo); }

class TarifaSocial implements EstrategiaTarifa {
    public double calcular(double consumo) { return consumo * 100; }
}

class TarifaComercial implements EstrategiaTarifa {
    public double calcular(double consumo) { return consumo * 500; }
}

class CalculadoraCobro {
    private EstrategiaTarifa estrategia;
    public void setEstrategia(EstrategiaTarifa e) { this.estrategia = e; }
    public double ejecutarCobro(double m3) { return estrategia.calcular(m3); }
}
