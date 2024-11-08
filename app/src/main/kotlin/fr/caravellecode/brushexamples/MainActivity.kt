package fr.caravellecode.brushexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.caravellecode.brushexamples.captureImage.ExampleExportImage
import fr.caravellecode.brushexamples.captureImage.InputContentRasterComposable
import fr.caravellecode.brushexamples.captureImage.InputContentVectorComposable
import fr.caravellecode.brushexamples.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var pickExample by remember {
                        mutableIntStateOf(-1)
                    }

                    Column {
                        val labels = listOf("Export vector image", "Export raster image")

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            labels.mapIndexed { index: Int, label: String ->
                                PickExampleButton(
                                    id = index,
                                    onClick = { pickExample = it },
                                    totalButtons = labels.size,
                                    label = label
                                )
                            }
                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                        when (pickExample) {
                            0 -> ExampleExportImage(content = { addBlendedMotif: Boolean ->
                                InputContentVectorComposable(
                                    drawObjectFirst = true,
                                    addBlendedMotif = addBlendedMotif
                                )
                            })

                            1 -> ExampleExportImage(content = { addBlendedMotif: Boolean ->
                                InputContentRasterComposable(
                                    drawObjectFirst = false,
                                    addBlendedMotif = addBlendedMotif
                                )
                            })

                            else -> ShowText()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.PickExampleButton(
    modifier: Modifier = Modifier,
    label: String,
    id: Int,
    totalButtons: Int,
    onClick: (Int) -> Unit,
) {

    OutlinedButton(
        modifier = Modifier
            .padding(4.dp)
            .weight(weight = (1f / totalButtons)),
        onClick = { onClick(id) },
        contentPadding = PaddingValues(4.dp),
        shape = MaterialTheme.shapes.extraSmall,
    ) {
        Text(modifier = Modifier.fillMaxWidth(), text = label, textAlign = TextAlign.Center)
    }
}

@Composable
fun ShowText() {
    Text(text = "Click on a button to see example")
}
