package histogram

import hep.dataforge.meta.invoke
import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.models.BarMode
import scientifik.plotly.models.Histogram
import java.util.*


fun main() {
    val rnd = Random()
    val k = List(500) { rnd.nextDouble() }
    val x1 = k.map { it }.toList()
    val x2 = k.map { it/2 }.toList()

    val trace1 = Histogram(x1) {
        opacity = 0.5
        marker {
            color("green")
        }
    }
    val trace2 = Histogram(x2) {
        opacity = 0.5
        marker {
            color("orange")
        }
    }
    val plot = Plotly.plot2D {
        addTrace(trace1, trace2)
        layout {
            title {
                text = "Stacked Histogram"
            }
            barmode = BarMode.stack
        }
    }


    plot.makeFile()
}