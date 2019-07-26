package com.renegades.labs.spacex.ui.tabs.chart

import com.renegades.labs.spacex.custom.GenericChartAdapter
import com.renegades.labs.spacex.entity.launch.Launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class LaunchChartAdapter : GenericChartAdapter<Launch>() {

    override fun getXValue(value: Launch): String {
        val date = Date(TimeUnit.SECONDS.toMillis(value.launchDateUnix ?: 0L))
        return SimpleDateFormat("MMM ''yy", Locale.US).format(date)
    }

    override fun getYValue(value: Launch): Int = 1
}