package com.mycompany.dao.dao;

import com.mycompany.dao.modelo.ContadorAgua;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Interface que define qué operaciones podemos hacer con los datos.
 * Principio SOLID: DIP (Inversión de Dependencias).
 */
public interface ContadorDAO {
    void guardar(ContadorAgua contador) throws IOException;
    Optional<ContadorAgua> buscarPorId(String id) throws IOException;
    List<ContadorAgua> listarTodos() throws IOException;
    void eliminar(String id) throws IOException;
    
    // Método de ayuda para saber si ya existe
    default boolean existe(String id) throws IOException {
        return buscarPorId(id).isPresent();
    }
}