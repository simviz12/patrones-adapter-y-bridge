package com.smarthome.bridge;

/**
 * Implementación concreta de Control - Comando de Voz
 */
public class ComandoVoz extends Control {
    
    public ComandoVoz(Dispositivo dispositivo) {
        super(dispositivo);
    }
    
    @Override
    public void encender() {
        System.out.println("Comando de Voz: Procesando comando 'Encender'...");
        dispositivo.encender();
        System.out.println("Comando de Voz: Confirmación vocal - Dispositivo encendido");
    }
    
    @Override
    public void apagar() {
        System.out.println("Comando de Voz: Procesando comando 'Apagar'...");
        dispositivo.apagar();
        System.out.println("Comando de Voz: Confirmación vocal - Dispositivo apagado");
    }
    
    @Override
    public String getEstado() {
        return "Comando de Voz -> " + dispositivo.getTipo() + ": " + dispositivo.getEstado();
    }
    
    // Métodos adicionales específicos del comando de voz
    public void procesarComandoVoz(String comando) {
        System.out.println("Comando de Voz: Recognizing '" + comando + "'...");
        
        String comandoLower = comando.toLowerCase();
        if (comandoLower.contains("encender") || comandoLower.contains("prende")) {
            encender();
        } else if (comandoLower.contains("apagar") || comandoLower.contains("apaga")) {
            apagar();
        } else {
            dispositivo.ejecutarAccion(comando);
        }
    }
}
