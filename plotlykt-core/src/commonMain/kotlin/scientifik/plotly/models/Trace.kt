package scientifik.plotly.models

import hep.dataforge.meta.*
import hep.dataforge.names.asName
import hep.dataforge.values.Value
import hep.dataforge.values.asValue
import scientifik.plotly.*
import kotlin.js.JsName


enum class TraceType {
    // Simple
    scatter,
    scattergl,
    bar,
    pie,
    heatmap,
    heatmapgl,
    contour,

    @UnsupportedPlotlyAPI
    table,

    @UnsupportedPlotlyAPI
    image,

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
    `true`,
    `false`,
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
    cross,

    @JsName("lineNs")
    `line-ns`
}

enum class SizeMode {
    diameter,
    area
}

class MarkerLine : Scheme(), Line {
    /**
     * Number greater than or equal to 0. Sets the width (in px)
     * of the lines bounding the marker points.
     */
    override var width by numberGreaterThan(0)

    /**
     * Array of numbers greater than or equal to 0. Sets the width (in px)
     * of the lines bounding the marker points.
     */
    override var widthList by numberList(key = "width".asName())

    /**
     * Sets themarker.linecolor. It accepts either a specific color
     * or an array of numbers that are mapped to the colorscale
     * relative to the max and min values of the array or relative to
     * `cmin` and `cmax` if set.
     */
    override val color = Color(this, "color".asName())

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
    var cmin by number()

    /**
     * Sets the upper bound of the color domain. Has an effect only if
     * in `color`is set to a numerical array. Value should have
     * the same units as in `color` and if set, `cmin` must be set as well.
     */
    var cmax by number()

    /**
     * Sets the mid-point of the color domain by scaling `cmin` and/or `cmax`
     * to be equidistant to this point. Has an effect only if in `color`
     * is set to a numerical array. Value should have the same units
     * as in `color`. Has no effect when `cauto` is `false`.
     */
    var cmid by number()

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

    /**
     * Sets the border line color of the outlier sample points. Defaults to marker.color
     */
    var outliercolor = Color(this, "outliercolor".asName())

    /**
     * Sets the border line width (in px) of the outlier sample points.
     * Default: 1
     */
    var outlierwidth by numberGreaterThan(0)

    fun colors(colors: Iterable<Any>) {
        color.value = colors.map { Value.of(it) }.asValue()
    }

    companion object : SchemeSpec<MarkerLine>(::MarkerLine)
}

enum class TextPosition {
    @JsName("topLeft")
    `top left`,

    @JsName("topCenter")
    `top center`,

    @JsName("topRight")
    `top right`,

    @JsName("middleLeft")
    `middle left`,

    @JsName("middleCenter")
    `middle center`,

    @JsName("middleRight")
    `middle right`,

    @JsName("bottomLeft")
    `bottom left`,

    @JsName("bottomCenter")
    `bottom center`,

    @JsName("bottomRight")
    `bottom right`,

    // pie
    inside,
    outside,
    auto,
    none
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

    /**
     * HTML font family
     */
    var familiesList by stringList(key = "family".asName())

    var size by numberGreaterThan(1)

    var sizesList by numberList(key = "size".asName())

    val color = Color(this, "color".asName())

    fun colors(array: Iterable<Any>) {
        color.value = array.map { Value.of(it) }.asValue()
    }

    companion object : SchemeSpec<Font>(::Font)
}

enum class Ref {
    container,
    paper
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

    /**
     * Sets the container `x` refers to. "container" spans the entire `width` of the plot.
     * "paper" refers to the width of the plotting area only. Default: container.
     */
    var xref by enum(Ref.container)

    /**
     * Sets the container `y` refers to. "container" spans the entire `height` of the plot.
     * "paper" refers to the height of the plotting area only. Default: container.
     */
    var yref by enum(Ref.container)

    /**
     * Sets the x position with respect to `xref` in normalized coordinates from "0" (left) to "1" (right).
     * Default: 0.5
     */
    var x by numberInRange(0.0..1.0)

    /**
     * Sets the y position with respect to `yref` in normalized coordinates from "0" (bottom) to "1" (top).
     * "auto" places the baseline of the title onto the vertical center of the top margin. Default: auto.
     */
    var y by numberInRange(0.0..1.0)

    /**
     * Sets the title's horizontal alignment with respect to its x position. "left" means that the title starts at x,
     * "right" means that the title ends at x and "center" means that the title's center is at x.
     * "auto" divides `xref` by three and calculates the `xanchor` value automatically based on the value of `x`.
     */
    var xanchor by enum(XAnchor.auto)

    /**
     * Sets the title's vertical alignment with respect to its y position. "top" means that the title's cap line is at y,
     * "bottom" means that the title's baseline is at y and "middle" means that the title's midline is at y.
     * "auto" divides `yref` by three and calculates the `yanchor` value automatically based on the value of `y`.
     */
    var yanchor by enum(YAnchor.auto)

    /**
     * Sets the padding of the title. Each padding value only applies when the corresponding `xanchor`/`yanchor`
     * value is set accordingly. E.g. for left padding to take effect, `xanchor` must be set to "left".
     * The same rule applies if `xanchor`/`yanchor` is determined automatically. Padding is muted if
     * the respective anchor value is "middle"/"center".
     */
    var pad by spec(Margin)

    fun font(block: Font.() -> Unit) {
        font = Font(block)
    }

    fun pad(block: Margin.() -> Unit) {
        pad = Margin(block)
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
    var value by numberGreaterThan(0)

    /**
     * Number greater than or equal to 0.
     * Sets the value of either the percentage (if `type` is set to "percent")
     * or the constant (if `type` is set to "constant")
     * corresponding to the lengths of the error bars in the bottom (left)
     * direction for vertical (horizontal) bars.
     * Default: 10.
     */
    var valueminus by numberGreaterThan(0)

    /**
     * Sets the stoke color of the error bars.
     */
    var color = Color(this, "color".asName())

    /**
     * Sets the thickness (in px) of the error bars.
     * Default: 2.
     */
    var thickness by numberGreaterThan(0)

    /**
     * Sets the width (in px) of the cross-bar at both ends of the error bars.
     */
    var width by numberGreaterThan(0)

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

enum class MeasureMode {
    fraction,
    pixels
}

enum class ConstrainText {
    inside,
    outside,
    both,
    none
}

class ColorBar : Scheme() {
    /**
     * Determines whether this color bar's thickness (i.e. the measure
     * in the constant color direction) is set in units of plot "fraction"
     * or in "pixels" (default). Use `thickness` to set the value.
     */
    var thicknessmode by enum(MeasureMode.pixels)

    /**
     * Sets the thickness of the color bar.
     * This measure excludes the size of the padding, ticks and labels.
     * Default: 30.
     */
    var thickness by numberGreaterThan(0)

    /**
     * Determines whether this color bar's length (i.e. the measure
     * in the color variation direction) is set in units of plot
     * "fraction" (default) or in "pixels. Use `len` to set the value.
     */
    var lenmode by enum(MeasureMode.fraction)

    /**
     * Sets the length of the color bar This measure excludes
     * the padding of both ends. That is, the color bar length
     * is this length minus the padding on both ends.
     * Default: 1.
     */
    var len by numberGreaterThan(0)

    /**
     * Sets the x position of the color bar (in plot fraction).
     * Default: 1.02.
     */
    var x by numberInRange(-2.0..3.0)

    /**
     * Sets this color bar's horizontal position anchor.
     * This anchor binds the `x` position to the "left" (default),
     * "center" or "right" of the color bar.
     */
    var xanchor by enum(XAnchor.left)

    /**
     * Sets the amount of padding (in px) along the x direction.
     * Default: 10.
     */
    var xpad by numberGreaterThan(0)

    /**
     * Sets the y position of the color bar (in plot fraction).
     * Default: 0.5.
     */
    var y by numberInRange(-2.0..3.0)

    /**
     * Sets this color bar's vertical position anchor.
     * This anchor binds the `y` position to the "top",
     * "middle" (default) or "bottom" of the color bar.
     */
    var yanchor by enum(YAnchor.middle)

    /**
     * Sets the amount of padding (in px) along the y direction.
     * Default: 10.
     */
    var ypad by intGreaterThan(0)

    /**
     * Sets the axis line color.
     * Default: #444.
     */
    var bordercolor = Color(this, "bordercolor".asName())

    /**
     * Sets the width (in px) or the border enclosing this color bar.
     * Default: 0.
     */
    var borderwidth by numberGreaterThan(0)

    /**
     * Sets the color of padded area.
     * Default: rgba(0, 0, 0, 0).
     */
    var bgcolor = Color(this, "bgcolor".asName())

    var title by spec(Title)

    /**
     * Sets the width (in px) of the axis line.
     * Default: 1.
     */
    var outlinewidth by numberGreaterThan(0)

    /**
     * Sets the axis line color.
     * Default: #444.
     */
    var outlinecolor = Color(this, "outlinecolor".asName())

    /**
     * Constrain the size of text inside or outside a bar to be no larger than the bar itself.
     */
    var constraintext by enum(ConstrainText.both)

    /**
     * Sets the color bar's tick label font
     */
    var tickfont by spec(Font)

    fun title(block: Title.() -> Unit) {
        title = Title(block)
    }

    fun tickfont(block: Font.() -> Unit) {
        tickfont = Font(block)
    }

    companion object : SchemeSpec<ColorBar>(::ColorBar)
}

enum class Orientation {
    v,
    h
}

enum class ContoursType {
    levels,
    constraint
}

enum class ContoursColoring {
    fill,
    heatmap,
    lines,
    none
}

enum class Calendar {
    gregorian,
    chinese,
    coptic,
    discworld,
    ethiopian,
    hebrew,
    islamic,
    julian,
    mayan,
    nanakshahi,
    nepali,
    persian,
    jalali,
    taiwan,
    thai,
    ummalqura
}

class Domain : Scheme() {
    /**
     * Sets the horizontal domain of this pie trace (in plot fraction).
     * Default: [0, 1]
     */
    var x by numberList()

    /**
     * Sets the vertical domain of this pie trace (in plot fraction).
     * Default: [0, 1]
     */
    var y by numberList()

    /**
     * If there is a layout grid, use the domain for this row in the grid for this pie trace.
     */
    var row by intGreaterThan(0)

    /**
     * If there is a layout grid, use the domain for this column in the grid for this pie trace.
     */
    var column by intGreaterThan(0)

    companion object : SchemeSpec<Domain>(::Domain)
}


open class Trace : Scheme() {
    fun axis(axisName: String) = TraceValues(this, axisName)

    /**
     * Sets the x coordinates.
     */
    val x = axis(X_AXIS)

    /**
     * Alternate to `x`. Builds a linear space of x coordinates.
     * Use with `dx` where `x0` is the starting coordinate and `dx` the step.
     * Default: 0.
     */
    var x0 by value()

    /**
     * Sets the x coordinate step. See `x0` for more info.
     * Default: 1.
     */
    var dx by numberGreaterThan(0)

    /**
     * Sets the y coordinates.
     */
    val y = axis(Y_AXIS)

    /**
     * Z coordinates
     */
    var z = axis(Z_AXIS)

    /**
     * Alternate to `y`. Builds a linear space of y coordinates.
     * Use with `dy` where `y0` is the starting coordinate and `dy` the step.
     */
    var y0 by value()

    /**
     * Sets the y coordinate step. See `y0` for more info.
     * Default: 1.
     */
    var dy by numberGreaterThan(0)

    /**
     * Determines whether or not markers and text nodes are clipped about the subplot axes.
     * To show markers and text nodes above axis lines and tick labels, make sure
     * to set `xaxis.layer` and `yaxis.layer` to "below traces".
     */
    var cliponaxis by boolean()

    /**
     * Determines whether or not the color domain is computed with respect to
     * the input data (here in `z`) or the bounds set in `zmin` and `zmax`
     * Defaults to `false` when `zmin` and `zmax` are set by the user.
     *
     */
    var zauto by boolean()

    /**
     * Sets the lower bound of the color domain. Value should have the same units
     * as in `z` and if set, `zmax` must be set as well.
     */
    var zmin by number()

    /**
     * Sets the upper bound of the color domain. Value should have the same units
     * as in `z` and if set, `zmin` must be set as well.

     */
    var zmax by number()

    /**
     * Sets the mid-point of the color domain by scaling `zmin` and/or `zmax`
     * to be equidistant to this point. Value should have the same units as in `z`.
     * Has no effect when `zauto` is `false`.
     */
    var zmid by number()

    /**
     * Data array. Sets the values of the sectors.
     * If omitted, we count occurrences of each label.
     */
    var values by list()

    /**
     * Data array. Sets the sector labels. If `labels` entries
     * are duplicated, we sum associated `values` or simply
     * count occurrences if `values` is not provided.
     * For other array attributes (including color) we use the first
     * non-empty entry among all occurrences of the label.
     */
    var labels by list()

    var line by spec(LayoutLine)

    /**
     * Sets the colorscale. The colorscale must be an array
     * containing arrays mapping a normalized value to an rgb,
     * rgba, hex, hsl, hsv, or named color string.
     */
    var colorscale by value()

    var colorbar by spec(ColorBar)

    /**
     * Sets the fill color if `contours.type` is "constraint". Defaults to
     * a half-transparent variant of the line color, marker color, or marker line color, whichever is available.
     */
    var fillcolor = Color(this, "fillcolor".asName())

    /**
     * Sets the trace name. The trace name appear as the legend item and on hover.
     */
    var name by string()

    var type by enum(TraceType.scatter)

    /**
     * Enumerated , one of ( true | false | "legendonly" )
     * Determines whether or not this trace is visible.
     * If "legendonly", the trace is not drawn, but can appear
     * as a legend item (provided that the legend itself is visible).
     * Default: true.
     */
    var visible by enum(Visible.`true`)

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
    var opacity by numberInRange(0.0..1.0)

    //var line by spec(Line)

    var marker by lazySpec(Marker)

    /**
     * Sets text elements associated with each (x,y) pair.
     * The same string appears over all the data points.
     * If trace `hoverinfo` contains a "text" flag and "hovertext" is not set,
     * these elements will be seen in the hover labels.
     */
    var text by string()

    /**
     * Sets text elements associated with each (x,y) pair.
     * The items are mapped in order to the this trace's (x,y) coordinates.
     * If trace `hoverinfo` contains a "text" flag and "hovertext" is not set,
     * these elements will be seen in the hover labels.
     */
    var textsList by stringList(key = "text".asName())

    /**
     * Sets the position of the `text` elements
     * with respects to the (x,y) coordinates.
     * Default: "middle center".
     */
    var textposition by enum(TextPosition.`middle center`)

    /**
     * Sets the positions of the `text` elements
     * with respects to the (x,y) coordinates.
     * Default: "middle center".
     */
    var textpositionsList by list(key = "textposition".asName())

    /**
     * Sets the text font.
     */
    var textfont by spec(Font)

    /**
     * Flaglist string. Any combination of "x", "y", "z", "text", "name" joined with a "+" OR "all" or "none" or "skip".
     * Examples: "x", "y", "x+y", "x+y+z", "all", default: "all". Determines which trace information appear on hover.
     * If `none` or `skip` are set, no information is displayed upon hovering. But, if `none` is set,
     * click and hover events are still fired.
     */
    var hoverinfo by string()

    var error_x by spec(Error)

    var error_y by spec(Error)

    /**
     * Sets the orientation of the plot(s).
     * If "vertical" ("horizontal"), the data
     * is visualized along the vertical (horizontal).
     */
    var orientation by enum(Orientation.h)

    /**
     * If there are multiple violins that should be sized according to
     * some metric (see `scalemode`), link them by providing a non-empty
     * group id here shared by every trace in the same group.
     * If a violin's `width` is undefined, `scalegroup` will default
     * to the trace's name. In this case, violins with the same names
     * will be linked together. Default: ""
     */
    var scalegroup by string()

    /**
     * Determines whether or not a colorbar is displayed for this trace.
     */
    var showscale by boolean()

    /**
     * Reverses the color mapping if true. If true, `zmin` will
     * correspond to the last color in the array and `zmax` will correspond to the first color.
     */
    var reversescale by boolean()

    /**
     * Transposes the z data.
     */
    var transpose by boolean()

    /**
     * Determines whether or not gaps (i.e. {nan} or missing values) in the `z` data are filled in.
     * It is defaulted to true if `z` is a one dimensional array and `zsmooth` is not false;
     * otherwise it is defaulted to false.
     */
    var connectgaps by boolean()

    /**
     * Sets the calendar system to use with `x` date data.
     */
    var xcalendar by enum(Calendar.gregorian)

    /**
     * Sets the calendar system to use with `y` date data.
     */
    var ycalendar by enum(Calendar.gregorian)

    var domain by spec(Domain)

    fun values(array: Iterable<Any>) {
        values = array.map { Value.of(it) }
    }

    fun labels(array: Iterable<Any>) {
        labels = array.map { Value.of(it) }
    }

    fun colorbar(block: ColorBar.() -> Unit) {
        colorbar = ColorBar(block)
    }

    fun textfont(block: Font.() -> Unit) {
        textfont = Font(block)
    }

    fun marker(block: Marker.() -> Unit) {
        marker = Marker(block)
    }

    fun line(block: LayoutLine.() -> Unit) {
        line = LayoutLine(block)
    }

    fun error_x(block: Error.() -> Unit) {
        error_x = Error(block)
    }

    fun error_y(block: Error.() -> Unit) {
        error_y = Error(block)
    }

    fun domain(block: Domain.() -> Unit) {
        domain = Domain(block)
    }

    companion object : SchemeSpec<Trace>(::Trace) {
        const val X_AXIS = "x"
        const val Y_AXIS = "y"
        const val Z_AXIS = "z"
        const val TEXT_AXIS = "text"
    }
}

operator fun <T : Trace> SchemeSpec<T>.invoke(
        xs: Any,
        ys: Any? = null,
        zs: Any? = null,
        block: Trace.() -> Unit
) = invoke {
    x.set(xs)
    if (ys != null) y.set(ys)
    if (zs != null) z.set(zs)
    block()
}
