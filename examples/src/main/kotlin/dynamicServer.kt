import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import scientifik.plotly.Plotly
import scientifik.plotly.models.Trace
import scientifik.plotly.server.pushUpdates
import scientifik.plotly.server.serve
import kotlin.math.PI
import kotlin.math.sin

@KtorExperimentalAPI
@ExperimentalCoroutinesApi
fun main() {

    val server = Plotly.serve{
        val x = (0..100).map { it.toDouble() / 100.0 }
        val y = x.map { sin(2.0 * PI * it) }

        val trace = Trace.build(x = x, y = y) { name = "sin" }


        //root level plots go to default page

        plot {
            trace(trace)
            layout {
                title = "Dynamic plot"
                xaxis { title = "x axis name" }
                yaxis { title = "y axis name" }
            }
        }

        launch {
            var time: Long = 0
            while (isActive) {
                delay(10)
                time += 10
                val dynamicY = x.map { sin(2.0 * PI * (it + time.toDouble() / 1000.0)) }
                trace.y = dynamicY
            }
        }
    }.pushUpdates(100) // start sending updates via websocket to the front-end

    println("Press Enter to close server")
    readLine()

    server.stop()
}