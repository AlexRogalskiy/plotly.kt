package heatmap

import scientifik.plotly.Plotly
import scientifik.plotly.models.Heatmap
import scientifik.plotly.show
import scientifik.plotly.*


/**
 * - heatmaps with and without gaps between not-null values
 * - change plot size
 * - stack two plots
 */
fun main() {
    val x1 = (0..7)
    val y2 = (0..6)

    val z1 = listOf(null, null, null, 12, 13, 14, 15, 16,
            null, 1, null, 11, null, null, null, 17,
            null, 2, 6, 7, null, null, null, 18,
            null, 3, null, 8, null, null, null, 19,
            5, 4, 10, 9, null, null, null, 20,
            null, null, null, 27, null, null, null, 21,
            null, null, null, 26, 25, 24, 23, 22).chunked(8)

    val heatmap1 = Heatmap {
        x.set(x1)
        y.set(y2)
        z.set(z1)
        showscale = false
    }

    val heatmap2 = Heatmap {
        x.set(x1)
        y.set(y2)
        z.set(z1)
        showscale = false
        connectgaps = true
    }

    Plotly.show {
        staticPlot {
            traces(heatmap1)
            layout {
                width = 800
                height = 450
                title {
                    text = "Gaps Between Nulls"
                }
            }
        }

        staticPlot {
            traces(heatmap2)
            layout {
                width = 800
                height = 450
                title {
                    text = "Connected Gaps"
                }
            }
        }
    }
}
