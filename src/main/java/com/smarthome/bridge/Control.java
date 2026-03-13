package com.smarthome.bridge;

/**
 * Abstracción - Parte del patrón Bridge
 * Define la interfaz de control y mantiene una referencia al implementador
 */
public abstract class Control {
    protected Dispositivo dispositivo;
    
    public Control(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
    
    public abstract void encender();
    public abstract void apagar();
    public abstract String getEstado();
    
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
    
    public Dispositivo getDispositivo() {
        return dispositivo;
    }
}
