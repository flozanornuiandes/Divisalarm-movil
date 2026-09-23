package com.divisalarm.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.divisalarm.app.ui.components.DivisAlarmBell
import com.divisalarm.app.ui.components.ScreenPadding

/**
 * Mis alarmas (FM2) - equivalente a la pantalla "Mis alarmas" del
 * prototipo de Figma (flujo 1, node 120:157). A diferencia del frontend
 * web, en el mockup movil esta pantalla NO tiene crear/editar/eliminar:
 * cada alarma ya existe y solo se puede activar o desactivar con un
 * switch. No hay boton "+" ni iconos de lapiz/basura. Tocar la fila
 * (fuera del switch) abre el detalle de esa alarma (ver
 * AlarmDetailScreen.kt).
 */
internal data class AlarmaEjemplo(
    val divisa: String,
    val umbral: String,
    val activaInicialmente: Boolean,
    val umbralCop: String,
    val ultimaActualizacion: String
)

internal val alarmasEjemplo = listOf(
    AlarmaEjemplo("USD", "Umbral: $20", activaInicialmente = true, umbralCop = "$20 COP", ultimaActualizacion = "10/09/26 14:55"),
    AlarmaEjemplo("EUR", "Umbral: $30", activaInicialmente = false, umbralCop = "$30 COP", ultimaActualizacion = "10/09/26 14:40"),
    AlarmaEjemplo("GBP", "Umbral: $25", activaInicialmente = true, umbralCop = "$25 COP", ultimaActualizacion = "10/09/26 14:50")
)

@Composable
fun MisAlarmasScreen(
    onVerNotificacionClick: () -> Unit,
    onAlarmaClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mis alarmas", style = MaterialTheme.typography.headlineLarge)
            IconButton(onClick = onVerNotificacionClick) {
                DivisAlarmBell(contentDescription = "Ver notificación de ejemplo")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "Act. hace 2 min",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.height(8.dp))

        alarmasEjemplo.forEach { alarma ->
            var activa by remember { mutableStateOf(alarma.activaInicialmente) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAlarmaClick(alarma.divisa) }
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(alarma.divisa, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        alarma.umbral,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                color = if (activa) MaterialTheme.colorScheme.secondary
                                else MaterialTheme.colorScheme.outlineVariant,
                                shape = CircleShape
                            )
                    )
                    Spacer(Modifier.width(8.dp))
                    Switch(checked = activa, onCheckedChange = { activa = it })
                }
            }
            Divider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}
