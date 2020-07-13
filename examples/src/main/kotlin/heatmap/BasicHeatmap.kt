package heatmap

import hep.dataforge.meta.invoke
import hep.dataforge.values.Value
import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.models.*


/**
 * - basic heatmap from 1 to 25
 * - change heatmap colorscale
 * - use 2D array as z
 */
fun main() {
    val x = listOf(1, 2, 3, 4, 5)
    val y = listOf(6, 7, 8, 9, 10)
    val z1 = (1..25).chunked(5)

    val heatmap = Heatmap(x, y) {
        z(z1)
        colorscale = Value.of("Reds")
    }

    val plot = Plotly.plot2D {
        traces(heatmap)
        layout {
            title {
                text = "Red Heatmap"
            }
        }
    }

    plot.makeFile()
}