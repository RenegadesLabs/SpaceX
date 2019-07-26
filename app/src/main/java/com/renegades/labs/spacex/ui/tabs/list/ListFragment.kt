package com.renegades.labs.spacex.ui.tabs.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.renegades.labs.spacex.R
import com.renegades.labs.spacex.ui.main.MainViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private var adapter = LaunchListAdapter()
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        disposable = viewModel.pagedLaunches.subscribe { list ->
            recycler.adapter = adapter
            adapter.submitList(list)
        }
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}
