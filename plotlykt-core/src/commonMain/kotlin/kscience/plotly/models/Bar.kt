package kscience.plotly.models

import hep.dataforge.meta.*
import hep.dataforge.names.asName
import kscience.plotly.numberGreaterThan

class Bar : Trace(), SelectedPoints {
    init {
        type = TraceType.bar
    }

    /**
     * Array containing integer indices of selected points. Has an effect only for traces that support selections.
     * Note that an empty array means an empty selection where the `unselected` are turned on for all points, whereas,
     * any other non-array values means no selection all where the `selected` and `unselected` styles have no effect.
     */
    override var selectedpoints by numberList()

    override var selected by spec(SelectPoints)

    override var unselected by spec(SelectPoints)

    /**
     * Sets where the bar base is drawn (in position axis units). In "stack" or "relative" barmode,
     * traces that set "base" will be excluded and drawn in "overlay" mode instead.
     */
    var base by numberList()

    /**
     * Sets the bar width (in position axis units).
     */
    var width by numberGreaterThan(0)

    /**
     * Array of numbers greater than or equal to 0. Sets the bar width (in position axis units).
     */
    var widthList by numberList(key = "width".asName())

    /**
     * Shifts the position where the bar is drawn (in position axis units). In "group" barmode,
     * traces that set "offset" will be excluded and drawn in "overlay" mode instead.
     */
    var offset by number()

    /**
     * Shifts the position where the bar is drawn (in position axis units). In "group" barmode,
     * traces that set "offset" will be excluded and drawn in "overlay" mode instead.
     */
    var offsetsList by numberList(key = "offset".asName())


    /**
     * Constrain the size of text inside or outside a bar to be no larger than the bar itself.
     */
    var constraintext by enum(ConstrainText.both)

    fun selected(block: SelectPoints.() -> Unit) {
        selected = SelectPoints(block)
    }

    fun unselected(block: SelectPoints.() -> Unit) {
        unselected = SelectPoints(block)
    }

    companion object : SchemeSpec<Bar>(::Bar)
}