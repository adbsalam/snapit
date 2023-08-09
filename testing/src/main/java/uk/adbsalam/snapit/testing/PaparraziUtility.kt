package uk.adbsalam.snapit.testing

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode

/**
 *
 */
fun Paparazzi.Companion.forComponent() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO,
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.SHRINK,
    showSystemUi = false
)

/**
 *
 */
fun Paparazzi.Companion.forDarkComponent() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO.copy(nightMode = NightMode.NIGHT),
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.SHRINK,
    showSystemUi = false
)

/**
 *
 */
fun Paparazzi.Companion.forScreen() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO,
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
    showSystemUi = true
)

/**
 *
 */
fun Paparazzi.Companion.forDarkScreen() = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO,
    maxPercentDifference = 0.0,
    renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
    showSystemUi = true
)

/**
 *
 */
fun Paparazzi.Companion.forGif() = Paparazzi()

/**
 *
 */
fun Paparazzi.Companion.forDarkGif() = Paparazzi(
    deviceConfig = DeviceConfig.NEXUS_5.copy(nightMode = NightMode.NIGHT)
)

/**
 *
 */
fun Paparazzi.captureScreenshot(composable: @Composable () -> Unit) {
    this.snapshot {
        composable()
    }
}

/**
 *
 */
fun Paparazzi.gifSnapshot(
    start: Long = 0L,
    end: Long = 500L,
    fps: Int = 30,
    content: @Composable () -> Unit,
) {
    val composable: @Composable () -> Unit = {
        content()
    }
    val hostView = ComposeView(context)
    hostView.setContent(composable)
    gif(view = hostView, start = start, end = end, fps = fps)
}

