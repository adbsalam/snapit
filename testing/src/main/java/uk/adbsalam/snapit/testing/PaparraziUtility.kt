package uk.adbsalam.snapit.testing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode

fun Paparazzi.Companion.forComponent() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO,
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.SHRINK,
    showSystemUi = false
)

fun Paparazzi.Companion.forDarkComponent() = Paparazzi(
    deviceConfig = DeviceConfig(nightMode = NightMode.NIGHT),
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.SHRINK,
    showSystemUi = false
)

fun Paparazzi.Companion.forScreen() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO,
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
    showSystemUi = true
)

fun Paparazzi.Companion.forDarkScreen() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO,
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
    showSystemUi = true
)

fun Paparazzi.captureScreenshot(composable: @Composable () -> Unit) {
    this.snapshot {
        Box(modifier = Modifier.background(Color.White)) {
            composable()
        }
    }
}

fun Paparazzi.captureDarkScreenshot(composable: @Composable () -> Unit) {
    this.snapshot {
        Box(modifier = Modifier.background(Color.Unspecified)) {
            composable()
        }
    }
}

