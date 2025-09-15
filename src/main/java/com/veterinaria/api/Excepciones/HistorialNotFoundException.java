package com.veterinaria.api.Excepciones;

public class HistorialNotFoundException extends RuntimeException{
    public HistorialNotFoundException(String mensaje) {
        super(mensaje);
    }
}
