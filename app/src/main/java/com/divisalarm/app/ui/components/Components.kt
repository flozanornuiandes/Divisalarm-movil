package com.divisalarm.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.divisalarm.app.R

/**
 * Boton relleno (Material 3 "filled button"), equivalente a .btn-primary
 * en el frontend web / al boton azul solido del prototipo de Figma. No
 * ejecuta ninguna llamada real: solo dispara [onClick], que en todas las
 * pantallas del prototipo navega a otra pantalla o no hace nada.
 */
@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text)
    }
}

/** Boton con borde, equivalente a .btn-outline / a los botones blancos con borde del prototipo. */
@Composable
fun OutlineActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(999.dp)
    ) {
        Text(text)
    }
}

/**
 * Logo de DivisAlarm (campana + check), el mismo dibujo usado en el
 * frontend web y en el icono del launcher. Se dibuja con [Image] (no
 * [Icon]) para conservar sus dos colores propios (azul y naranja) en
 * vez de aplicarles un tint monocromatico.
 */
@Composable
fun DivisAlarmBell(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(id = R.drawable.ic_divisalarm_bell),
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}

val ScreenPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp)
