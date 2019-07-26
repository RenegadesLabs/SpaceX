package com.renegades.labs.spacex.custom.chart

abstract class GenericChartAdapter<T> : ChartView.ChartAdapter() {

    private val internalList: MutableList<T> = mutableListOf()

    fun setList(list: List<T>) {
        internalList.clear()
        internalList.addAll(list)
        listener?.onListUpdate()
    }

    abstract fun getXValue(value: T): String

    abstract fun getYValue(value: T): Int

    override fun getData(): Map<String, Int> {
        val result = mutableMapOf<String, Int>()
        internalList.forEach {
            val key = getXValue(it)
            result[key] = (result[key] ?: 0) + getYValue(it)
        }
        return result
    }

}