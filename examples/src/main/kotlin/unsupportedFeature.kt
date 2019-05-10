import hep.dataforge.meta.configure
import hep.dataforge.meta.set
import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.models.Trace

fun main() {

    val x = (0..5).map { it.toDouble() }
    val y = x.map { it * it }

    val trace = Trace.build(x = x, y = y) {
        name = "sin"
        /* The hover text definition is currently not supported.
         * We are applying it directly to configuration.
         * It is still observable in the same way as other properties but is not type safe.
         */
        configure {
            set("text", x.map { "label for  $it" })
        }
    }

    val plot = Plotly.plot2D {
        trace(trace)
        layout {
            title = "Plot with labels"
            xaxis { title = "x axis name" }
            yaxis { title = "y axis name" }
        }
    }

    plot.makeFile()
}
