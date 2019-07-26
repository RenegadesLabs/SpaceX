package com.renegades.labs.spacex.ui.tabs.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.PagedList
import com.renegades.labs.spacex.R
import com.renegades.labs.spacex.ui.main.MainViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_chart.*

class ChartFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private var disposable: Disposable? = null
    private val adapter = LaunchChartAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        disposable = viewModel.pagedLaunches.subscribe { pagedList ->
            adapter.setList(pagedList.snapshot().filterNotNull())
            pagedList.addWeakCallback(null, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {
                    adapter.setList(pagedList.snapshot().filterNotNull())
                }

                override fun onInserted(position: Int, count: Int) {}
                override fun onRemoved(position: Int, count: Int) {}
            })
        }
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

}
