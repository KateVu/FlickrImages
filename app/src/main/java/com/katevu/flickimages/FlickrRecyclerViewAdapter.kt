package com.katevu.flickimages

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class FlickrImageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}


class FlickrRecyclerViewAdapter (private var photoList: List<Photo>): RecyclerView.Adapter<FlickrImageViewHolder>() {
    private val TAG = "FlickrRecyclerViewAdapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        Log.d(TAG, "OnCreateViewHolder called, new view created")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder called")
        //called by layout manager when it wants new data in an existing view
        val photoItem = photoList[position]
        Picasso.get()
            .load(photoItem.image)
            .error(R.drawable.placeholder_image_icon_48dp)
            .placeholder(R.drawable.placeholder_image_icon_48dp)
            .into(holder.thumbnail)
        holder.title.text = photoItem.title

    }

    override fun getItemCount(): Int {
//        Log.d(TAG, "getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    fun loadNewData(newPhoto: List<Photo>) {
        photoList = newPhoto
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int) : Photo? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}