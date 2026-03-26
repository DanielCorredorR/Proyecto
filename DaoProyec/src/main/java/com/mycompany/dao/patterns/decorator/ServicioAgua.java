package com.mycompany.dao.patterns.decorator;

/**
 * Decorator: Añade responsabilidades (como telemetría) dinámicamente.
 */
interface ServicioAgua { String getDescripcion(); }

class ServicioBasico implements ServicioAgua {
    public String getDescripcion() { return "Servicio de Agua Básico"; }
}

abstract class ServicioDecorator implements ServicioAgua {
    protected ServicioAgua servicio;
    public ServicioDecorator(ServicioAgua s) { this.servicio = s; }
}

class SensorInteligente extends ServicioDecorator {
    public SensorInteligente(ServicioAgua s) { super(s); }
    public String getDescripcion() { return servicio.getDescripcion() + " + Sensor de Fugas"; }
}
