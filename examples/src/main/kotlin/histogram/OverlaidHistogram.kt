package histogram

import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.models.AxisType
import scientifik.plotly.models.BarMode
import scientifik.plotly.models.Trace
import java.util.*


fun main() {
    val rnd = Random()
    val k = List(500) { rnd.nextDouble() }
    val x1 = k.map { it + 1 }.toList()
    val x2 = k.map { it + 1.1 }.toList()

    val trace1 = Trace(x1) {
        type = AxisType.histogram
        opacity = 0.5
        marker {
            color("green")
        }
    }
    val trace2 = Trace(x2) {
        type = AxisType.histogram
        opacity = 0.6
        marker {
            color("red")
        }
    }
    val plot = Plotly.plot2D {
        addTrace(trace1, trace2)
        layout {
            title = "Overlaid Histogram"
            barmode = BarMode.overlay
        }
    }


    plot.makeFile()
}