package com.mycompany.dao.patterns.facade;

// Subsistemas que el Facade ocultará al cliente
class Facturacion {
    public void calcularCosto() { System.out.println("Costo calculado."); }
}
class Auditoria {
    public void registrarOperacion() { System.out.println("Registro auditado."); }
}

/**
 * Facade: Simplifica el acceso a la lógica de facturación y auditoría.
 */
public class SistemaAguaFacade {
    private Facturacion facturacion = new Facturacion();
    private Auditoria auditoria = new Auditoria();

    public void procesarFacturaMes() {
        System.out.println("--- Iniciando proceso Facade ---");
        facturacion.calcularCosto();
        auditoria.registrarOperacion();
        System.out.println("--- Proceso finalizado ---");
    }
}
