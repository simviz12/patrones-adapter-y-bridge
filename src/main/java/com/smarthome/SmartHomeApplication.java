package com.smarthome;

import javax.swing.SwingUtilities;

/**
 * Aplicación principal del Sistema Smart Home
 * Demuestra los patrones Bridge y Adapter
 */
public class SmartHomeApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new SmartHomeGUI().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
