package com.renegades.labs.spacex.ui.details

import android.annotation.SuppressLint
import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.jakewharton.rxbinding3.view.clicks
import com.renegades.labs.spacex.R
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.gallery_item.view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class GalleryAdapter(val requestManager: RequestManager, val snackListener: (String) -> Unit) :
    ListAdapter<String, GalleryAdapter.GalleryVH>(object : DiffUtil.ItemCallback<String?>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.gallery_item, parent, false)
        return GalleryVH(view)
    }

    override fun onBindViewHolder(holder: GalleryVH, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class GalleryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CheckResult")
        fun bindTo(url: String) {
            requestManager
                .load(url)
                .centerCrop()
                .transition(withCrossFade())
                .into(itemView.ivPhoto)

            itemView.btnDownload.clicks()
                .observeOn(Schedulers.io())
                .subscribe({
                    val future = requestManager
                        .downloadOnly()
                        .load(url)
                        .submit()

                    saveImage(future.get())

                    snackListener("Image saved in Pictures directory")
                }, Throwable::printStackTrace)
        }

        private fun saveImage(file: File) {
            val imageFileName = "${file.name}.jpg"
            val storageDir = getExternalStoragePublicDirectory(DIRECTORY_PICTURES)
            val imageFile = File(storageDir, imageFileName)

            try {
                copy(file, imageFile)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        private fun copy(src: File, dst: File) {
            FileInputStream(src).use { inStream ->
                FileOutputStream(dst).use { out ->
                    val buf = ByteArray(1024)
                    var len: Int
                    len = inStream.read(buf)
                    while (len > 0) {
                        out.write(buf, 0, len)
                        len = inStream.read(buf)
                    }
                }
            }
        }

    }
}
