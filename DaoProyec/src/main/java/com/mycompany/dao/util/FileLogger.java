package com.mycompany.dao.util;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * CAPA: UTILIDADES (Cross-cutting)
 * RESTRICCIÓN #4: Generación de logs con marca de tiempo precisa.
 * El nombre del archivo usa el formato 'yyyyMMddHHmmssSSS' para evitar colisiones.
 */
public class FileLogger {
    // Formato numérico puro: AñoMesDíaHoraMinutoSegundoMilisegundo (Ej: 20260326203015125)
    private static final DateTimeFormatter FORMATO_NOMBRE_ARCHIVO = 
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    
    private static final DateTimeFormatter FORMATO_LOG_INTERNO = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void logException(String detalle, Exception e, Object objetoDatos) {
        try {
            // Generar nombre: log20260326203015125.log
            String timestamp = LocalDateTime.now().format(FORMATO_NOMBRE_ARCHIVO);
            String nombreArchivo = "log" + timestamp + ".log";
            Path rutaLog = Paths.get(nombreArchivo);

            // Crea el archivo único para este milisegundo exacto
            Files.createFile(rutaLog);

            String logEntry = String.format(
                "[%s] ERROR: %s | TIPO: %s | MSG: %s | DATOS: %s%n",
                LocalDateTime.now().format(FORMATO_LOG_INTERNO),
                detalle,
                e.getClass().getSimpleName(),
                e.getMessage(),
                (objetoDatos != null) ? objetoDatos.toString() : "N/A"
            );

            Files.writeString(rutaLog, logEntry, StandardOpenOption.APPEND);
            
        } catch (IOException ioEx) {
            System.err.println("CRÍTICO: Fallo en el log con timestamp numérico.");
        }
    }
}
