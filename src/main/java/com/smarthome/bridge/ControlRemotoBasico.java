package com.smarthome.bridge;

/**
 * Implementación concreta de Control - Control Remoto Básico
 */
public class ControlRemotoBasico extends Control {
    
    public ControlRemotoBasico(Dispositivo dispositivo) {
        super(dispositivo);
    }
    
    @Override
    public void encender() {
        System.out.println("Control Remoto Básico: Enviando señal de encendido...");
        dispositivo.encender();
    }
    
    @Override
    public void apagar() {
        System.out.println("Control Remoto Básico: Enviando señal de apagado...");
        dispositivo.apagar();
    }
    
    @Override
    public String getEstado() {
        return "Control Remoto Básico -> " + dispositivo.getTipo() + ": " + dispositivo.getEstado();
    }
}
