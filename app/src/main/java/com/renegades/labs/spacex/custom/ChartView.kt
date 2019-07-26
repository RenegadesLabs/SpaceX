package com.renegades.labs.spacex.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.renegades.labs.spacex.R


class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyle, defStyleRes), UpdatesListener {

    var adapter: ChartAdapter? = null
        set(value) {
            field = value
            field?.subscribeToUpdates(this)
        }

    private val textPaint: TextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val chartPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val chartPath: Path = Path()
    private var tooltipText: String? = null
    private var tooltipPosition: Pair<Float, Float>? = null

    private val myListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            val point = getRelativePosition(this@ChartView, e)
            var x = point.x / X_STEP_WIDTH_DP.dpToPx().toInt()
            adapter?.run {
                if (x == getData().size) {
                    x--
                }

                val value = getData().toList()[x].second
                tooltipText = "$value"

                val textWidth = textPaint.measureText(tooltipText)
                val tooltipX = ((X_STEP_WIDTH_DP * 0.5f) + (X_STEP_WIDTH_DP * x)).dpToPx() - (textWidth * 0.5f)
                val tooltipY = getTooltipY(this, value)
                tooltipPosition = Pair(tooltipX, tooltipY)

                invalidate()
            }
            return true
        }

        private fun getTooltipY(adapter: ChartAdapter, value: Int): Float {
            val textBaseline = height - X_LABEL_BASELINE_HEIGHT_DP.dpToPx()
            val maxValue = getMaxValue(adapter.getData())
            val yStep = getYStep(textBaseline, maxValue)
            return (maxValue - value) * yStep + TOOLTIP_TOP_OFFSET_DP.dpToPx()
        }
    }

    private val detector: GestureDetector = GestureDetector(context, myListener)

    init {
        textPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        textPaint.textSize = X_LABEL_TEXT_SIZE * resources.displayMetrics.scaledDensity

        chartPaint.color = ContextCompat.getColor(context, R.color.secondaryLightColor)
        chartPaint.strokeWidth = CHART_STROKE_WIDTH.dpToPx()
        chartPaint.style = Paint.Style.STROKE
        val radius = 50.0f
        val corEffect = CornerPathEffect(radius)
        chartPaint.pathEffect = corEffect
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = X_STEP_WIDTH_DP.dpToPx() * (adapter?.getData()?.entries?.size ?: 1)
        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)

        val desiredHeight = CHART_HEIGHT_DP.dpToPx()
        val height = resolveSize(desiredHeight.toInt(), heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val textBaseline = height - X_LABEL_BASELINE_HEIGHT_DP.dpToPx()

        adapter?.getData()?.let { map ->
            map.keys.forEachIndexed { index, month ->
                val centerX = ((X_STEP_WIDTH_DP * 0.5f) + (X_STEP_WIDTH_DP * index)).dpToPx()
                val textWidth = textPaint.measureText(month)
                val textX = centerX - (textWidth * 0.5f)

                canvas.drawText(month, textX, textBaseline, textPaint)
            }

            val maxValue = getMaxValue(map)
            val yStep = getYStep(textBaseline, maxValue)

            chartPath.reset()
            map.values.forEachIndexed { index, value ->
                val y = (maxValue - value) * yStep + CHART_TOP_OFFSET_DP.dpToPx()
                val x = ((X_STEP_WIDTH_DP * 0.5f) + (X_STEP_WIDTH_DP * index)).dpToPx()
                if (index == 0) {
                    chartPath.moveTo(x, y)
                } else {
                    chartPath.lineTo(x, y)
                }
            }

            canvas.drawPath(chartPath, chartPaint)

            tooltipText?.let { text ->
                tooltipPosition?.let { pos ->
                    canvas.drawText(text, pos.first, pos.second, textPaint)
                    tooltipText = null
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return detector.onTouchEvent(event)
    }

    override fun onListUpdate() {
        invalidate()
        requestLayout()
    }

    private fun getRelativePosition(v: View, event: MotionEvent): Point {
        val location = IntArray(2)
        v.getLocationOnScreen(location)
        val screenX = event.rawX
        val screenY = event.rawY
        val viewX = screenX - location[0]
        val viewY = screenY - location[1]
        return Point(viewX.toInt(), viewY.toInt())
    }

    private fun getYStep(textBaseline: Float, maxValue: Int) =
        (textBaseline - CHART_BOTTOM_OFFSET_DP.dpToPx()) / (maxValue - 1)

    private fun getMaxValue(map: Map<String, Int>) = map.values.max() ?: 0

    private fun Float.dpToPx(): Float {
        return this * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    abstract class ChartAdapter {

        protected var listener: UpdatesListener? = null

        fun subscribeToUpdates(listener: UpdatesListener) {
            this.listener = listener
        }

        abstract fun getData(): Map<String, Int>

    }

    companion object {
        private const val CHART_HEIGHT_DP = 400f
        private const val X_STEP_WIDTH_DP = 100f
        private const val X_LABEL_BASELINE_HEIGHT_DP = 8f
        private const val X_LABEL_TEXT_SIZE = 14f
        private const val TOOLTIP_TOP_OFFSET_DP = 16f
        private const val CHART_BOTTOM_OFFSET_DP = 96f
        private const val CHART_TOP_OFFSET_DP = 32f
        private const val CHART_STROKE_WIDTH = 4f
    }
}

interface UpdatesListener {

    fun onListUpdate()

}