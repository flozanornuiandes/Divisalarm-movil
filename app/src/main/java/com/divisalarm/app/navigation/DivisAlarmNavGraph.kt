package com.divisalarm.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.divisalarm.app.ui.screens.CrearCuentaScreen
import com.divisalarm.app.ui.screens.DetalleAlarmaScreen
import com.divisalarm.app.ui.screens.LoginScreen
import com.divisalarm.app.ui.screens.MisAlarmasScreen
import com.divisalarm.app.ui.screens.NotificacionSistemaScreen
import com.divisalarm.app.ui.screens.NuevaContrasenaScreen
import com.divisalarm.app.ui.screens.PostergarScreen
import com.divisalarm.app.ui.screens.RecuperarAccesoScreen
import com.divisalarm.app.ui.screens.RevisionAlarmaScreen
import com.divisalarm.app.ui.screens.UsdAlarmaActivaScreen

/**
 * Grafo de navegacion de las 9 pantallas del prototipo movil, calcado
 * del prototipo interactivo de Figma (2 flujos: acceso + alarmas, y
 * notificacion + alarma activa + posponer), mas el detalle de alarma
 * agregado a pedido (tocar una fila de "Mis alarmas"). Ninguna ruta
 * llama a un backend: todas las pantallas son interactivas pero no
 * funcionales.
 */
@Composable
fun DivisAlarmNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.MisAlarmas.route) },
                onRecuperarClick = { navController.navigate(Screen.RecuperarAcceso.route) },
                onCrearCuentaClick = { navController.navigate(Screen.CrearCuenta.route) }
            )
        }

        composable(Screen.CrearCuenta.route) {
            CrearCuentaScreen(
                onCrearCuentaClick = { navController.popBackStack(Screen.Login.route, inclusive = false) },
                onYaTengoCuentaClick = { navController.popBackStack() }
            )
        }

        composable(Screen.RecuperarAcceso.route) {
            RecuperarAccesoScreen(
                onEnviarClick = { navController.navigate(Screen.NuevaContrasena.route) }
            )
        }

        composable(Screen.NuevaContrasena.route) {
            NuevaContrasenaScreen(
                onGuardarClick = { navController.popBackStack(Screen.Login.route, inclusive = false) }
            )
        }

        composable(Screen.MisAlarmas.route) {
            MisAlarmasScreen(
                onVerNotificacionClick = { navController.navigate(Screen.NotificacionSistema.route) },
                onAlarmaClick = { divisa -> navController.navigate(Screen.DetalleAlarma.createRoute(divisa)) }
            )
        }

        composable(
            route = Screen.DetalleAlarma.route,
            arguments = listOf(navArgument("divisa") { type = NavType.StringType })
        ) { backStackEntry ->
            val divisa = backStackEntry.arguments?.getString("divisa").orEmpty()
            DetalleAlarmaScreen(
                divisa = divisa,
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable(Screen.NotificacionSistema.route) {
            NotificacionSistemaScreen(
                onAbrirClick = { navController.navigate(Screen.UsdAlarmaActiva.route) }
            )
        }

        composable(Screen.UsdAlarmaActiva.route) {
            UsdAlarmaActivaScreen(
                // Silenciar, y los botones finales de Posponer/Ver detalles,
                // vuelven todos directo al panel principal (Mis alarmas),
                // limpiando el resto del flujo de notificacion de la pila.
                onSilenciarClick = { navController.popBackStack(Screen.MisAlarmas.route, inclusive = false) },
                onPostergarClick = { navController.navigate(Screen.Postergar.route) },
                onVerDetallesClick = { navController.navigate(Screen.RevisionAlarma.route) }
            )
        }

        composable(Screen.Postergar.route) {
            PostergarScreen(
                onConfirmarClick = { navController.popBackStack(Screen.MisAlarmas.route, inclusive = false) }
            )
        }

        composable(Screen.RevisionAlarma.route) {
            // "Marcar como atendida" no persiste nada, solo regresa al panel principal.
            RevisionAlarmaScreen(
                onMarcarComoAtendidaClick = { navController.popBackStack(Screen.MisAlarmas.route, inclusive = false) }
            )
        }
    }
}
