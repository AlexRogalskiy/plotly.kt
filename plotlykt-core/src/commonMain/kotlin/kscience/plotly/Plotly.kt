package kscience.plotly

import hep.dataforge.meta.*
import hep.dataforge.names.toName
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlin.js.JsName

/**
 * A namespace for utility functions
 */
@JsName("PlotlyKt")
object Plotly {
    const val VERSION = "1.54.6"
    fun plot(block: Plot.() -> Unit): Plot = Plot().apply(block)
}

fun Scheme.toJson(): JsonObject = config.toJson()

/**
 * Convert any type-safe configurator to json string
 */
fun Scheme.toJsonString(): String = toJson().toString()


fun List<Scheme>.toJson(): JsonArray = jsonArray {
    forEach { +it.toJson() }
}

/**
 * Convert list of type-safe configurators to json array string
 */
fun List<Scheme>.toJsonString(): String = toJson().toString()


@RequiresOptIn("Unstable API subjected to change in future releases", RequiresOptIn.Level.WARNING)
annotation class UnstablePlotlyAPI

@RequiresOptIn("This Plotly API is not fully supported. Use it at your own risk.", RequiresOptIn.Level.ERROR)
annotation class UnsupportedPlotlyAPI

class PlotlyConfig : Scheme() {
    var editable by boolean()
    var showEditInChartStudio by boolean()
    var plotlyServerURL by string()
    var responsive by boolean()
    var imageFormat by string("toImageButtonOptions.format".toName())

    fun withEditorButton() {
        showEditInChartStudio = true
        plotlyServerURL = "https://chart-studio.plotly.com"
    }

    fun saveAsSvg(){
        imageFormat = "svg"
    }

    override fun toString(): String = toJsonString()

    companion object : SchemeSpec<PlotlyConfig>(::PlotlyConfig)
}

