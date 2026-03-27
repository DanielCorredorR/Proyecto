package com.mycompany.dao.dao;

import com.mycompany.dao.modelo.ContadorAgua;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
/**
 * CAPA: ACCESO A DATOS (DAO - MVC)
 * Implementación de persistencia en archivo plano (.txt).
 * Sigue el principio SOLID de Inversión de Dependencias (DIP).
 */
public class ContadorDAOImpl implements ContadorDAO {
    private final Path archivo = Paths.get("datos_contadores.txt");

    public ContadorDAOImpl() throws IOException {
        // Si el archivo no existe, lo crea al iniciar
        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
        }
    }

    @Override
    public void guardar(ContadorAgua contador) throws IOException {
        List<ContadorAgua> todos = listarTodos();
        // Si el contador ya existe (por ID), lo quitamos para poner la versión nueva
        todos.removeIf(c -> c.getIdContador().equals(contador.getIdContador()));
        todos.add(contador);
        
        // Convertimos los objetos a líneas de texto
        List<String> lineas = todos.stream()
                .map(ContadorAgua::toString)
                .collect(Collectors.toList());
        
        Files.write(archivo, lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public List<ContadorAgua> listarTodos() throws IOException {
        try (var lines = Files.lines(archivo)) {
            return lines.filter(l -> !l.isBlank())
                    .map(this::mapearTextoAObjeto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Optional<ContadorAgua> buscarPorId(String id) throws IOException {
        return listarTodos().stream()
                .filter(c -> c.getIdContador().equals(id))
                .findFirst();
    }

    @Override
    public void eliminar(String id) throws IOException {
        List<ContadorAgua> todos = listarTodos();
        todos.removeIf(c -> c.getIdContador().equals(id));
        
        List<String> lineas = todos.stream().map(ContadorAgua::toString).toList();
        Files.write(archivo, lineas, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Método privado para convertir una línea del TXT en un Objeto Java
    private ContadorAgua mapearTextoAObjeto(String linea) {
        String[] datos = linea.split("\\|");
        return new ContadorAgua(datos[0], datos[1], Double.parseDouble(datos[2]), Integer.parseInt(datos[3]));
    }
}