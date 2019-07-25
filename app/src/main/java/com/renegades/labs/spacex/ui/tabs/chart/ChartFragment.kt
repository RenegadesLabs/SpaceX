package com.renegades.labs.spacex.ui.tabs.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.renegades.labs.spacex.R

class ChartFragment : Fragment() {

    private val viewModel by viewModels<ChartViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

}
