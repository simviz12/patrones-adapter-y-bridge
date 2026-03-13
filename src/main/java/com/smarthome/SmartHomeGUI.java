package com.smarthome;

import com.smarthome.adapter.PhilipsHueAdapter;
import com.smarthome.adapter.XiaomiAdapter;
import com.smarthome.bridge.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Interfaz Gráfica del Sistema Smart Home
 * Demuestra el uso de los patrones Bridge y Adapter
 */
public class SmartHomeGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Componentes del patrón Bridge
    private Control controlActual;
    private Dispositivo dispositivoActual;
    
    // Panel de estado
    private JPanel panelEstado;
    private JPanel panelConsola;
    private JPanel panelPrincipal;
    
    // Controles de dispositivos
    private JComboBox<String> comboDispositivos;
    private JComboBox<String> comboControles;
    private JTextArea areaEstado;
    private JTextArea areaConsola;
    
    // Botones de acciones
    private JButton btnEncender;
    private JButton btnApagar;
    private JButton btnIntensidad;
    private JButton btnAccionPersonalizada;
    private JTextField txtComando;
    
    // Dispositivos disponibles
    private final Bombilla bombilla = new Bombilla();
    private final AireAcondicionado aireAcondicionado = new AireAcondicionado();
    private final Persiana persiana = new Persiana();
    private final PhilipsHueAdapter philipsHue = new PhilipsHueAdapter("192.168.1.100");
    private final XiaomiAdapter xiaomi = new XiaomiAdapter("XIAOMI_001");
    
    public SmartHomeGUI() {
        inicializarComponentes();
        configurarVentana();
        establecerDispositivoPorDefecto();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de control
        JPanel panelControl = crearPanelControl();
        
        // Panel de estado
        panelEstado = crearPanelEstado();
        
        // Panel de consola
        panelConsola = crearPanelConsola();
        
        panelPrincipal.add(panelControl, BorderLayout.NORTH);
        panelPrincipal.add(panelEstado, BorderLayout.CENTER);
        panelPrincipal.add(panelConsola, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private JPanel crearPanelControl() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Control de Dispositivos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Selección de dispositivo
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Dispositivo:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        comboDispositivos = new JComboBox<>(new String[]{
            "Bombilla Estándar",
            "Aire Acondicionado", 
            "Persiana",
            "Bombilla Philips Hue (Adapter)",
            "Bombilla Xiaomi (Adapter)"
        });
        comboDispositivos.addActionListener(e -> cambiarDispositivo());
        panel.add(comboDispositivos, gbc);
        
        // Selección de control
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tipo de Control:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        comboControles = new JComboBox<>(new String[]{
            "Control Remoto Básico",
            "App Móvil",
            "Comando de Voz"
        });
        comboControles.addActionListener(e -> cambiarControl());
        panel.add(comboControles, gbc);
        
        // Botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout());
        
        btnEncender = new JButton("Encender");
        btnEncender.addActionListener(e -> encenderDispositivo());
        panelBotones.add(btnEncender);
        
        btnApagar = new JButton("Apagar");
        btnApagar.addActionListener(e -> apagarDispositivo());
        panelBotones.add(btnApagar);
        
        btnIntensidad = new JButton("Intensidad 50%");
        btnIntensidad.addActionListener(e -> ajustarIntensidad());
        panelBotones.add(btnIntensidad);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);
        
        // Acción personalizada
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        panel.add(new JLabel("Comando:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 1;
        txtComando = new JTextField(20);
        panel.add(txtComando, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        btnAccionPersonalizada = new JButton("Ejecutar Comando");
        btnAccionPersonalizada.addActionListener(e -> ejecutarComandoPersonalizado());
        panel.add(btnAccionPersonalizada, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Estado del Sistema"));
        
        areaEstado = new JTextArea(8, 40);
        areaEstado.setEditable(false);
        areaEstado.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        areaEstado.setBackground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(areaEstado);
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelConsola() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Consola de Depuración"));
        
        areaConsola = new JTextArea(6, 40);
        areaConsola.setEditable(false);
        areaConsola.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        areaConsola.setBackground(Color.BLACK);
        areaConsola.setForeground(Color.GREEN);
        
        JScrollPane scroll = new JScrollPane(areaConsola);
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void configurarVentana() {
        setTitle("Smart Home - Patrones Bridge y Adapter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 400));
    }
    
    private void establecerDispositivoPorDefecto() {
        comboDispositivos.setSelectedIndex(0);
        comboControles.setSelectedIndex(0);
        cambiarDispositivo();
        cambiarControl();
        actualizarEstado();
    }
    
    private void cambiarDispositivo() {
        int index = comboDispositivos.getSelectedIndex();
        switch (index) {
            case 0:
                dispositivoActual = bombilla;
                break;
            case 1:
                dispositivoActual = aireAcondicionado;
                break;
            case 2:
                dispositivoActual = persiana;
                break;
            case 3:
                dispositivoActual = philipsHue;
                break;
            case 4:
                dispositivoActual = xiaomi;
                break;
        }
        
        if (controlActual != null) {
            controlActual.setDispositivo(dispositivoActual);
        }
        
        actualizarBotonesSegunDispositivo();
        actualizarEstado();
    }
    
    private void cambiarControl() {
        int index = comboControles.getSelectedIndex();
        switch (index) {
            case 0:
                controlActual = new ControlRemotoBasico(dispositivoActual);
                break;
            case 1:
                controlActual = new AppMovil(dispositivoActual);
                break;
            case 2:
                controlActual = new ComandoVoz(dispositivoActual);
                break;
        }
        
        actualizarEstado();
    }
    
    private void actualizarBotonesSegunDispositivo() {
        String tipo = dispositivoActual.getTipo();
        
        // Mostrar/ocultar botón de intensidad
        boolean esBombilla = tipo.contains("Bombilla");
        btnIntensidad.setVisible(esBombilla);
        
        // Actualizar texto del botón según el dispositivo
        if (tipo.contains("Aire")) {
            btnIntensidad.setText("Temperatura 22°C");
        } else if (tipo.contains("Persiana")) {
            btnIntensidad.setText("Posición 50%");
        } else {
            btnIntensidad.setText("Intensidad 50%");
        }
    }
    
    private void encenderDispositivo() {
        controlActual.encender();
        actualizarEstado();
    }
    
    private void apagarDispositivo() {
        controlActual.apagar();
        actualizarEstado();
    }
    
    private void ajustarIntensidad() {
        String tipo = dispositivoActual.getTipo();
        String comando = "";
        
        if (tipo.contains("Aire")) {
            comando = "temperatura 22";
        } else if (tipo.contains("Persiana")) {
            comando = "posicion 50";
        } else {
            comando = "intensidad 50";
        }
        
        dispositivoActual.ejecutarAccion(comando);
        actualizarEstado();
    }
    
    private void ejecutarComandoPersonalizado() {
        String comando = txtComando.getText().trim();
        if (!comando.isEmpty()) {
            if (controlActual instanceof ComandoVoz) {
                ((ComandoVoz) controlActual).procesarComandoVoz(comando);
            } else if (controlActual instanceof AppMovil) {
                ((AppMovil) controlActual).ejecutarAccionPersonalizada(comando);
            } else {
                dispositivoActual.ejecutarAccion(comando);
            }
            
            // Cambiar color de la interfaz si es un comando de color
            cambiarColorInterfazSegunComando(comando);
            
            txtComando.setText("");
            actualizarEstado();
        }
    }
    
    private void cambiarColorInterfazSegunComando(String comando) {
        String comandoLower = comando.toLowerCase();
        
        if (comandoLower.contains("rojo")) {
            cambiarColorInterfaz(new Color(255, 200, 200), new Color(220, 20, 60));
        } else if (comandoLower.contains("azul")) {
            cambiarColorInterfaz(new Color(200, 220, 255), new Color(0, 120, 215));
        } else if (comandoLower.contains("verde")) {
            cambiarColorInterfaz(new Color(200, 255, 200), new Color(34, 139, 34));
        } else if (comandoLower.contains("amarillo")) {
            cambiarColorInterfaz(new Color(255, 255, 200), new Color(255, 215, 0));
        } else if (comandoLower.contains("naranja")) {
            cambiarColorInterfaz(new Color(255, 230, 200), new Color(255, 140, 0));
        } else if (comandoLower.contains("morado") || comandoLower.contains("púrpura")) {
            cambiarColorInterfaz(new Color(230, 200, 255), new Color(128, 0, 128));
        } else if (comandoLower.contains("blanco")) {
            cambiarColorInterfaz(Color.WHITE, Color.GRAY);
        } else if (comandoLower.contains("negro")) {
            cambiarColorInterfaz(new Color(240, 240, 240), Color.BLACK);
        }
    }
    
    private void cambiarColorInterfaz(Color colorFondo, Color colorAcento) {
        // Cambiar color del panel principal
        panelPrincipal.setBackground(colorFondo);
        
        // Cambiar color de los paneles secundarios
        if (panelEstado != null) {
            panelEstado.setBackground(colorFondo);
        }
        if (panelConsola != null) {
            panelConsola.setBackground(colorFondo);
        }
        
        // Cambiar color de los botones principales
        btnEncender.setBackground(colorAcento);
        btnEncender.setForeground(Color.WHITE);
        btnApagar.setBackground(colorAcento);
        btnApagar.setForeground(Color.WHITE);
        btnIntensidad.setBackground(colorAcento);
        btnIntensidad.setForeground(Color.WHITE);
        btnAccionPersonalizada.setBackground(colorAcento);
        btnAccionPersonalizada.setForeground(Color.WHITE);
        
        // Cambiar color del borde de los paneles
        if (panelEstado != null) {
            panelEstado.setBorder(new TitledBorder(
                BorderFactory.createLineBorder(colorAcento, 2), 
                "Estado del Sistema"
            ));
        }
        if (panelConsola != null) {
            panelConsola.setBorder(new TitledBorder(
                BorderFactory.createLineBorder(colorAcento, 2), 
                "Consola de Depuración"
            ));
        }
        
        // Refrescar la interfaz
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    private void actualizarEstado() {
        StringBuilder estado = new StringBuilder();
        estado.append("=== ESTADO ACTUAL DEL SISTEMA ===\n\n");
        
        // Información del control
        estado.append("Tipo de Control: ").append(comboControles.getSelectedItem()).append("\n");
        estado.append("Dispositivo Seleccionado: ").append(comboDispositivos.getSelectedItem()).append("\n");
        estado.append("Tipo Real del Dispositivo: ").append(dispositivoActual.getTipo()).append("\n");
        estado.append("Estado del Dispositivo: ").append(dispositivoActual.getEstado()).append("\n\n");
        
        // Estado del control
        if (controlActual != null) {
            estado.append("Estado del Control: ").append(controlActual.getEstado()).append("\n");
        }
        
        // Información específica del dispositivo
        estado.append("\n=== INFORMACIÓN DETALLADA ===\n");
        
        if (dispositivoActual instanceof Bombilla) {
            Bombilla b = (Bombilla) dispositivoActual;
            estado.append("Intensidad: ").append(b.getIntensidad()).append("%\n");
        } else if (dispositivoActual instanceof AireAcondicionado) {
            AireAcondicionado ac = (AireAcondicionado) dispositivoActual;
            estado.append("Temperatura: ").append(ac.getTemperatura()).append("°C\n");
            estado.append("Modo: ").append(ac.getModo()).append("\n");
        } else if (dispositivoActual instanceof Persiana) {
            Persiana p = (Persiana) dispositivoActual;
            estado.append("Posición: ").append(p.getPosicion()).append("%\n");
        } else if (dispositivoActual instanceof PhilipsHueAdapter) {
            PhilipsHueAdapter ph = (PhilipsHueAdapter) dispositivoActual;
            estado.append("Conectado: ").append(ph.isConnected() ? "Sí" : "No").append("\n");
            estado.append("Color: ").append(ph.getColor()).append("\n");
        } else if (dispositivoActual instanceof XiaomiAdapter) {
            XiaomiAdapter xm = (XiaomiAdapter) dispositivoActual;
            estado.append("ID del Dispositivo: ").append(xm.getDeviceId()).append("\n");
            estado.append("Brillo: ").append(xm.getBrightness()).append("%\n");
        }
        
        // Demostración de patrones
        estado.append("\n=== PATRONES EN ACCIÓN ===\n");
        estado.append("Bridge: El control (").append(comboControles.getSelectedItem())
               .append(") opera sobre el dispositivo sin conocer su implementación interna.\n");
        
        if (dispositivoActual instanceof PhilipsHueAdapter || dispositivoActual instanceof XiaomiAdapter) {
            estado.append("Adapter: El dispositivo de terceros está siendo adaptado a nuestra interfaz estándar.\n");
        }
        
        areaEstado.setText(estado.toString());
        areaEstado.setCaretPosition(0);
    }
}
