package com.smarthome.bridge;

/**
 * Implementación concreta de Dispositivo - Aire Acondicionado
 */
public class AireAcondicionado implements Dispositivo {
    private boolean encendido = false;
    private int temperatura = 22;
    private String modo = "auto"; // auto, frio, calor
    
    @Override
    public void encender() {
        encendido = true;
    }
    
    @Override
    public void apagar() {
        encendido = false;
    }
    
    @Override
    public String getEstado() {
        if (!encendido) {
            return "Apagado";
        }
        return String.format("Encendido (%d°C, Modo: %s)", temperatura, modo);
    }
    
    @Override
    public String getTipo() {
        return "Aire Acondicionado";
    }
    
    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equalsIgnoreCase("encender")) {
            encender();
        } else if (accion.equalsIgnoreCase("apagar")) {
            apagar();
        } else if (accion.startsWith("temperatura")) {
            try {
                int temp = Integer.parseInt(accion.split(" ")[1]);
                if (temp >= 16 && temp <= 30) {
                    temperatura = temp;
                }
            } catch (Exception e) {
                // Ignorar formato inválido
            }
        } else if (accion.startsWith("modo")) {
            String nuevoModo = accion.substring(5).trim();
            if (nuevoModo.equals("auto") || nuevoModo.equals("frio") || nuevoModo.equals("calor")) {
                modo = nuevoModo;
            }
        }
    }
    
    public int getTemperatura() {
        return temperatura;
    }
    
    public String getModo() {
        return modo;
    }
}
