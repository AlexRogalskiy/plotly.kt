import hep.dataforge.meta.invoke
import scientifik.plotly.Plotly
import scientifik.plotly.UnstablePlotlyAPI
import scientifik.plotly.models.Trace
import scientifik.plotly.show
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@UnstablePlotlyAPI
fun main() {

    val x = (0..100).map { it.toDouble() / 100.0 }
    val y1 = x.map { sin(2.0 * PI * it) }
    val y2 = x.map { cos(2.0 * PI * it) }

    val trace1 = Trace(x, y1) { name = "sin" }
    val trace2 = Trace(x, y2) { name = "cos" }


    val plot = Plotly.grid {
        title = "Page sample"
        plot(row = 1, width = 8) {
            addTrace(trace1, trace2)
            layout {
                title = "First graph, row: 1, size: 8/12"
                xaxis { title = "x axis name" }
                yaxis { title = "y axis name" }
            }
        }

        plot(row = 1, width = 4) {
            addTrace(trace1, trace2)
            layout {
                title = "Second graph, row: 1, size: 4/12"
                xaxis { title = "x axis name" }
                yaxis { title = "y axis name" }
            }
        }

        plot(row = 2, width = 12) {
            addTrace(trace1, trace2)
            layout {
                title = "Third graph, row: 2, size: 12/12"
                xaxis { title = "x axis name" }
                yaxis { title = "y axis name" }
            }
        }
    }

    plot.show()
}
