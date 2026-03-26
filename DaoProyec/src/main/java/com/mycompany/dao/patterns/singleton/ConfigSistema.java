package com.mycompany.dao.patterns.singleton;

/**
 * Singleton: Garantiza una única instancia de la configuración del sistema.
 */
public class ConfigSistema {
    private static ConfigSistema instancia;
    private String nombreEmpresa = "Acueducto Municipal";

    private ConfigSistema() {} // Constructor privado

    public static ConfigSistema getInstancia() {
        if (instancia == null) {
            instancia = new ConfigSistema();
        }
        return instancia;
    }

    public String getNombreEmpresa() { return nombreEmpresa; }
}