package com.smarthome.adapter;

import com.smarthome.bridge.Dispositivo;

/**
 * Adaptador - Parte del patrón Adapter
 * Convierte la interfaz de Philips Hue a nuestra interfaz estándar Dispositivo
 */
public class PhilipsHueAdapter implements Dispositivo {
    private PhilipsHueAPI hueAPI;
    private String bridgeIP;
    
    public PhilipsHueAdapter(String bridgeIP) {
        this.bridgeIP = bridgeIP;
        this.hueAPI = new PhilipsHueAPI(bridgeIP);
    }
    
    @Override
    public void encender() {
        // El adaptador traduce nuestro método estándar al método específico de Philips Hue
        System.out.println("Philips Hue Adapter: Convirtiendo comando 'encender' a setLightIntensity(100)");
        hueAPI.setLightIntensity(100);
    }
    
    @Override
    public void apagar() {
        // El adaptador traduce nuestro método estándar al método específico de Philips Hue
        System.out.println("Philips Hue Adapter: Convirtiendo comando 'apagar' a setLightIntensity(0)");
        hueAPI.setLightIntensity(0);
    }
    
    @Override
    public String getEstado() {
        int intensity = hueAPI.getLightIntensity();
        if (intensity == 0) {
            return "Apagada (Adaptador Philips Hue)";
        } else {
            return String.format("Encendida (Adaptador Philips Hue) - Intensidad: %d%%, Color: %s", 
                               intensity, hueAPI.getColor());
        }
    }
    
    @Override
    public String getTipo() {
        return "Bombilla Philips Hue (Adaptador)";
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
                    System.out.println("Philips Hue Adapter: Convirtiendo comando 'intensidad " + valor + "' a setLightIntensity(" + valor + ")");
                    hueAPI.setLightIntensity(valor);
                }
            } catch (Exception e) {
                System.out.println("Philips Hue Adapter: Error al procesar comando de intensidad");
            }
        } else if (accion.startsWith("color")) {
            String color = accion.substring(6).trim();
            System.out.println("Philips Hue Adapter: Estableciendo color a " + color);
            hueAPI.setColor(color);
        }
    }
    
    // Métodos adicionales específicos del adaptador
    public void setColor(String color) {
        hueAPI.setColor(color);
    }
    
    public String getColor() {
        return hueAPI.getColor();
    }
    
    public boolean isConnected() {
        return hueAPI.isConnected();
    }
}
