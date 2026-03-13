package com.smarthome.adapter;

/**
 * Simulación de la API de terceros - Philips Hue
 * Esta representa la librería externa que no podemos modificar
 */
public class PhilipsHueAPI {
    private boolean connected = false;
    private int lightIntensity = 0;
    private String color = "white";
    
    public PhilipsHueAPI(String bridgeIP) {
        // Simula conexión con el bridge Hue
        this.connected = true;
        System.out.println("Philips Hue API: Conectado al bridge en " + bridgeIP);
    }
    
    // Método específico de la API de Philips Hue
    public void setLightIntensity(int intensity) {
        if (connected) {
            this.lightIntensity = Math.max(0, Math.min(100, intensity));
            System.out.println("Philips Hue API: Intensidad ajustada a " + this.lightIntensity + "%");
        }
    }
    
    public int getLightIntensity() {
        return lightIntensity;
    }
    
    // Otros métodos específicos de la API
    public void setColor(String color) {
        if (connected) {
            this.color = color;
            System.out.println("Philips Hue API: Color cambiado a " + color);
        }
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean isConnected() {
        return connected;
    }
}
