package uk.adbsalam.snapit.sample

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.forGif
import uk.adbsalam.snapit.testing.gifSnapshot

@RunWith(JUnit4::class)
class ExampleScreenLightGifTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forGif()

    @Test
    fun `when a gif, should render correctly`() {
        paparazzi.gifSnapshot(0L, 500L, 30) {
            ExamplePreviewGif()
        }
    }
}



