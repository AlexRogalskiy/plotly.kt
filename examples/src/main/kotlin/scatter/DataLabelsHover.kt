package scatter

import scientifik.plotly.Plotly
import scientifik.plotly.layout
import scientifik.plotly.makeFile
import scientifik.plotly.models.TraceMode
import scientifik.plotly.models.TraceType
import scientifik.plotly.trace

fun main() {
    val plot = Plotly.plot2D {
        trace {
            x(1, 2, 3, 4)
            y(10, 15, 13, 17)
            mode = TraceMode.markers
            type = TraceType.scatter
            name = "Team A"
            text = listOf("A-1", "A-2", "A-3", "A-4", "A-5")
            marker { size = 12 }
        }
        trace {
            x(2, 3, 4, 5)
            y(10, 15, 13, 17)
            mode = TraceMode.lines
            type = TraceType.scatter
            name = "Team B"
            text = listOf("B-a", "B-b", "B-c", "B-d", "B-e")
            marker { size = 12 }
        }
        layout {
            title = "Data Labels Hover"
            xaxis {
                range = 0.75..5.25
            }
            yaxis {
                range = 0.0..8.0
            }
        }
    }

    plot.makeFile()
}
