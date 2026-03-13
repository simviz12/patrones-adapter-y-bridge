package com.smarthome.bridge;

/**
 * Implementación concreta de Control - Aplicación Móvil
 */
public class AppMovil extends Control {
    
    public AppMovil(Dispositivo dispositivo) {
        super(dispositivo);
    }
    
    @Override
    public void encender() {
        System.out.println("App Móvil: Enviando comando via WiFi...");
        dispositivo.encender();
        System.out.println("App Móvil: Notificación推送 - Dispositivo encendido");
    }
    
    @Override
    public void apagar() {
        System.out.println("App Móvil: Enviando comando via WiFi...");
        dispositivo.apagar();
        System.out.println("App Móvil: Notificación推送 - Dispositivo apagado");
    }
    
    @Override
    public String getEstado() {
        return "App Móvil -> " + dispositivo.getTipo() + ": " + dispositivo.getEstado();
    }
    
    // Métodos adicionales específicos de la app móvil
    public void ejecutarAccionPersonalizada(String accion) {
        System.out.println("App Móvil: Ejecutando acción personalizada: " + accion);
        dispositivo.ejecutarAccion(accion);
    }
}
