package scientifik.plotly.models

import hep.dataforge.meta.*
import hep.dataforge.names.asName
import hep.dataforge.values.DoubleArrayValue
import hep.dataforge.values.Value
import hep.dataforge.values.asValue
import hep.dataforge.values.doubleArray
import scientifik.plotly.doubleGreaterThan
import scientifik.plotly.doubleInRange
import scientifik.plotly.intGreaterThan
import kotlin.js.JsName


enum class TraceMode {
    lines,

    @JsName("linesMarkers")
    `lines+markers`,
    markers
}

enum class TraceType {
    // Simple
    scatter,
    scattergl,
    bar,
    pie,
    heatmap,
    contour,
    table,

    // Distributions
    box,
    violin,
    histogram,
    histogram2d,
    histogram2dcontour,
//    // Finance
//    ohlc,
//    candlestick,
//    waterfall,
//    // 3D
//    scatter3d,
//    surface,
//    mesh3d,
//    cone,
//    streamtube,
//    volume,
//    isosurface,
//    // Maps
//    scattergeo,
//    choropleth,
//    scattermapbox
//    // Specialized
}

enum class Visible {
    @JsName("true")
    True,

    @JsName("false")
    False,
    legendonly
}

enum class Symbol {
    circle,

    @JsName("triangleUp")
    `triangle-up`,

    @JsName("triangleDown")
    `triangle-down`,

    @JsName("squareCross")
    `square-cross`,

    @JsName("crossThin")
    `cross-thin`,
    cross
}

enum class SizeMode {
    diameter,
    area
}

class MarkerLine : Line() {
    /**
     * Number than or equal to 0. Sets the width (in px)
     * of the lines bounding the marker points.
     */
    var width by intGreaterThan(0)

    /**
     * Sets themarker.linecolor. It accepts either a specific color
     * or an array of numbers that are mapped to the colorscale
     * relative to the max and min values of the array or relative to
     * `cmin` and `cmax` if set.
     */
    val color = Color(this, "color".asName())

    /**
     * Determines whether or not the color domain is computed with respect
     * to the input data (here in `color`) or the bounds set in `cmin` and `cmax`
     * Has an effect only if in `color` is set to a numerical array.
     * Defaults to `false` when `cmin` and `cmax` are set by the user.
     *
     */
    var cauto by boolean()

    /**
     * Sets the lower bound of the color domain. Has an effect only if
     * in `color`is set to a numerical array. Value should have
     * the same units as in `color` and if set, `cmax` must be set as well.
     */
    var cmin by int()

    /**
     * Sets the upper bound of the color domain. Has an effect only if
     * in `color`is set to a numerical array. Value should have
     * the same units as in `color` and if set, `cmin` must be set as well.
     */
    var cmax by int()

    /**
     * Sets the mid-point of the color domain by scaling `cmin` and/or `cmax`
     * to be equidistant to this point. Has an effect only if in `color`
     * is set to a numerical array. Value should have the same units
     * as in `color`. Has no effect when `cauto` is `false`.
     */
    var cmid by int()

    /**
     * Sets the colorscale. Has an effect only if in `color`is set to a numerical array.
     * The colorscale must be an array containing arrays mapping a normalized value
     * to an rgb, rgba, hex, hsl, hsv, or named color string. At minimum,
     * a mapping for the lowest (0) and highest (1) values are required. For example,
     * `[[0, 'rgb(0,0,255)'], [1, 'rgb(255,0,0)']]`. To control the bounds of the colorscale
     * in color space, use `cmin` and `cmax`. Alternatively, `colorscale` may be
     * a palette name string of the following list: Greys, YlGnBu, Greens, YlOrRd,
     * Bluered, RdBu, Reds, Blues, Picnic, Rainbow, Portland, Jet, Hot, Blackbody,
     * Earth, Electric, Viridis, Cividis.
     */
    var colorscale by value() // TODO()

    /**
     * Determines whether the colorscale is a default palette (`true`) or
     * the palette determined by `colorscale`. Has an effect only if
     * in `color` is set to a numerical array. In case `colorscale`
     * is unspecified or `autocolorscale` is true, the default palette
     * will be chosen according to whether numbers in the `color` array
     * are all positive, all negative or mixed.
     * Default: true.
     */
    var autocolorscale by boolean() // TODO("colorscale first!")

    /**
     * Reverses the color mapping if true. Has an effect only if
     * in `color` is set to a numerical array. If true, `cmin`
     * will correspond to the last color in the array and `cmax`
     * will correspond to the first color.
     */
    var reversescale by boolean() // TODO("colorscale first!")

    /**
     * Sets a reference to a shared color axis. References to these
     * shared color axes are "coloraxis", "coloraxis2", "coloraxis3", etc.
     * Settings for these shared color axes are set in the layout,
     * under `coloraxis`, `coloraxis2`, etc. Note that multiple
     * color scales can be linked to the same color axis.
     */
    // var coloraxis TODO()

    companion object : SchemeSpec<MarkerLine>(::MarkerLine)
}

enum class TextPosition {
    topLeft,
    topCenter,
    topRight,
    middleLeft,
    middleCenter,
    middleRight,
    bottomLeft,
    bottomCenter,
    bottomRight,
}

class Font : Scheme() {
    /**
     * HTML font family - the typeface that will be applied
     * by the web browser. The web browser will only be able
     * to apply a font if it is available on the system
     * which it operates. Provide multiple font families,
     * separated by commas, to indicate the preference
     * in which to apply fonts if they aren't available
     * on the system. The Chart Studio Cloud (at https://chart-studio.plotly.com or on-premise)
     * generates images on a server, where only a select number
     * of fonts are installed and supported. These include "Arial",
     * "Balto", "Courier New", "Droid Sans",, "Droid Serif",
     * "Droid Sans Mono", "Gravitas One", "Old Standard TT",
     * "Open Sans", "Overpass", "PT Sans Narrow", "Raleway", "Times New Roman".
     */
    var family by string()

    var size by intGreaterThan(1)

    val color = Color(this, "color".asName())

    companion object : SchemeSpec<Font>(::Font)
}

enum class Direction {
    increasing,
    decreasing
}

enum class CurrentBin {
    include,
    exclude,
    half
}

class Cumulative : Scheme() {
    /**
     * If true, display the cumulative distribution by summing
     * the binned values. Use the `direction` and `centralbin`
     * attributes to tune the accumulation method. Note: in this mode,
     * the "density" `histnorm` settings behave the same
     * as their equivalents without "density": "" and "density"
     * both rise to the number of data points, and "probability"
     * and "probability density" both rise to the number of sample points.
     */
    var enabled by boolean()

    /**
     * Enumerated , one of ( "increasing" | "decreasing" )
     * Only applies if cumulative is enabled. If "increasing" (default)
     * we sum all prior bins, so the result increases from left to right.
     * If "decreasing" we sum later bins so the result decreases from left to right.
     * Default: increasing.
     */
    var direction by enum(Direction.increasing)

    /**
     * Enumerated , one of ( "include" | "exclude" | "half" )
     * Only applies if cumulative is enabled. Sets whether the current bin
     * is included, excluded, or has half of its value included in
     * the current cumulative value. "include" is the default for
     * compatibility with various other tools, however it introduces
     * a half-bin bias to the results. "exclude" makes the opposite
     * half-bin bias, and "half" removes it.
     * Default: include.
     */
    var currentbin by enum(CurrentBin.include)

    companion object : SchemeSpec<Cumulative>(::Cumulative)
}

enum class HistNorm {
    empty,
    percent,
    probability,
    density,
    probability_density
}

enum class HistFunc {
    count,
    sum,

    @JsName("avg")
    average,
    min,
    max
}


class Bins : Scheme() {
    //FIXME("add categorical coordinate string")

    /**
     * Sets the starting value for the x axis bins.
     * Defaults to the minimum data value, shifted down
     * if necessary to make nice round values and to remove
     * ambiguous bin edges. For example, if most of the data
     * is integers we shift the bin edges 0.5 down, so a `size`
     * of 5 would have a default `start` of -0.5, so it is clear
     * that 0-4 are in the first bin, 5-9 in the second, but
     * continuous data gets a start of 0 and bins [0,5), [5,10) etc.
     * Dates behave similarly, and `start` should be a date string.
     * For category data, `start` is based on the category
     * serial numbers, and defaults to -0.5.
     */
    var start by double()

    /**
     * Sets the end value for the x axis bins. The last bin may
     * not end exactly at this value, we increment the bin edge
     * by `size` from `start` until we reach or exceed `end`.
     * Defaults to the maximum data value. Like `start`, for
     * dates use a date string, and for category data `end`
     * is based on the category serial numbers.
     */
    var end by double()

    /**
     * Sets the size of each x axis bin. Default behavior:
     * If `nbinsx` is 0 or omitted, we choose a nice round
     * bin size such that the number of bins is about
     * the same as the typical number of samples in each bin.
     * If `nbinsx` is provided, we choose a nice round bin size
     * giving no more than that many bins. For date data,
     * use milliseconds or "M<n>" for months, as in `axis.dtick`.
     * For category data, the number of categories to bin together
     * (always defaults to 1).
     */
    var size by doubleGreaterThan(0.0)

    companion object : SchemeSpec<Bins>(::Bins)
}

class Title : Scheme() {
    /**
     * Sets the plot's title.
     */
    var text by string()

    /**
     * Sets the title font.
     */
    var font by spec(Font)

    fun font(block: Font.() -> Unit) {
        font = Font(block)
    }

    companion object : SchemeSpec<Title>(::Title)
}

enum class ErrorType {
    percent,
    constant,
    sqrt,
    data
}

class Error : Scheme() {
    /**
     * Determines whether or not this set of error bars is visible.
     */
    var visible by boolean()

    /**
     * Enumerated , one of ("percent" | "constant" | "sqrt" | "data")
     * Determines the rule used to generate the error bars.
     * If "constant`, the bar lengths are of a constant value.
     * Set this constant in `value`. If "percent", the bar lengths correspond
     * to a percentage of underlying data. Set this percentage in `value`.
     * If "sqrt", the bar lengths correspond to the square of the underlying data.
     * If "data", the bar lengths are set with data set `array`.
     * Default: constant.
     */
    var type by enum(ErrorType.constant)

    /**
     * Determines whether or not the error bars have the same length in both
     * direction (top/bottom for vertical bars, left/right for horizontal bars.
     */
    var symmetric by boolean()

    /**
     * Sets the data corresponding the length of each error bar.
     * Values are plotted relative to the underlying data.
     */
    var array by numberList()

    /**
     * Sets the data corresponding the length of each error bar
     * in the bottom (left) direction for vertical (horizontal) bars.
     * Values are plotted relative to the underlying data.
     */
    var arrayminus by numberList()

    /**
     * Number greater than or equal to 0.
     * Sets the value of either the percentage (if `type` is set to "percent")
     * or the constant (if `type` is set to "constant")
     * corresponding to the lengths of the error bars.
     * Default: 10.
     */
    var value by doubleGreaterThan(0.0)

    /**
     * Number greater than or equal to 0.
     * Sets the value of either the percentage (if `type` is set to "percent")
     * or the constant (if `type` is set to "constant")
     * corresponding to the lengths of the error bars in the bottom (left)
     * direction for vertical (horizontal) bars.
     * Default: 10.
     */
    var valueminus by doubleGreaterThan(0.0)

    /**
     * Sets the stoke color of the error bars.
     */
    var color = Color(this, "color".asName())

    /**
     * Sets the thickness (in px) of the error bars.
     * Default: 2.
     */
    var thickness by intGreaterThan(0)

    /**
     * Sets the width (in px) of the cross-bar at both ends of the error bars.
     */
    var width by intGreaterThan(0)

    /**
     * Integer greater than or equal to 0.
     * Default: 0.
     */
    var traceref by intGreaterThan(0)

    /**
     * Integer greater than or equal to 0.
     * Default: 0.
     */
    var tracerefminus by intGreaterThan(0)

    companion object : SchemeSpec<Error>(::Error)
}

class Trace() : Scheme() {
    fun axis(axisName: String) = TraceValues(this, axisName)

    /**
     * Sets the x coordinates.
     */
    val x = axis(X_AXIS)

    /**
     * Sets the y coordinates.
     */
    val y = axis(Y_AXIS)

    /**
     * Sets the trace name. The trace name appear as the legend item and on hover.
     */
    var name by string()

    /**
     * Flaglist string. Any combination of "lines", "markers", "text"
     * joined with a "+" OR "none". Determines the drawing mode for
     * this scatter trace. If the provided `mode` includes "text" then
     * the `text` elements appear at the coordinates. Otherwise,
     * the `text` elements appear on hover.
     * Default: lines.
     */
    var mode by enum(TraceMode.lines)

    var type by enum(TraceType.scatter)

    /**
     * Enumerated , one of ( true | false | "legendonly" )
     * Determines whether or not this trace is visible.
     * If "legendonly", the trace is not drawn, but can appear
     * as a legend item (provided that the legend itself is visible).
     * Default: true.
     */
    var visible by enum(Visible.True)

    /**
     * Determines whether or not an item corresponding to this trace is shown in the legend.
     * Default: true
     */
    var showlegend by boolean()

    /**
     * Sets the legend group for this trace. Traces part of
     * the same legend group hide/show at the same time when toggling legend items.
     * Default: ""
     */
    var legendgroup by string()

    /**
     * Sets the opacity of the trace.
     * Default: 1.
     */
    var opacity by doubleInRange(0.0..1.0)


    var cumulative by spec(Cumulative)

    // val autobinx by boolean() is not needed
    /**
     * Enumerated, one of ( "empty" | "percent" | "probability" | "density" | "probability density" )
     * Specifies the type of normalization used for this histogram trace.
     * If "empty", the span of each bar corresponds to the number of occurrences
     * (i.e. the number of data points lying inside the bins). If "percent" / "probability",
     * the span of each bar corresponds to the percentage / fraction of occurrences
     * with respect to the total number of sample points (here, the sum of all
     * bin HEIGHTS equals 100% / 1). If "density", the span of each bar corresponds
     * to the number of occurrences in a bin divided by the size of the bin interval
     * (here, the sum of all bin AREAS equals the total number of sample points).
     * If "probability density", the area of each bar corresponds to the probability
     * that an event will fall into the corresponding bin (here, the sum of all bin AREAS equals 1).
     * Default: "empty".
     */
    var histnorm by enum(HistNorm.empty)

    /**
     * Enumerated , one of ( "count" | "sum" | "avg" | "min" | "max" )
     * Specifies the binning function used for this histogram trace.
     * If "count", the histogram values are computed by counting the
     * number of values lying inside each bin. If "sum", "avg", "min", "max",
     * the histogram values are computed using the sum, the average,
     * the minimum or the maximum of the values lying inside each bin respectively.
     * Default: "count"
     */
    var histfunc by enum(HistFunc.count)

    var xbins by spec(Bins)

    var ybins by spec(Bins)

    //    var line by spec(Line)
    var marker by spec(Marker)

    /**
     * Sets text elements associated with each (x,y) pair.
     * If a single string, the same string appears over
     * all the data points. If an array of string, the items
     * are mapped in order to the this trace's (x,y) coordinates.
     * If trace `hoverinfo` contains a "text" flag and "hovertext" is not set,
     * these elements will be seen in the hover labels.
     */
    var text by stringList()

    /**
     * Sets the positions of the `text` elements
     * with respects to the (x,y) coordinates.
     * Default: "middle center".
     */
    var textposition by enum(TextPosition.middleCenter)

    /**
     * Sets the text font.
     */
    var textfont by spec(Font)

    var error_x by spec(Error)

    var error_y by spec(Error)

    fun textfont(block: Font.() -> Unit) {
        textfont = Font(block)
    }

    fun marker(block: Marker.() -> Unit) {
        marker = Marker(block)
    }

    fun cumulative(block: Cumulative.() -> Unit) {
        cumulative = Cumulative(block)
    }

    fun xbins(block: Bins.() -> Unit) {
        xbins = Bins(block)
    }

    fun ybins(block: Bins.() -> Unit) {
        ybins = Bins(block)
    }

    fun error_x(block: Error.() -> Unit) {
        error_x = Error(block)
    }

    fun error_y(block: Error.() -> Unit) {
        error_y = Error(block)
    }

    companion object : SchemeSpec<Trace>(::Trace) {
        const val X_AXIS = "x"
        const val Y_AXIS = "y"
        const val TEXT_AXIS = "text"

        operator fun invoke(xs: Any, ys: Any? = null/*, zs: Any? = null*/, block: Trace.() -> Unit = {}) = invoke {
            block()
            x.set(xs)
            y.set(ys)
        }
    }
}