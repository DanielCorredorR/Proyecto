package com.mycompany.dao.patterns.builder;

// Patrón Builder: Permite construir un recibo complejo paso a paso.
public class ReciboAgua {
    private final String id;
    private final double consumo;
    private final String usuario; // Opcional

    private ReciboAgua(Builder builder) {
        this.id = builder.id;
        this.consumo = builder.consumo;
        this.usuario = builder.usuario;
    }

    public static class Builder {
        private String id;
        private double consumo;
        private String usuario;

        public Builder(String id) { this.id = id; }
        public Builder conConsumo(double c) { this.consumo = c; return this; }
        public Builder paraUsuario(String u) { this.usuario = u; return this; }
        public ReciboAgua build() { return new ReciboAgua(this); }
    }
}