package com.renegades.labs.spacex.ui.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.renegades.labs.spacex.R
import com.renegades.labs.spacex.entity.launch.Launch
import kotlinx.android.synthetic.main.fragment_details.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var adapter: GalleryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GalleryAdapter(Glide.with(this)) { message ->
            Log.d("myLogs", "show Snackbar!!!")
            Snackbar.make(containerDetails, message, Snackbar.LENGTH_LONG).show()
        }
        recyclerGallery.layoutManager = LinearLayoutManager(context)
        recyclerGallery.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerGallery.adapter = adapter

        setDetails()
    }

    private fun setDetails() {
        args.launch.let { launch ->
            txtTitle.text = launch.missionName
            txtRocket.text = launch.rocket?.rocketName ?: ""
            txtDetails.text = launch.details

            val date = formatDate(launch)
            txtDate.text = if (launch.upcoming == true) {
                "${getString(R.string.upcoming)} $date"
            } else {
                date
            }

            launch.links?.flickrImages.let { adapter.submitList(it) }
        }
    }

    private fun formatDate(it: Launch) =
        SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            .format(Date(TimeUnit.SECONDS.toMillis(it.launchDateUnix ?: 0L)))

}
