package com.renegades.labs.spacex.ui.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.renegades.labs.spacex.R
import com.renegades.labs.spacex.entity.launch.Launch
import kotlinx.android.synthetic.main.fragment_details.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetails()
    }

    private fun setDetails() {
        args.launch.let {
            txtTitle.text = it.missionName
            txtRocket.text = it.rocket?.rocketName ?: ""
            txtDetails.text = it.details

            val date = formatDate(it)
            txtDate.text = if (it.upcoming == true) {
                "${getString(R.string.upcoming)} $date"
            } else {
                date
            }
        }
    }

    private fun formatDate(it: Launch) =
        SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            .format(Date(TimeUnit.SECONDS.toMillis(it.launchDateUnix ?: 0L)))

}
