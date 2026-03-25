package com.mycompany.dao.modelo;

/**
 * Entidad ContadorAgua - Representa el medidor físico.
 * Aplicando Restricción #1: El ID es inmutable.
 */
public class ContadorAgua {
    // El modificador 'final' asegura que el ID no cambie después de creado (R1)
    private final String idContador; 
    private String ubicacion;
    private double lecturaActual;
    private int actualizaciones; // Contador para la Restricción #3

    // Constructor completo
    public ContadorAgua(String idContador, String ubicacion, double lecturaActual, int actualizaciones) {
        this.idContador = idContador;
        this.ubicacion = ubicacion;
        this.lecturaActual = lecturaActual;
        this.actualizaciones = actualizaciones;
    }

    // GETTERS (Basados en tu diagrama de clases)
    public String getIdContador() { return idContador; }
    public String getUbicacion() { return ubicacion; }
    public double getLecturaActual() { return lecturaActual; }
    public int getActualizaciones() { return actualizaciones; }

    // SETTERS (No creamos Setter para idContador para cumplir R1)
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setLecturaActual(double lecturaActual) { this.lecturaActual = lecturaActual; }
    
    // Método para incrementar el contador de cambios (Lógica para R3)
    public void incrementarActualizacion() {
        this.actualizaciones++;
    }

    @Override
    public String toString() {
        return idContador + "|" + ubicacion + "|" + lecturaActual + "|" + actualizaciones;
    }
}