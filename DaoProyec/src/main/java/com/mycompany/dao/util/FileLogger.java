package com.mycompany.dao.util;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para el registro de errores en archivo.
 * Aplicando Restricción #4: Fecha, hora, detalle y objeto del error.
 */
public class FileLogger {
    private static final Path LOG_FILE = Paths.get("error_sistema.log");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logException(String detalle, Exception e, Object objetoDatos) {
        try {
            // Si el archivo no existe, lo crea
            if (!Files.exists(LOG_FILE)) {
                Files.createFile(LOG_FILE);
            }

            String logEntry = String.format(
                "[%s] ERROR: %s | TIPO: %s | MSG: %s | DATOS: %s%n",
                LocalDateTime.now().format(FORMATO_FECHA),
                detalle,
                e.getClass().getSimpleName(),
                e.getMessage(),
                (objetoDatos != null) ? objetoDatos.toString() : "N/A"
            );

            // Escribe al final del archivo sin borrar lo anterior (APPEND)
            Files.writeString(LOG_FILE, logEntry, StandardOpenOption.APPEND);
            
        } catch (IOException ioEx) {
            System.err.println("CRÍTICO: No se pudo escribir en el log de errores.");
            ioEx.printStackTrace();
        }
    }
}