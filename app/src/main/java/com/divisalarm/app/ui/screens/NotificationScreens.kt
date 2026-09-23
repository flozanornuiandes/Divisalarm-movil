package com.divisalarm.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.divisalarm.app.ui.components.DivisAlarmBell
import com.divisalarm.app.ui.components.OutlineActionButton
import com.divisalarm.app.ui.components.PrimaryButton
import com.divisalarm.app.ui.components.ScreenPadding

/**
 * Notificacion del sistema (FM3) - equivalente al banner del prototipo
 * de Figma (flujo 2, node 120:202): representa el push que mostraria el
 * sistema operativo cuando una divisa cruza el umbral. Tocarlo abre
 * "USD - alarma activa".
 */
@Composable
fun NotificacionSistemaScreen(
    onAbrirClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenPadding),
        verticalArrangement = Arrangement.Bottom
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable(onClick = onAbrirClick),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DivisAlarmBell(size = 32.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text("DivisAlarm", style = MaterialTheme.typography.labelLarge)
                    Text(
                        "El USD subió $20 COP",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        "Supera tu umbral",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

/**
 * USD alarma activa (FM3) - equivalente al node 120:181 del prototipo:
 * precio actual, aviso de que supero el umbral, instruccion de agitar
 * el celular para silenciar, y 3 botones. "Silenciar" vuelve directo a
 * Mis alarmas; "Posponer" y "Ver detalles" llevan a sus pantallas, cuyo
 * boton final ("Confirmar" / "Marcar como atendida") tambien vuelve a
 * Mis alarmas.
 */
@Composable
fun UsdAlarmaActivaScreen(
    onSilenciarClick: () -> Unit,
    onPostergarClick: () -> Unit,
    onVerDetallesClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DivisAlarmBell()
            Text("USD", style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(Modifier.height(16.dp))
        Text("$4.320,50", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(16.dp))

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
            Text(
                "Subió $22 COP sobre tu umbral",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(Modifier.height(16.dp))

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
            Text(
                "Agita el celular para silenciar. Si no interactúas, se silencia sola en 1 minuto.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(Modifier.height(24.dp))

        PrimaryButton(text = "Silenciar", onClick = onSilenciarClick)
        Spacer(Modifier.height(12.dp))
        OutlineActionButton(
            text = "Posponer",
            onClick = onPostergarClick,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlineActionButton(
            text = "Ver detalles",
            onClick = onVerDetallesClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Posponer (FM3) - equivalente al node 120:198: opciones de 15 minutos,
 * 1 hora o 24 horas, y un boton "Confirmar" que aqui vuelve directo a
 * Mis alarmas (en el prototipo original de Figma no navega a ningun
 * lado, pero se prefirio cerrar el ciclo hacia la pantalla principal).
 */
@Composable
fun PostergarScreen(
    onConfirmarClick: () -> Unit
) {
    val opciones = listOf("15 minutos", "1 hora", "24 horas")
    var seleccion by remember { mutableStateOf(opciones.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DivisAlarmBell()
            Text("Posponer", style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(Modifier.height(16.dp))

        opciones.forEach { opcion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RadioButton(
                    selected = seleccion == opcion,
                    onClick = { seleccion = opcion }
                )
                Text(opcion, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Divider(color = MaterialTheme.colorScheme.outlineVariant)

        Spacer(Modifier.height(24.dp))
        PrimaryButton(text = "Confirmar", onClick = onConfirmarClick)
    }
}

/**
 * Revision y decision de alarma (FM4) - pantalla a la que lleva "Ver
 * detalles" desde "USD alarma activa": precio, mini grafico de
 * tendencia, aviso del umbral superado y un boton para marcar la
 * alarma como atendida. El grafico es data de ejemplo fija (no hay
 * consulta real a ninguna tasa de cambio); "Marcar como atendida"
 * vuelve directo a Mis alarmas, no persiste nada.
 */
@Composable
fun RevisionAlarmaScreen(
    onMarcarComoAtendidaClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DivisAlarmBell()
            Text("USD", style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(Modifier.height(16.dp))
        Text("$4.320,50", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(16.dp))

        TrendChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )
        Spacer(Modifier.height(16.dp))

        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
            Text(
                "Superó tu umbral por $22 COP",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        Spacer(Modifier.height(24.dp))

        PrimaryButton(text = "Marcar como atendida", onClick = onMarcarComoAtendidaClick)
    }
}

/** Mini grafico de linea de tendencia, datos de ejemplo fijos. */
@Composable
private fun TrendChart(modifier: Modifier = Modifier) {
    val puntos = listOf(0.08f, 0.32f, 0.22f, 0.55f, 0.42f, 0.62f, 0.55f, 0.7f, 0.82f)
    val lineColor = MaterialTheme.colorScheme.primary

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (puntos.size < 2) return@Canvas
            val stepX = size.width / (puntos.size - 1)
            val path = Path()
            puntos.forEachIndexed { index, valor ->
                val x = index * stepX
                val y = size.height * (1f - valor)
                if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
        }
    }
}
