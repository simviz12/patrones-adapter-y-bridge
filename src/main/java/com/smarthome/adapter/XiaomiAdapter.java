package com.smarthome.adapter;

import com.smarthome.bridge.Dispositivo;

/**
 * Adaptador - Parte del patrón Adapter
 * Convierte la interfaz de Xiaomi a nuestra interfaz estándar Dispositivo
 */
public class XiaomiAdapter implements Dispositivo {
    private final XiaomiAPI xiaomiAPI;
    
    public XiaomiAdapter(String deviceId) {
        this.xiaomiAPI = new XiaomiAPI(deviceId);
    }
    
    @Override
    public void encender() {
        // El adaptador traduce nuestro método estándar a los métodos específicos de Xiaomi
        System.out.println("Xiaomi Adapter: Convirtiendo comando 'encender' a setPower(true) y setBrightness(100)");
        xiaomiAPI.setPower(true);
        xiaomiAPI.setBrightness(100);
    }
    
    @Override
    public void apagar() {
        // El adaptador traduce nuestro método estándar a los métodos específicos de Xiaomi
        System.out.println("Xiaomi Adapter: Convirtiendo comando 'apagar' a setPower(false)");
        xiaomiAPI.setPower(false);
    }
    
    @Override
    public String getEstado() {
        if (!xiaomiAPI.getPowerState()) {
            return "Apagada (Adaptador Xiaomi)";
        } else {
            return String.format("Encendida (Adaptador Xiaomi) - Brillo: %d%%", xiaomiAPI.getBrightness());
        }
    }
    
    @Override
    public String getTipo() {
        return "Bombilla Xiaomi (Adaptador)";
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
                    System.out.println("Xiaomi Adapter: Convirtiendo comando 'intensidad " + valor + "' a setBrightness(" + valor + ")");
                    xiaomiAPI.setBrightness(valor);
                }
            } catch (Exception e) {
                System.out.println("Xiaomi Adapter: Error al procesar comando de intensidad");
            }
        }
    }
    
    // Métodos adicionales específicos del adaptador
    public int getBrightness() {
        return xiaomiAPI.getBrightness();
    }
    
    public String getDeviceId() {
        return xiaomiAPI.getDeviceId();
    }
}
