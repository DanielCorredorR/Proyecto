package com.mycompany.dao.patterns.command;

// Interfaz que define la acción
interface Comando { void ejecutar(); }

// Receptor: El objeto que sabe cómo realizar la acción
class Valvula {
    public void cerrar() { System.out.println("Válvula cerrada."); }
}

/**
 * Command: Encapsula la acción de cerrar la válvula.
 */
public class CerrarValvulaCmd implements Comando {
    private Valvula valvula;
    public CerrarValvulaCmd(Valvula v) { this.valvula = v; }

    @Override
    public void ejecutar() {
        valvula.cerrar();
    }
}
