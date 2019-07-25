package com.renegades.labs.spacex.ui.tabs


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.renegades.labs.spacex.R
import kotlinx.android.synthetic.main.fragment_tabs.*


class TabsFragment : Fragment() {

    private val tabIcons = arrayOf(R.drawable.ic_list, R.drawable.ic_chart)
    private lateinit var adapter: PagerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = SimpleFragmentPagerAdapter(context, childFragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        repeat(tabs.tabCount) {
            tabs.getTabAt(it)?.setIcon(tabIcons[it])
        }
    }

}
