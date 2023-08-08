package uk.adbsalam.snapit.sample

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
fun ExampleCompose() {
    Text(text = "Hello World")
}

@Composable
@Preview
@SnapIt
fun ExamplePreview() {
    ExampleCompose()
}

@Composable
@Preview
@SnapIt(
    name = "when in preview, should render correctly",
    preview = true,
    isScreen = true
)
fun ExamplePreview2() {
    ExampleCompose()
}

@Composable
@Preview
@SnapIt(name = "when in preview, should render correctly", isDark = true)
fun ExamplePreviewDarkComponent() {
    ExampleCompose()
}