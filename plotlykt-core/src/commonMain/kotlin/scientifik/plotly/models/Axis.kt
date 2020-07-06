package scientifik.plotly.models

import hep.dataforge.meta.*
import hep.dataforge.names.asName
import hep.dataforge.values.ListValue
import hep.dataforge.values.asValue
import hep.dataforge.values.doubleArray
import kotlin.js.JsName


enum class AxisType {
    @JsName("default")
    `-`,
    linear,
    log,
    date,
    category,
    multicategory;
}

class Axis : Scheme() {
    /**
     * Sets the title of this axis.
     */
    var title by string()

    /**
     * Enumerated, one of ( "-" | "linear" | "log" | "date" | "category" | "multicategory" ) .
     * Sets the axis type. By default, plotly attempts to determined
     * the axis type by looking into the data of the traces
     * that referenced the axis in question.
     * Default: "-"
     */
    var type by enum(AxisType.`-`)

    /**
     * A single toggle to hide the axis while preserving interaction
     * like dragging. Default is true when a cheater plot
     * is present on the axis, otherwise false
     */
    var visible by boolean()

    /**
     * Determines whether or not the range of this axis is computed
     * in relation to the input data. See `rangemode` for more info.
     * If `range` is provided, then `autorange` is set to "false".
     * Default: true.
     */
    var autorange by boolean(true)

    /**
     * Sets the range of this axis. If the axis `type` is "log",
     * then you must take the log of your desired range
     * (e.g. to set the range from 1 to 100, set the range from 0 to 2).
     * If the axis `type` is "date", it should be date strings, like date data,
     * though Date objects and unix milliseconds will be accepted and
     * converted to strings. If the axis `type` is "category", it should be
     * numbers, using the scale where each category is assigned
     * a serial number from zero in the order it appears.
     */
    var range: ClosedFloatingPointRange<Double>?
        get() = config["range"]?.value?.doubleArray?.let { it[0]..it[1] }
        set(value) {
            config["range"] = value?.let { ListValue(listOf(value.start.asValue(), value.endInclusive.asValue())) }
        }

    /**
     * Sets default for all colors associated with this axis all at once:
     * line, font, tick, and grid colors. Grid color is lightened
     * by blending this with the plot background Individual pieces can override this.
     * Default: #444.
     */
    val color = Color(this, "color".asName())

    companion object : SchemeSpec<Axis>(::Axis)
}