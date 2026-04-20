package com.mycompany.dao.controller.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Muestra información detallada del archivo (Restricción #5).
 */
public class ExploradorUtil {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void mostrarPropiedadesArchivo(String rutaArchivo) {
        Path path = Paths.get(rutaArchivo);
        System.out.println("\n--- PROPIEDADES DEL ARCHIVO (Modo Explorador) ---");
        
        if (Files.exists(path)) {
            try {
                BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
                
                System.out.println("Nombre:           " + path.getFileName());
                System.out.println("Ruta absoluta:    " + path.toAbsolutePath());
                System.out.println("Tamaño:           " + attrs.size() + " bytes");
                System.out.println("Fecha creación:   " + attrs.creationTime().toInstant().atZone(ZoneId.systemDefault()).format(FORMATO));
                System.out.println("Última modif.:    " + attrs.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()).format(FORMATO));
                
            } catch (IOException e) {
                System.err.println("Error al leer metadatos: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo '" + rutaArchivo + "' aún no ha sido creado.");
        }
        System.out.println("-----------------------------------------------\n");
    }
}
