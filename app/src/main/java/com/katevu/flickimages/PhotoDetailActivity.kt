package com.katevu.flickimages

import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_detail.*

class PhotoDetailActivity : BaseActivity() {
    private val TAG = "PhotoDetailAct"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        activateToolbar(true)

        val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photo
        Log.d(TAG,"link: ${photo.link}")
        Log.d(TAG,"image: ${photo.image}")

        photo_title.text = photo.title
        photo_tags.text = photo.tags
        photo_author.text = photo.author
        Picasso.get()
            .load(photo.link)
            .error(R.drawable.placeholder_image_icon_48dp)
            .placeholder(R.drawable.placeholder_image_icon_48dp)
            .into(photo_image)


        Log.d(TAG,"onCreate ends")

    }
}