package com.renegades.labs.spacex.ui.tabs.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.renegades.labs.spacex.R
import com.renegades.labs.spacex.entity.launch.Launch
import com.renegades.labs.spacex.ui.tabs.TabsFragmentDirections
import kotlinx.android.synthetic.main.list_item.view.*


class LaunchListAdapter : PagedListAdapter<Launch, LaunchListAdapter.LaunchVH>(
    Launch.DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return LaunchVH(view)
    }

    override fun onBindViewHolder(holder: LaunchVH, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class LaunchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(launch: Launch?) {
            if (launch == null) {
                itemView.txtLaunch.text = itemView.context.getString(R.string.loading)
                itemView.icResult.setImageDrawable(null)
            } else {

                val text = "${launch.launchYear} ${launch.missionName} ${launch.rocket?.rocketName}"
                itemView.txtLaunch.text = text

                itemView.icResult.setImageResource(
                    when (launch.launchSuccess) {
                        null -> 0
                        true -> R.drawable.ic_success
                        false -> R.drawable.ic_failure
                    }
                )

                itemView.setOnClickListener {
                    val action = TabsFragmentDirections.actionTabsFragmentToDetailsFragment(launch)
                    it.findNavController().navigate(action)
                }
            }
        }
    }
}

