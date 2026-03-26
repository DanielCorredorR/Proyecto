package com.mycompany.dao.patterns.factory;
import com.mycompany.dao.modelo.ContadorAgua;

// Factory Method: Define una interfaz para crear objetos.
public abstract class ContadorFactory {
    public abstract ContadorAgua crearContador(String id);
}

class FactoryResidencial extends ContadorFactory {
    @Override
    public ContadorAgua crearContador(String id) {
        return new ContadorAgua(id, "Residencial", 0.0, 0);
    }
}