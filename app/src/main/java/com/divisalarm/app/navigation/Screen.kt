package com.divisalarm.app.navigation

// Cada pantalla del prototipo movil, calcada 1:1 del prototipo interactivo
// de Figma (archivo "DivisAlarm Movil", flujos 1 y 2). No hay datos reales
// viajando entre pantallas (mismo alcance que Divisalarm-web).
sealed class Screen(val route: String) {
    // FM1 - Acceso (Flujo 1 de Figma)
    data object Login : Screen("login")
    data object CrearCuenta : Screen("crear_cuenta")
    data object RecuperarAcceso : Screen("recuperar_acceso")
    data object NuevaContrasena : Screen("nueva_contrasena")

    // FM2 - Mis alarmas (Flujo 1 de Figma): solo activar/desactivar,
    // sin crear/editar/eliminar (asi esta definido en el mockup).
    data object MisAlarmas : Screen("mis_alarmas")

    // FM2 - Detalle de alarma: se abre al tocar una fila de "Mis alarmas".
    // Recibe la divisa como argumento de ruta para mostrar su umbral y
    // fecha de ultima actualizacion.
    data object DetalleAlarma : Screen("detalle_alarma/{divisa}") {
        fun createRoute(divisa: String) = "detalle_alarma/$divisa"
    }

    // FM3 - Notificaciones (Flujo 2 de Figma)
    data object NotificacionSistema : Screen("notificacion_sistema")
    data object UsdAlarmaActiva : Screen("usd_alarma_activa")
    data object Postergar : Screen("postergar")

    // FM4 - Revision y decision de alarma (a la que lleva "Ver detalles")
    data object RevisionAlarma : Screen("revision_alarma")
}
