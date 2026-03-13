package com.smarthome.bridge;

/**
 * Interfaz del Implementador - Parte del patrón Bridge
 * Define las operaciones básicas que todos los dispositivos deben tener
 */
public interface Dispositivo {
    void encender();
    void apagar();
    String getEstado();
    String getTipo();
    void ejecutarAccion(String accion);
}
