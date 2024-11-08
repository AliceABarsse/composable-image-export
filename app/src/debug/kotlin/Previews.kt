package fr.caravellecode.brushexamples

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.caravellecode.brushexamples.captureImage.ExampleExportImage
import fr.caravellecode.brushexamples.captureImage.InputContentRasterComposable
import fr.caravellecode.brushexamples.captureImage.InputContentVectorComposable
import fr.caravellecode.brushexamples.ui.theme.MyTheme


@Preview
@Composable
private fun PickExampleButtonPreview() {
    MyTheme {
        val labels = listOf("Blend 1","Blend 2","Blend 3","Export vector imageccccc","Export raster image")

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            labels.mapIndexed { index: Int, label: String->
                PickExampleButton(id = index, onClick = {  }, totalButtons = labels.size, label = label)
            }        }
    }
}

@Preview
@Composable
private fun RasterImagePreview() {
    MyTheme {
        InputContentRasterComposable(true, true)
    }
}

@Preview
@Composable
private fun VectorImagePreview() {
    MyTheme {
        InputContentVectorComposable(true, true)
    }
}


@Preview
@Composable
private fun ExampleCaptureImagePreview() {
    MyTheme {
        ExampleExportImage(content = { InputContentVectorComposable(true, true) })
    }
}
