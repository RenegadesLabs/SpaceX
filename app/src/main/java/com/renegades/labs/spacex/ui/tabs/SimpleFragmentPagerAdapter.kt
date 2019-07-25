package com.renegades.labs.spacex.ui.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.renegades.labs.spacex.R
import com.renegades.labs.spacex.ui.tabs.chart.ChartFragment
import com.renegades.labs.spacex.ui.tabs.list.ListFragment


class SimpleFragmentPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            ListFragment()
        } else {
            ChartFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.list)
            1 -> context.getString(R.string.chart)
            else -> null
        }
    }

}