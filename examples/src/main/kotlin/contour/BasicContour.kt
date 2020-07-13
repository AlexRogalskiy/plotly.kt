package contour

import hep.dataforge.meta.invoke
import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.models.MeasureMode
import scientifik.plotly.models.Contour


/**
 * - basic contour plot
 * - change font size of labels ticks
 * - setting x and y coordinates
 * - change color bar size
 */
fun main() {
    val values = listOf(10, 10.625, 12.5, 15.625, 20.0,
        5.625, 6.25, 8.125, 11.25, 15.625,
        2.5, 3.125, 5.0, 8.125, 12.5,
        0.625, 1.25, 3.125, 6.25, 10.625,
        0.0, 0.625, 2.5, 5.625, 10).chunked(5)
    val x = listOf(-9, -6, -5 , -3, -1)
    val y = listOf(0, 1, 4, 5, 7)

    val contour = Contour(x, y) {
        z(values)

        colorbar {
            thickness = 75.0
            thicknessmode = MeasureMode.pixels
            len = 0.9
            lenmode = MeasureMode.fraction
            outlinewidth = 0
        }
    }

    val plot = Plotly.plot2D {
        traces(contour)

        layout {
            title {
                text = "Basic Contour Plot"
            }
            xaxis {
                tickfont {
                    size = 16
                }
            }
            yaxis {
                tickfont {
                    size = 16
                }
            }
        }
    }

    plot.makeFile()
}