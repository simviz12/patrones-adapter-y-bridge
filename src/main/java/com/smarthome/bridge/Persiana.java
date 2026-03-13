package com.smarthome.bridge;

/**
 * Implementación concreta de Dispositivo - Persiana
 */
public class Persiana implements Dispositivo {
    private int posicion = 0; // 0 = cerrada, 100 = completamente abierta
    
    @Override
    public void encender() {
        // Para persiana, encender significa abrirla completamente
        posicion = 100;
    }
    
    @Override
    public void apagar() {
        // Para persiana, apagar significa cerrarla completamente
        posicion = 0;
    }
    
    @Override
    public String getEstado() {
        if (posicion == 0) {
            return "Cerrada";
        } else if (posicion == 100) {
            return "Completamente abierta";
        } else {
            return "Abierta al " + posicion + "%";
        }
    }
    
    @Override
    public String getTipo() {
        return "Persiana";
    }
    
    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equalsIgnoreCase("encender")) {
            encender();
        } else if (accion.equalsIgnoreCase("apagar")) {
            apagar();
        } else if (accion.startsWith("posicion")) {
            try {
                int pos = Integer.parseInt(accion.split(" ")[1]);
                if (pos >= 0 && pos <= 100) {
                    posicion = pos;
                }
            } catch (Exception e) {
                // Ignorar formato inválido
            }
        } else if (accion.equals("subir")) {
            posicion = Math.min(100, posicion + 25);
        } else if (accion.equals("bajar")) {
            posicion = Math.max(0, posicion - 25);
        }
    }
    
    public int getPosicion() {
        return posicion;
    }
}
