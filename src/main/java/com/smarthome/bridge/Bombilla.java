package com.smarthome.bridge;

/**
 * Implementación concreta de Dispositivo - Bombilla
 */
public class Bombilla implements Dispositivo {
    private boolean encendida = false;
    private int intensidad = 0;
    
    @Override
    public void encender() {
        encendida = true;
        intensidad = 100;
    }
    
    @Override
    public void apagar() {
        encendida = false;
        intensidad = 0;
    }
    
    @Override
    public String getEstado() {
        return encendida ? "Encendida (Intensidad: " + intensidad + "%)" : "Apagada";
    }
    
    @Override
    public String getTipo() {
        return "Bombilla";
    }
    
    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equalsIgnoreCase("encender")) {
            encender();
        } else if (accion.equalsIgnoreCase("apagar")) {
            apagar();
        } else if (accion.startsWith("intensidad")) {
            try {
                int valor = Integer.parseInt(accion.split(" ")[1]);
                if (valor >= 0 && valor <= 100) {
                    intensidad = valor;
                    encendida = valor > 0;
                }
            } catch (Exception e) {
                // Ignorar formato inválido
            }
        }
    }
    
    public int getIntensidad() {
        return intensidad;
    }
}
