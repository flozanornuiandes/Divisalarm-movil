# DivisAlarm — Movil (frontend)

Prototipo de interfaz nativo para Android, escrito en **Kotlin** con
**Jetpack Compose** y **Material 3**, construido a partir del mismo Style
Tile y los mismos mockups de Figma que el frontend web
([Divisalarm-web](https://github.com/Jcepeda8/Divisalarm-web)) del proyecto
DivisAlarm (MISO - UX).

No hay backend: los componentes interactivos (switches, radio buttons,
steppers, dialogos) responden visualmente, pero no guardan datos, no
autentican y no hacen llamadas de red.

## Requisitos

- Android Studio (Koala o superior) con el Android SDK.
- JDK 17 (Android Studio lo trae incluido).
- minSdk 27 (Android 8.1), compileSdk / targetSdk 34.

## Como abrirlo

1. Abre la carpeta `Divisalarm-movil` completa como proyecto en Android
   Studio (**File > Open**).
2. Espera a que Gradle sincronice (la primera vez descarga el Android SDK
   y las dependencias de Compose; requiere conexion a internet).
3. Ejecuta la configuracion `app` en un emulador o dispositivo fisico con
   API 27+.

## Generar el APK

Desde Android Studio: **Build > Build App Bundle(s) / APK(s) > Build
APK(s)**. El archivo queda en `app/build/outputs/apk/debug/app-debug.apk`.

Desde la linea de comandos (con el SDK ya configurado y `ANDROID_HOME`
apuntando a el):

```
./gradlew assembleDebug
```

En Windows: `gradlew.bat assembleDebug`.

## Mapa de pantallas

| Pantalla (Compose) | Equivalente web | Grupo |
|---|---|---|
| `LoginScreen` | `login.html` | FM1 - Acceso |
| `CrearCuentaScreen` | `crear-cuenta.html` | FM1 - Acceso |
| `RecuperarAccesoScreen` | `recuperar-acceso.html` | FM1 - Acceso |
| `NuevaContrasenaScreen` | `nueva-contrasena.html` | FM1 - Acceso |
| `MisAlarmasScreen` | `panel-vacio.html` / `panel-datos.html` | FM2 - Alarmas |
| `UsdDetalleScreen` | `elige-divisa.html` + `define-umbral.html` + `confirma-alarma.html` + `alarma-creada.html` | FM2 - Alarmas |
| `EditarAlarmaScreen` | `editar-alarma.html` | FM2 - Alarmas |
| `NotificacionSistemaScreen` | (solo movil) | FM3 - Notificaciones |
| `UsdAlarmaActivaScreen` | (solo movil) | FM3 - Notificaciones |
| `PostergarScreen` | (solo movil) | FM3 - Notificaciones |
| `UsdHistoricoScreen` | (solo movil) | FM4 - Historico |

`UsdDetalleScreen` reune en una sola pantalla, con pasos internos, el
flujo de 4 HTML independientes del frontend web (elegir divisa, definir
umbral, confirmar y ver el estado de exito), porque en Compose ese tipo
de wizard se modela como una sola pantalla con estado en vez de 4 rutas
separadas. Sigue siendo completamente navegable con el boton "Atras" /
"Continuar" de cada paso.

### Navegacion

```
Login → Mis alarmas → USD detalle (elegir divisa → umbral → confirmar) → Mis alarmas
Login → Recuperar acceso → Nueva contrasena → Login
Login → Crear cuenta → Login
Mis alarmas → Editar alarma → Mis alarmas
Mis alarmas → Notificacion del sistema → USD alarma activa → Postergar → USD alarma activa
                                                            → USD historico
```

## Estructura

```
Divisalarm-movil/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/divisalarm/app/
│       │   ├── MainActivity.kt
│       │   ├── navigation/         # Screen.kt, DivisAlarmNavGraph.kt
│       │   └── ui/
│       │       ├── theme/          # Color.kt, Theme.kt, Type.kt
│       │       ├── components/     # botones y piezas compartidas
│       │       └── screens/        # las 10+ pantallas del prototipo
│       └── res/                    # strings, iconos, tema base
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew / gradlew.bat
```

## Design tokens (Style Tile)

Los mismos valores que usa el frontend web en `css/styles.css`, declarados
en `ui/theme/Color.kt` y mapeados al `ColorScheme` de Material 3 en
`ui/theme/Theme.kt`: Primary `#0B3D5C`, Secondary `#F2A93B`, Tertiary
`#1C7293`, Error `#B3261E`, Surface `#F4F7FA`, Outline/On Surface Variant
`#5B6B7C`.

## Limitaciones conocidas (prototipo)

- Los datos no viajan entre pantallas de verdad: `UsdDetalleScreen` guarda
  la seleccion solo mientras esa pantalla esta en memoria (estado de
  Compose), no hay persistencia.
- Ninguna pantalla valida credenciales, guarda alarmas ni consulta tasas
  de cambio reales; todos los valores mostrados son de ejemplo.
- El icono de la app es un vector propio inspirado en el logo de
  DivisAlarm; no se genero a partir del archivo `img/logo.png` original.