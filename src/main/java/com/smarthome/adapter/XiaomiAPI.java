package com.smarthome.adapter;

/**
 * Simulación de la API de terceros - Xiaomi Smart Home
 * Esta representa la librería externa que no podemos modificar
 */
public class XiaomiAPI {
    private String deviceId;
    private boolean powerState = false;
    private int brightness = 0;
    
    public XiaomiAPI(String deviceId) {
        this.deviceId = deviceId;
        System.out.println("Xiaomi API: Dispositivo " + deviceId + " inicializado");
    }
    
    // Métodos específicos de la API de Xiaomi
    public void setPower(boolean state) {
        this.powerState = state;
        System.out.println("Xiaomi API: Power set to " + state + " for device " + deviceId);
    }
    
    public void setBrightness(int level) {
        this.brightness = Math.max(1, Math.min(100, level));
        if (this.brightness > 0) {
            this.powerState = true;
        }
        System.out.println("Xiaomi API: Brightness set to " + this.brightness + " for device " + deviceId);
    }
    
    public boolean getPowerState() {
        return powerState;
    }
    
    public int getBrightness() {
        return brightness;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
}
