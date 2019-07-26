package com.renegades.labs.spacex.custom.chart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
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

    override fun onListUpdate() {
        invalidate()
        requestLayout()
    }

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
            var x = point.x / MONTH_WIDTH_DP.dpToPx().toInt()
            adapter?.run {
                if (x == getData().size) {
                    x--
                }

                val value = getData().toList()[x].second
                tooltipText = "$value"

                val textWidth = textPaint.measureText(tooltipText)

                val tooltipX = ((MONTH_WIDTH_DP * 0.5f) + (MONTH_WIDTH_DP * x)).dpToPx() - (textWidth * 0.5f)


                val textBaseline = height - 8f.dpToPx()
                val maxValue = getData().values.max() ?: 0
                val yStep = (textBaseline - 96f.dpToPx()) / (maxValue - 1)
                val tooltipY = (maxValue - value) * yStep + 16f.dpToPx()


                tooltipPosition = Pair(tooltipX, tooltipY)

                invalidate()
            }

            return true
        }
    }

    private val detector: GestureDetector = GestureDetector(context, myListener)

    init {
        textPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        textPaint.textSize = 14f * resources.displayMetrics.scaledDensity
        chartPaint.color = ContextCompat.getColor(context, R.color.secondaryLightColor)
        chartPaint.strokeWidth = 4f.dpToPx()
        chartPaint.style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = MONTH_WIDTH_DP.dpToPx() *
                (adapter?.getData()?.entries?.size ?: 1)

        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)

        val desiredHeight = CHART_HEIGHT_DP.dpToPx()

        val height = resolveSize(desiredHeight.toInt(), heightMeasureSpec)

        setMeasuredDimension(width, height)
    }


    override fun onDraw(canvas: Canvas) {

        val textBaseline = height - 8f.dpToPx()
        adapter?.getData()?.keys?.forEachIndexed { index, month ->
            val centerX = ((MONTH_WIDTH_DP * 0.5f) + (MONTH_WIDTH_DP * index)).dpToPx()
            val textWidth = textPaint.measureText(month)
            val textX = centerX - (textWidth * 0.5f)

            canvas.drawText(month, textX, textBaseline, textPaint)
        }

        adapter?.getData()?.let { map ->


            val maxValue = map.values.max() ?: 0
            val yStep = (textBaseline - 96f.dpToPx()) / (maxValue - 1)

            chartPath.reset()

            map.values.forEachIndexed { index, value ->
                if (index == 0) {
                    val x = (MONTH_WIDTH_DP * 0.5f).dpToPx()
                    val y = (maxValue - value) * yStep + 32f.dpToPx()
                    chartPath.moveTo(x, y)
                } else {
                    val x = ((MONTH_WIDTH_DP * 0.5f) + (MONTH_WIDTH_DP * index)).dpToPx()
                    val y = (maxValue - value) * yStep + 32f.dpToPx()
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

    private fun getRelativePosition(v: View, event: MotionEvent): Point {
        val location = IntArray(2)
        v.getLocationOnScreen(location)
        val screenX = event.rawX
        val screenY = event.rawY
        val viewX = screenX - location[0]
        val viewY = screenY - location[1]
        return Point(viewX.toInt(), viewY.toInt())
    }

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
        const val MONTH_WIDTH_DP = 100f
        const val CHART_HEIGHT_DP = 400f
    }

}

interface UpdatesListener {

    fun onListUpdate()
}