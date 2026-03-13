# Sistema Smart Home - Patrones Bridge y Adapter

Esta aplicación demuestra la implementación de los patrones de diseño **Bridge** y **Adapter** en un sistema de Smart Home con interfaz gráfica en Swing.

## 🏗️ Estructura del Proyecto

```
src/main/java/com/smarthome/
├── SmartHomeApplication.java     # Clase principal que inicia la GUI
├── SmartHomeGUI.java             # Interfaz gráfica Swing
├── bridge/                       # Implementación del patrón Bridge
│   ├── Dispositivo.java          # Interfaz del Implementador
│   ├── Control.java              # Abstracción del Control
│   ├── Bombilla.java             # Dispositivo concreto
│   ├── AireAcondicionado.java    # Dispositivo concreto
│   ├── Persiana.java             # Dispositivo concreto
│   ├── ControlRemotoBasico.java  # Control concreto
│   ├── AppMovil.java             # Control concreto
│   └── ComandoVoz.java           # Control concreto
└── adapter/                      # Implementación del patrón Adapter
    ├── PhilipsHueAPI.java        # API de terceros (simulada)
    ├── PhilipsHueAdapter.java    # Adaptador para Philips Hue
    ├── XiaomiAPI.java            # API de terceros (simulada)
    └── XiaomiAdapter.java        # Adaptador para Xiaomi
```

## 🎯 Patrones Implementados

### Patrón Bridge
**Propósito:** Separar la abstracción de su implementación para que ambas puedan variar independientemente.

- **Abstracción:** `Control` (Control Remoto, App Móvil, Comando de Voz)
- **Implementación:** `Dispositivo` (Bombilla, Aire Acondicionado, Persiana)
- **Beneficio:** Puedes combinar cualquier tipo de control con cualquier tipo de dispositivo sin crear clases hijas para cada combinación.

### Patrón Adapter
**Propósito:** Permitir que interfaces incompatibles trabajen juntas.

- **Target:** `Dispositivo` (nuestra interfaz estándar)
- **Adaptee:** `PhilipsHueAPI`, `XiaomiAPI` (APIs de terceros)
- **Adapter:** `PhilipsHueAdapter`, `XiaomiAdapter`
- **Beneficio:** Integra dispositivos de terceros con APIs diferentes a nuestro sistema sin modificar el código existente.

## 🚀 Cómo Ejecutar

### Compilar el Proyecto
```bash
cd "c:\Users\usuario\Desktop\patron12"
javac -cp . -d . src/main/java/com/smarthome/*.java src/main/java/com/smarthome/bridge/*.java src/main/java/com/smarthome/adapter/*.java
```

### Ejecutar la Aplicación
```bash
java com.smarthome.SmartHomeApplication
```

## 🎮 Uso de la Aplicación

1. **Seleccionar Dispositivo:** Elige entre dispositivos estándar o dispositivos de terceros (con adaptadores)
2. **Seleccionar Control:** Elige el tipo de control (Remoto, App Móvil, Comando de Voz)
3. **Control Básico:** Usa los botones "Encender" y "Apagar"
4. **Acciones Específicas:**
   - **Bombillas:** Ajustar intensidad
   - **Aire Acondicionado:** Ajustar temperatura
   - **Persianas:** Ajustar posición
5. **Comandos Personalizados:** Ingresa comandos como:
   - `temperatura 25` (para aire acondicionado)
   - `posicion 75` (para persianas)
   - `intensidad 80` (para bombillas)
   - `modo frio` (para aire acondicionado)
   - `color azul` (para Philips Hue)

## 🔍 Demostración de Patrones

### Bridge en Acción
- El mismo control (ej. App Móvil) puede operar diferentes dispositivos
- Los controles no conocen la implementación interna de los dispositivos
- Solo llaman a `dispositivo.ejecutarAccion()` sin saber qué hace físicamente

### Adapter en Acción
- Los dispositivos Philips Hue y Xiaomi usan métodos diferentes (`setLightIntensity()`, `setPower()`)
- Los adaptadores convierten estos métodos a nuestra interfaz estándar (`encender()`, `apagar()`)
- El sistema principal nunca sabe que está hablando con dispositivos de "idiomas" diferentes

## 📊 Características de la GUI

- **Panel de Control:** Selección de dispositivos y controles
- **Panel de Estado:** Muestra información detallada del sistema
- **Consola de Depuración:** Muestra los mensajes de los patrones en acción
- **Actualización Dinámica:** Los botones se adaptan según el tipo de dispositivo

## 🎓 Objetivos Educativos

1. **Comprender el patrón Bridge:** Separación de abstracción e implementación
2. **Comprender el patrón Adapter:** Integración de sistemas incompatibles
3. **Ver la flexibilidad:** Combinación independiente de controles y dispositivos
4. **Práctica real:** Aplicación GUI que demuestra patrones en acción

## 🔧 Extensión del Proyecto

Para extender este sistema:

1. **Nuevo Dispositivo:** Implementa `Dispositivo` y añádelo a la GUI
2. **Nuevo Control:** Hereda de `Control` y añádelo a la GUI
3. **Nuevo Adaptador:** Crea un adaptador para otra API de terceros
4. **Nuevas Acciones:** Añade más comandos personalizados

---

**Nota:** Esta aplicación es puramente educativa para demostrar patrones de diseño. Las APIs de terceros son simulaciones.
