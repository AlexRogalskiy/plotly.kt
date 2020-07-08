package histogram

import hep.dataforge.meta.invoke
import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.histogram
import scientifik.plotly.palettes.T10
import java.util.*

/**
 * - cumulative histogram
 * - use Tableau10 as color palette
 */
fun main() {
    val rnd = Random()
    val values = List(500){rnd.nextDouble()}

    val plot = Plotly.plot2D{
        histogram(values){
            name = "Random data"

            cumulative {
                enabled = true
            }

            marker {
                color(T10.CYAN)
            }
        }
        layout {
            title {
                text = "Cumulative Histogram"
            }
            xaxis {
                title {
                    text = "Value"
                }
            }
            yaxis {
                title {
                    text = "Sum of probabilities"
                }
            }
        }
    }

    plot.makeFile()
}